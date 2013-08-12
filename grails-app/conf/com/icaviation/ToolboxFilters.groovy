package com.icaviation

import grails.plugins.springsecurity.SpringSecurityService

class ToolboxFilters {

    SpringSecurityService springSecurityService

    def filters = {

        all(controller: '*', action: '*') {

            after = { Map model ->
                if (model && springSecurityService.loggedIn) {

                    model.toolboxes = Toolbox.withCriteria {
                        cache: true
                    }

                    log.debug "${model.toolboxes.size()} toolboxes added to model"
                }
            }
        }
    }
}
