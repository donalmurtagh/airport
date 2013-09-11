package com.icaviation

import grails.plugins.springsecurity.SpringSecurityService

class ToolboxFilters {

    SpringSecurityService springSecurityService

    def filters = {

        all(controller: '*', action: '*') {

            after = { Map model ->
                if (springSecurityService.loggedIn) {

                    // store the data as a request attribute rather than in the model, because the model is not available
                    // when a URL is mapped directly to a view in UrlMappings.groovy
                    request['toolboxes'] = Toolbox.withCriteria {
                        cache: true
                    }
                }
            }
        }
    }
}
