package com.icaviation.command

import grails.validation.Validateable

@Validateable
class ResetPasswordCommand {
    String username
    String password
    String password2

    static constraints = {
        password blank: false, validator: {val, self ->

            if (val != self.password2) {
                'changePasswordCommand.newPassword.validator.error'
            }
        }

        password2 blank: false
    }
}