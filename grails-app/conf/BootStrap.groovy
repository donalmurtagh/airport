import grails.util.GrailsUtil
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

        def questionIndex = 0
        3.times {
            def page = it + 1

            5.times {
                new ToolboxItem(page: page, text: "Item ${++questionIndex}").save(failOnError: true)
            }
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
