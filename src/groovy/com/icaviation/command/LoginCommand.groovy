package com.icaviation.command

import grails.validation.Validateable

@Validateable
class LoginCommand {
    String j_username
    String j_password
    boolean _spring_security_remember_me

    static constraints = {
        j_username email: true
        j_password blank: false
    }
}