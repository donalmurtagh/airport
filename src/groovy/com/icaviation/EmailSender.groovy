package com.icaviation


import grails.gsp.PageRenderer
import grails.plugin.mail.MailService
import grails.util.Environment
import org.apache.commons.logging.LogFactory
import org.codehaus.groovy.grails.commons.GrailsApplication
import org.codehaus.groovy.grails.web.mapping.LinkGenerator
import org.springframework.context.MessageSource
import org.springframework.context.MessageSourceResolvable

import javax.annotation.PostConstruct
import javax.annotation.PreDestroy
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class EmailSender {

    MessageSource messageSource
    PageRenderer pageRenderer
    LinkGenerator linkGenerator
    MailService mailService

    private final ExecutorService threadPool = Executors.newFixedThreadPool(5)

    @PreDestroy
    private void close() {
        threadPool.shutdown()
    }

    private String getBaseUrl() {
        String baseUrl = linkGenerator.link(absolute: true, uri: '/')

        // if the last char is '/' remove it or things will go horribly wrong when we use it with <g:link base="${baseUrl}".../>
        baseUrl[-1] == '/' ? baseUrl[0..-2] : baseUrl
    }

    private String resolve(resolvable) {
        messageSource.getMessage(resolvable, Locale.default)
    }

    /**
     * Sends an email
     *
     * @param recipient email address of the the recipient
     * @param messageSubject used to resolve the email subject
     * @param mailTemplate path to the template that generates the body of the message
     * @param mailModel model that the template uses to generate the message body
     * @param async
     */
    void send(String recipient, MessageSourceResolvable messageSubject, String mailTemplate, Map mailModel, boolean async = false) {

        String mailSubject = resolve(messageSubject)
        mailModel.baseUrl = getBaseUrl()
        def mailBody = pageRenderer.render(template: mailTemplate, model: mailModel)

        Closure mailServiceArgs = {
            to recipient
            subject mailSubject
            html mailBody
        }

        send(mailServiceArgs, async)
    }

    /**
     * Send an email
     * @param mailServiceArgs
     * @param async sending asynchronously makes for a snappier UI when registering, resetting password etc. However,
     * it also means that email sending fails silently if there are any errors, e.g. invalid email account. We have
     * been burned by this in the past, so enable asynch with caution.
     */
    void send(Closure mailServiceArgs, boolean async = false) {

        Runnable mailSendTask = {-> mailService.sendMail mailServiceArgs } as Runnable

        if (async) {
            threadPool.submit(mailSendTask)
        } else {
            mailSendTask.run()
        }
    }
}