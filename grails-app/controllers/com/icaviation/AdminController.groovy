package com.icaviation

import com.icaviation.i18n.GroovyMessageSourceResolvable
import grails.plugins.springsecurity.Secured
import org.apache.commons.lang.RandomStringUtils
import org.springframework.transaction.TransactionStatus

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
            User.withTransaction { TransactionStatus status ->

                try {
                    Role userRole = Role.createCriteria().get {
                        eq 'authority', grailsApplication.config.airport.userRoleName
                        cache true
                    }

                    user = user.save(failOnError: true)
                    UserRole.create user, userRole

                    sendRegistrationEmail(username, randomPassword)
                    flashHelper.info 'register.success': username
                    redirect action: 'listUsers'

                } catch (ex) {
                    status.rollbackOnly
                    log.error "Error registering user, errors: $user.errors", ex

                    flashHelper.warn 'register.fail': username
                    render view: 'listUsers', model: [users: User.list(), newUser: user]
                }
            }
        } else {
            flashHelper.warn 'register.fail.invalid': username
            render view: 'listUsers', model: [users: User.list(), newUser: user]
        }
    }

    private sendRegistrationEmail(String username, String plainTextPassword) {

        // TODO: add the change password URL
        def mailModel = [username: username, password: plainTextPassword, changePasswordUrl: '']
        def subject = new GroovyMessageSourceResolvable('register.subject')

        // even if the email is sent synchronously it will not cause a transaction rollback, so we might as well
        // send it asynchronously
        emailSender.send(username, subject, '/email/register', mailModel, true)
    }
}
