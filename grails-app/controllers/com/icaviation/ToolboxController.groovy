package com.icaviation

import grails.plugins.springsecurity.Secured
import grails.plugins.springsecurity.SpringSecurityService


@Secured(['ROLE_ADMIN', 'ROLE_USER'])
class ToolboxController {

    SpringSecurityService springSecurityService

    def showPage(Integer page) {
        page = page ?: 1

        List<Response> completedItems = Response.withCriteria {
            eq 'user', springSecurityService.currentUser
            eq 'complete', true

            toolboxItem {
                eq 'page', page
            }
        }

        List<Long> completedToolboxItemIds = completedItems.toolboxItem.id
        [completedToolboxItemIds: completedToolboxItemIds]
    }
}
