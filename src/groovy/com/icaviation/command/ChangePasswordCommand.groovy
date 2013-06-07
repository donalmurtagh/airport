package com.icaviation.command

import com.icaviation.User
import grails.plugins.springsecurity.SpringSecurityService
import grails.validation.Validateable

@Validateable
class ChangePasswordCommand {

    SpringSecurityService springSecurityService
    def saltSource

    String currentPassword
    String newPassword
    String newPasswordConfirm

    private String getEncodedCurrentPassword() {
        def salt = saltSource.systemWideSalt
        springSecurityService.encodePassword(currentPassword, salt)
    }

    static constraints = {

        springSecurityService bindable: false

        currentPassword validator: {val, self ->
            User user = self.springSecurityService.currentUser
            user.password == self.encodedCurrentPassword
        }

        newPassword blank: false, validator: {val, self ->

            if (val != self.newPasswordConfirm) {
                'user.password.validator.error'
            }
        }
    }
}