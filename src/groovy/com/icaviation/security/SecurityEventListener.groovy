package com.icaviation.security

import grails.plugins.springsecurity.SpringSecurityService
import org.codehaus.groovy.grails.web.util.WebUtils
import org.springframework.context.ApplicationListener
import org.springframework.context.MessageSource
import org.springframework.security.authentication.event.InteractiveAuthenticationSuccessEvent

class SecurityEventListener implements ApplicationListener<InteractiveAuthenticationSuccessEvent> {

    MessageSource messageSource
    SpringSecurityService springSecurityService

    @Override
    void onApplicationEvent(InteractiveAuthenticationSuccessEvent event) {
        def flashScope = WebUtils.retrieveGrailsWebRequest().flashScope
        def username = springSecurityService.currentUser.username
        flashScope.info = messageSource.getMessage('springSecurity.errors.login.success', [username].toArray(), null)
    }
}
