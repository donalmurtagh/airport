package com.icaviation

import com.icaviation.command.*
import com.icaviation.i18n.GroovyMessageSourceResolvable
import grails.plugins.springsecurity.Secured
import grails.plugins.springsecurity.SpringSecurityService
import org.apache.commons.lang.RandomStringUtils
import org.codehaus.groovy.grails.plugins.springsecurity.SpringSecurityUtils
import org.springframework.transaction.TransactionStatus


class UserController {

    EmailSender emailSender
    SpringSecurityService springSecurityService

    @Lazy
    private Role userRole = {
        Role.createCriteria().get {
            eq 'authority', grailsApplication.config.airport.userRoleName
        }
    }()

    def beginPasswordReset() {
        [forgotPassword: new ForgotPasswordCommand()]
    }

    /**
     * Send an email that will allow the user to reset their password
     */
    def forgotPassword(ForgotPasswordCommand command) {

        if (!command.validate()) {
            render(view: 'beginPasswordReset', model: [forgotPassword: command])
            flashHelper.warn 'forgotPassword.error'
            return
        }
        User user = User.findByUsername(command.username)
        def registrationCode = new RegistrationCode(username: user.username).save(failOnError: true)

        String url = generateLink('resetPassword', [t: registrationCode.token])

        def mailModel = [url: url]
        def subject = new GroovyMessageSourceResolvable('email.subject.passwordReset')
        emailSender.send(user.username, subject, '/email/passwordReset', mailModel, true)

        log.info "Password confirmation email sent to $user.username"
        flashHelper.info 'forgotPassword.email': user.username
        redirect controller: 'home'
    }

    /**
     * This action has two roles:
     * <ul>
     *   <li>Handles the link contained in the password reset email, i.e. shows the form that allows the user to
     *   choose a new password</li>
     *   <li>Handles the submission of this form</li>
     * </ul>
     */
    def resetPassword(ResetPasswordCommand command) {

        String token = params.t
        def defaultTargetUrl = SpringSecurityUtils.securityConfig.successHandler.defaultTargetUrl

        def registrationCode = token ? RegistrationCode.findByToken(token) : null
        if (!registrationCode) {
            flashHelper.warn 'resetPassword.badCode'
            redirect uri: defaultTargetUrl
            return
        }

        if (!request.post) {
            return [token: token, command: new ResetPasswordCommand()]
        }

        command.username = registrationCode.username

        if (!command.validate()) {
            flashHelper.warn 'resetPassword.change.error'
            return [token: token, command: command]
        }

        RegistrationCode.withTransaction { status ->
            def user = User.findByUsername(registrationCode.username)
            user.password = command.password
            user.save(failOnError: true)
            registrationCode.delete()
        }

        springSecurityService.reauthenticate registrationCode.username
        flashHelper.info 'resetPassword.success'

        String postResetUrl = defaultTargetUrl
        redirect uri: postResetUrl
    }

    private String generateLink(String action, linkParams) {
        createLink(base: "$request.scheme://$request.serverName:$request.serverPort$request.contextPath",
                controller: 'user', action: action,
                params: linkParams)
    }

    @Secured(['ROLE_ADMIN', 'ROLE_USER'])
    def changePassword(ChangePasswordCommand command) {

        if (command.validate()) {
            User user = springSecurityService.currentUser
            user.password = command.newPassword
            user.save(failOnError: true)
            flashHelper.info 'passwordChange.success'
            redirect controller: 'home'

        } else {
            render view: '/index', model: [changePassword: command]
        }
    }

    @Secured(['ROLE_ADMIN'])
    def listUsers() {
        [users: findAllRegularUsers(), newUser: new User()]
    }

    private String generatePasword() {
        RandomStringUtils.randomAlphanumeric(10)
    }

    @Secured(['ROLE_ADMIN'])
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

    @Secured(['ROLE_ADMIN'])
    def delete() {
        User user = User.get(params.id)

        User.withTransaction {
            UserRole.removeAll(user)
            user.delete()
        }
        flashHelper.info 'user.deleted': user.username
        redirect action: 'listUsers'
    }

    @Secured(['ROLE_ADMIN'])
    def toggleEnabled() {
        User user = User.get(params.id)
        user.enabled = !user.enabled
        user.save(failOnError: true)

        def newStatus = user.enabled ? 'enabled' : 'disabled'
        flashHelper.info 'user.enabled.toggle': [user.username, newStatus]
        redirect action: 'listUsers'
    }

    @Secured(['ROLE_ADMIN'])
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
