package com.icaviation

import grails.plugins.springsecurity.SpringSecurityService
import groovy.sql.Sql
import org.hibernate.SessionFactory
import org.hibernate.jdbc.Work

import java.sql.Connection
import java.sql.SQLException

class ToolboxFilters {

    SpringSecurityService springSecurityService
    SessionFactory sessionFactory

    def filters = {

        // all controllers except ApiController
        all(controller: '*', action: '*') {

            after = { Map model ->
                if (model && springSecurityService.loggedIn) {
                    def lastToolboxPage = ToolboxItem.createCriteria().get {

                        cache true

                        projections {
                            max('page')
                        }
                    }

                    model.lastToolboxPage = lastToolboxPage
                }
            }
        }
    }
}
