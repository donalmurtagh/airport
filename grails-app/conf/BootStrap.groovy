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
            toolboxSection(
                    heading: 'Research your Catchment',
                    text: 'Understanding your catchment is an important first step in creating a Business Case for airlines. From understanding more of the detail about your catchment, you will probably be able to identify more accurately which carriers and more particularly, which destinations you should be focusing your Business Case towards. Take the following as a guide to build up a better picture of your catchment:') {
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
                toolboxItem(
                        heading: 'Do the businesses have links with the home country of a certain airline?',
                        text: 'Linked to the above question, try to establish whether or not the businesses in the area are linked in any way with a particular country. For example, if there are companies who have head quarters or other strong links with Germany, then obviously a German carrier would be a more sensible option to target than a French one. This type of information, although potentially highly valuable for an airline, is not always readily available. So, try to speak to the chamber of commerce for your region to get as much information as possible.'
                )
                toolboxItem(
                        heading: 'Are there special tourism related activities that should be highlighted?',
                        text: 'Contact local tourist boards and get them more involved with what is happening at the airport. Ask them to highlight areas of interest, historic links between other countries and any other attractions in the area that could be useful when marketing the airport towards potential new airlines.'
                )
            }

            toolboxSection(
                    heading: 'Target a Carrier',
                    text: 'As you start to build a better picture of the market surrounding your airport, you should also be able to start identifying new markets that could be served from your airport. When you have destinations in mind, then it is easier to start narrowing down the airlines that can operate such routes. When you have identified certain carrier(s), then you need to start researching their businesses also. Start with these questions:') {
                toolboxItem(
                        heading: 'What countries are they flying to/from?',
                        text: 'Flag Carrier airlines usually have one country in which they maintain a large presence. But try to identify what other countries they are present it. Are there any markets that they have stated publicly that they wish to increase their presence in, research this on the media page. If they are not operating at all in your market, they may be reluctant to start unless you can identify some really strong reasons why they should.'
                )
                toolboxItem(
                        heading: 'What sort of aircraft do they use and can those aircraft operate to your airport?',
                        text: 'Flag Carrier airlines in particular tend to operate many different types of aircraft, from small regional aircraft to much larger long haul types. You should have some idea what sort of aircraft would be suitable for serving a particular route to your airport and ensure that those type(s) of aircraft can physically land there. There will be more on this in the Build the Case section.'
                )
                toolboxItem(
                        heading: 'Are they in a period of growth or consolidation?',
                        text: 'Many Flag Carrier airlines in Europe are emerging from a period of intense consolidation and cost cutting. Be sure that you fully understand what phase your target airline is in. Whilst most Flag Carrier airlines are now starting to look again at growth options - research carefully what sort of growth opportunities they are actually looking for. For example, are they looking for regional airports to feed one of their hubs? Or are they looking to increase connections between city destinations? Or are they focusing on a long haul strategy only?'
                )
                toolboxItem(
                        heading: 'Are they already flying to your airport?',
                        text: 'In the case of smaller regional airports, Flag Carrier airlines usually only operate one or two routes to feed into one of their larger hubs. If they already have a route to your airport, are they likely to want to fly more? Think about which other of their destinations they might want to link with your airport? Or is there an opportunity to increase capacity on existing services as opposed to introducing new routes?'
                )
                toolboxItem(
                        heading: 'Are they flying to other airports near your airport?',
                        text: 'If they are, then consider what new opportunities there might be for them at your airport. Or can they duplicate a service to your airport? Consider also, what your competitors are doing to attract the airline and could you be doing it too, or even offering something better?'
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
