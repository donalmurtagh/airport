package com.icaviation.command

import com.icaviation.User
import grails.validation.Validateable

@Validateable
class ForgotPasswordCommand {
    String username

    static constraints = {
        username email: true, validator: {email ->
            User.countByUsername(email) == 1
        }
    }
}
