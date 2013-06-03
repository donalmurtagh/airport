package com.icaviation

import com.icaviation.command.LoginCommand

class HomeController {

    def index() {
        render view: '/index', model: [login: new LoginCommand()]
    }
}



