package com.icaviation

import grails.gsp.PageRenderer
import org.codehaus.groovy.grails.web.mapping.LinkGenerator
import org.springframework.context.MessageSource
import org.springframework.context.MessageSourceResolvable

class EmailSender {

    MessageSource messageSource
    PageRenderer pageRenderer
    LinkGenerator linkGenerator
    def mailService

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
     * @param sendImmediately send the mail immediately, or wait until the next time the mail-sending job runs
     */
    void send(String recipient, MessageSourceResolvable messageSubject, String mailTemplate,
              Map mailModel, Boolean sendImmediately = true) {

        String mailSubject = resolve(messageSubject)
        mailModel.baseUrl = getBaseUrl()
        def mailBody = pageRenderer.render(template: mailTemplate, model: mailModel)

        send {
            to recipient
            subject mailSubject
            html mailBody
            immediate sendImmediately
        }
    }

    /**
     * Send an email
     * @param mailServiceArgs
     */
    void send(Closure mailServiceArgs) {
        mailService.sendMail mailServiceArgs
    }
}
