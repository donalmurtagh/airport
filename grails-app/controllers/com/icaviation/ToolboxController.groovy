package com.icaviation

import grails.plugins.springsecurity.Secured
import grails.plugins.springsecurity.SpringSecurityService

import javax.servlet.http.HttpServletResponse


@Secured(['ROLE_ADMIN', 'ROLE_USER'])
class ToolboxController {

    SpringSecurityService springSecurityService

    def show() {

        Toolbox toolbox = Toolbox.read(params.id)

        List<Response> completedItems = Response.withCriteria {
            eq 'user', springSecurityService.currentUser
            eq 'complete', true

            toolboxItem {
                toolboxSection {
                    eq 'toolbox', toolbox
                }
            }
        }

        List<Long> completedToolboxItemIds = completedItems.toolboxItem.id
        [completedToolboxItemIds: completedToolboxItemIds, toolbox: toolbox]
    }

    def toggleItem() {
        User user = springSecurityService.currentUser
        ToolboxItem toolboxItem = ToolboxItem.read(params.id)

        if (toolboxItem) {
            Response response = Response.findByUserAndToolboxItem(user, toolboxItem)

            if (response) {
                response.complete = !response.complete
            } else {
                new Response(user: user, toolboxItem: toolboxItem).save(failOnError: true)
            }

            render status: HttpServletResponse.SC_OK
        }
    }
}
