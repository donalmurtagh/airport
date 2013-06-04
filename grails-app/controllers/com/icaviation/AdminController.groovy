package com.icaviation

import com.icaviation.i18n.GroovyMessageSourceResolvable
import grails.plugins.springsecurity.Secured
import org.apache.commons.lang.RandomStringUtils

@Secured(['ROLE_ADMIN'])
class AdminController {

    EmailSender emailSender

    def listUsers() {
        [users: User.list(), newUser: new User()]
    }

    def addUser() {
        def randomPassword = RandomStringUtils.randomAlphanumeric(10)
        def username = params.username
        User user = new User(username: username, password: randomPassword, enabled: true)

        if (user.validate()) {
            // TODO: add the change password URL
            def mailModel = [username: username, password: randomPassword, changePasswordUrl: '']
            def subject = new GroovyMessageSourceResolvable('register.subject')
            emailSender.send(username, subject, '/email/register', mailModel)

            User.withTransaction {

                Role userRole = Role.createCriteria().get {
                    eq 'authority', grailsApplication.config.airport.userRoleName
                    cache true
                }

                user = user.save(failOnError: true)
                UserRole.create user, userRole

                flashHelper.info 'register.success': username
            }
        } else {
            flashHelper.warn 'register.fail': username
        }
        redirect action: 'listUsers'
    }
}
