package com.icaviation

import com.icaviation.command.ChangePasswordCommand
import com.icaviation.i18n.GroovyMessageSourceResolvable
import grails.plugins.springsecurity.Secured
import grails.plugins.springsecurity.SpringSecurityService
import org.apache.commons.lang.RandomStringUtils
import org.springframework.transaction.TransactionStatus

@Secured(['ROLE_ADMIN'])
class UserController {

    EmailSender emailSender
    SpringSecurityService springSecurityService

    @Lazy
    private Role userRole = {
        Role.createCriteria().get {
            eq 'authority', grailsApplication.config.airport.userRoleName
        }
    }()

    @Secured(['ROLE_ADMIN', 'ROLE_USER'])
    def changePassword(ChangePasswordCommand command) {

        if (command.validate()) {
            User user = springSecurityService.currentUser
            user.password = command.newPassword
            user.save(failOnError: true)

        } else {
            render view: '/index', model: [changePassword: command]
        }
    }

    def listUsers() {
        [users: findAllRegularUsers(), newUser: new User()]
    }

    private String generatePasword() {
        RandomStringUtils.randomAlphanumeric(10)
    }

    def addUser() {
        def randomPassword = generatePasword()
        def username = params.username
        User user = new User(username: username, password: randomPassword, enabled: true)

        if (user.validate()) {
            User.withTransaction { TransactionStatus status ->

                try {
                    user = user.save(failOnError: true)
                    UserRole.create user, userRole

                    sendRegistrationEmail(username, randomPassword)
                    flashHelper.info 'register.success': username
                    redirect action: 'listUsers'

                } catch (ex) {
                    status.rollbackOnly
                    log.error "Error registering user, errors: $user.errors", ex

                    flashHelper.warn 'register.fail': username
                    render view: 'listUsers', model: [users: findAllRegularUsers(), newUser: user]
                }
            }
        } else {
            flashHelper.warn 'register.fail.invalid': username
            render view: 'listUsers', model: [users: findAllRegularUsers(), newUser: user]
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

    private findAllRegularUsers() {
        UserRole.findAllByRoleName(userRole.authority).user
    }

    def delete() {
        User user = User.get(params.id)

        User.withTransaction {
            UserRole.removeAll(user)
            user.delete()
        }
        flashHelper.info 'user.deleted': user.username
        redirect action: 'listUsers'
    }

    def toggleEnabled() {
        User user = User.get(params.id)
        user.enabled = !user.enabled
        user.save(failOnError: true)

        def newStatus = user.enabled ? 'enabled' : 'disabled'
        flashHelper.info 'user.enabled.toggle': [user.username, newStatus]
        redirect action: 'listUsers'
    }

    def resendInvite() {
        User user = User.get(params.id)
        String newPassword = generatePasword()
        user.password = newPassword
        user.save(failOnError: true)
        sendRegistrationEmail(user.username, newPassword)
        flashHelper.info 'user.invite.resent': user.username
        redirect action: 'listUsers'
    }
}
