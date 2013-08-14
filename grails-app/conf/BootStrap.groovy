import grails.util.DomainBuilder
import org.codehaus.groovy.grails.commons.GrailsApplication
import com.icaviation.*

class BootStrap {

    GrailsApplication grailsApplication

    def init = { servletContext ->

        def airportConfig = grailsApplication.config.airport

        def createRole = { roleName ->
            Role.findByAuthority(roleName) ?: new Role(authority: roleName).save(failOnError: true)
        }

        // create some roles
        def userRole = createRole(airportConfig.userRoleName)
        def adminRole = createRole(airportConfig.adminRoleName)

        // create some admins
        ['tshearer@ic-aviation.com', 'domurtag@yahoo.co.uk'].each {
            createUser it, adminRole
        }

        // create the toolboxes and their items
        def builder = new DomainBuilder()
        builder.classNameResolver = 'com.icaviation'

        def allToolboxes = []
        allToolboxes << builder.toolbox(name: 'Full Service Airlines') {
            toolboxSection(name: 'Research your Catchment') {
                toolboxItem(
                        heading: 'How many people live in your catchment and what are their travel patterns?',
                        text: 'Obviously, the bigger the catchment the more opportunity that exists for the carrier to attract people onto its flights. However, Flag Carrier airlines are also very interested in traffic flows from a particular city or region. Try to identify where people want to go or where they are going already. There are various ways to do this, many airports carry out surveys in the local area for example. Or speak to some of the businesses operating in your area and ask them where they need their employees to travel to most often. There are also companies like MK Metrics that can carry out passenger demand analyses for particular destinations.'
                )
                toolboxItem(
                        heading: 'What is the economic situation of your catchment, high GDP, low unemployment?',
                        text: 'Clearly, higher economic indicators can be very encouraging for a potential airline. This can have a particular interest for the potential of a business travel market for example. Gather as much information together as you can, you might not use it all in one Business Case, but it is always useful to have it to hand.'
                )
                toolboxItem(
                        heading: 'How many businesses are operating in your catchment and in what industries?',
                        text: 'This can also be a very important factor in the establishment of business type routes. Try to identify to the greatest extent possible how many businesses, especially export type companies, are operating in your catchment. And try to pick out some major industry sectors that might be interesting for an airline in a particular country.'
                )
            }
        }

        allToolboxes << builder.toolbox(name: 'Empty Toolbox')
        allToolboxes << builder.toolbox(name: 'Empty Toolbox2')

        allToolboxes.each {
            it.save(failOnError: true)
        }
    }

    /**
     * Create a user if they don't already exist.
     * @param username
     * @param role
     * @return
     */
    private User createUser(username, role) {

        def defaultPassword = 'password'

        User user = User.findByUsername(username) ?: new User(
                username: username,
                password: defaultPassword,
                enabled: true).save(failOnError: true)

        if (!user.authorities.contains(role)) {
            UserRole.create user, role
        }
        user
    }

    def destroy = {
    }
}
