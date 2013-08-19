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
                    text: '<p>Understanding your catchment is an important first step in creating a Business Case for airlines. From understanding more of the detail about your catchment, you will probably be able to identify more accurately which carriers and more particularly, which destinations you should be focusing your Business Case towards. Take the following as a guide to build up a better picture of your catchment:</p>') {
                toolboxItem(
                        heading: 'How many people live in your catchment and what are their travel patterns?',
                        text: '<p>Obviously, the bigger the catchment the more opportunity that exists for the carrier to attract people onto its flights. However, Flag Carrier airlines are also very interested in traffic flows from a particular city or region. Try to identify where people want to go or where they are going already. There are various ways to do this, many airports carry out surveys in the local area for example. Or speak to some of the businesses operating in your area and ask them where they need their employees to travel to most often. There are also companies like MK Metrics that can carry out passenger demand analyses for particular destinations.</p>'
                )
                toolboxItem(
                        heading: 'What is the economic situation of your catchment, high GDP, low unemployment?',
                        text: '<p>Clearly, higher economic indicators can be very encouraging for a potential airline. This can have a particular interest for the potential of a business travel market for example. Gather as much information together as you can, you might not use it all in one Business Case, but it is always useful to have it to hand.</p>'
                )
                toolboxItem(
                        heading: 'How many businesses are operating in your catchment and in what industries?',
                        text: '<p>This can also be a very important factor in the establishment of business type routes. Try to identify to the greatest extent possible how many businesses, especially export type companies, are operating in your catchment. And try to pick out some major industry sectors that might be interesting for an airline in a particular country.</p>'
                )
                toolboxItem(
                        heading: 'Do the businesses have links with the home country of a certain airline?',
                        text: '<p>Linked to the above question, try to establish whether or not the businesses in the area are linked in any way with a particular country. For example, if there are companies who have head quarters or other strong links with Germany, then obviously a German carrier would be a more sensible option to target than a French one. This type of information, although potentially highly valuable for an airline, is not always readily available. So, try to speak to the chamber of commerce for your region to get as much information as possible.</p>'
                )
                toolboxItem(
                        heading: 'Are there special tourism related activities that should be highlighted?',
                        text: '<p>Contact local tourist boards and get them more involved with what is happening at the airport. Ask them to highlight areas of interest, historic links between other countries and any other attractions in the area that could be useful when marketing the airport towards potential new airlines.</p>'
                )
            }

            toolboxSection(
                    heading: 'Target a Carrier',
                    text: '<p>As you start to build a better picture of the market surrounding your airport, you should also be able to start identifying new markets that could be served from your airport. When you have destinations in mind, then it is easier to start narrowing down the airlines that can operate such routes. When you have identified certain carrier(s), then you need to start researching their businesses also. Start with these questions:</p>') {
                toolboxItem(
                        heading: 'What countries are they flying to/from?',
                        text: '<p>Flag Carrier airlines usually have one country in which they maintain a large presence. But try to identify what other countries they are present it. Are there any markets that they have stated publicly that they wish to increase their presence in, research this on the media page. If they are not operating at all in your market, they may be reluctant to start unless you can identify some really strong reasons why they should.</p>'
                )
                toolboxItem(
                        heading: 'What sort of aircraft do they use and can those aircraft operate to your airport?',
                        text: '<p>Flag Carrier airlines in particular tend to operate many different types of aircraft, from small regional aircraft to much larger long haul types. You should have some idea what sort of aircraft would be suitable for serving a particular route to your airport and ensure that those type(s) of aircraft can physically land there. There will be more on this in the Build the Case section.</p>'
                )
                toolboxItem(
                        heading: 'Are they in a period of growth or consolidation?',
                        text: '<p>Many Flag Carrier airlines in Europe are emerging from a period of intense consolidation and cost cutting. Be sure that you fully understand what phase your target airline is in. Whilst most Flag Carrier airlines are now starting to look again at growth options - research carefully what sort of growth opportunities they are actually looking for. For example, are they looking for regional airports to feed one of their hubs? Or are they looking to increase connections between city destinations? Or are they focusing on a long haul strategy only?</p>'
                )
                toolboxItem(
                        heading: 'Are they already flying to your airport?',
                        text: '<p>In the case of smaller regional airports, Flag Carrier airlines usually only operate one or two routes to feed into one of their larger hubs. If they already have a route to your airport, are they likely to want to fly more? Think about which other of their destinations they might want to link with your airport? Or is there an opportunity to increase capacity on existing services as opposed to introducing new routes?</p>'
                )
                toolboxItem(
                        heading: 'Are they flying to other airports near your airport?',
                        text: '<p>If they are, then consider what new opportunities there might be for them at your airport. Or can they duplicate a service to your airport? Consider also, what your competitors are doing to attract the airline and could you be doing it too, or even offering something better?</p>'
                )
            }

            toolboxSection(
                    heading: 'Build the Case',
                    text: "<p>Now you know which routes and which airlines you want to target, so it's time to consider how to build a Business Case that the airline cannot refuse. Start by following these simple steps:</p>") {
                toolboxItem(
                        heading: 'Are the Charges Competitive?',
                        text: '<p>Are the charges themselves competitive with respect to other airports of a similar size whom you could be competing with? Airlines of all types are focusing more and more on costs these days and they may well simply choose a cheaper alternative if there is one available. If you end up reducing your airport charges to make them more competitive, advertise this fact by issuing press releases, or posting adverts via well known industry channels. Let people know you are in the market and that you are can be competitive on costs.</p>'
                )
                toolboxItem(
                        heading: 'Are the Charges Unnecessarily Complex?',
                        text: "<p>Simplify the charging structure. Traditionally airports maintain highly complex charging systems, with separate fees for landing, handling, passenger, security, CUTE, baggage, air bridges, etc. Airport fees don't need to be this complicated and airlines will be very relieved to receive a much more simplified (and preferably all inclusive) cost that they can understand straight away without having to get a team of financial analysts to figure them out.</p>"
                )
                toolboxItem(
                        heading: 'Who Controls the Charges?',
                        text: '<p>For cities and regions involved in Work Package 5.1 that do not have direct influence over the fees and charges at the airport they represent, then every effort should be made to lobby the people who do control the fees. Meet with them, present to them the issues, the challenges and make them understand that airports need to be competitive or they will continue to struggle to grow traffic.</p>'
                )
                toolboxItem(
                        heading: 'Incentivise, Incentivise, Incentivise!',
                        text: '''\
                                <p>If you are a small regional airport with little or no traffic, then you should be looking at every possible way to incentivise airlines to fly to your airport. Look at innovative ways of lowering charges for new routes and passengers. And keep in mind that the traditional model of airport revenues is changing. Look at these examples:</p>
                                <p><strong>ADD DIAGRAMS HERE</strong></p>
                                <p>These diagrams show that historically, airports relied upon receiving revenues from airlines as the main source of their income. However, airlines are becoming much more cost conscious and even Flag Carrier airlines who traditionally didn't use to attach such importance to low airport costs, are  seeking out the best airport deals. The new revenue model is the way of the future, especially for smaller regional airports. So when you are considering your published charges and introducing new incentives to attract airlines, remember that airports need to consider other methods of generating revenues than simply relying on the airline. Some examples could be as follows:</p>
                                <ul>
                                    <li>Undertake a complete review of all non-aeronautical activity at the airport. Assess whether certain areas can be improved - for example can you increase car parking charges?</li>
                                    <li>Visit other airports and look around at what they are doing, take inspiration.</li>
                                    <li>Seek out new areas for which you can charge people to advertise at your airport.</li>
                                    <li>Think about using the airport for other reasons than just transporting passengers, for example charge people host birthday parties or other events at the airport.</li>
                                    <li>Employ third party experts to guide you through a process of improving your non-aeronautical offering.</li>
                                </ul>'''
                )
                toolboxItem(
                        heading: 'Enlist the Support of Private Businesses',
                        text: '<p>In relation to Flag Carrier airlines, one potentially very important step in the Business Case process, is to get local companies to guarantee that their employees will use the airline in question for all of the business flights that they need to take. Get an estimate as to how many seats per year that would amount to and then get Letters of Intent signed by each company that you can incorporate into your Business Case.</p>'
                )
                toolboxItem(
                        heading: 'Tourism Authority Involvement',
                        text: "<p>Contact the local tourism authorities and ask them to contribute towards a marketing fund that can be used to enhance the business case for the target airline. Don't forget that tourism companies are one of the biggest benefactors of airport and airline activity. Consider asking them to join your meetings with the airline so that they can present the attractions that may exist in the vicinity of the airport - and let them see how hard it can be to attract new traffic to an airport.</p>"
                )
                toolboxItem(
                        heading: 'Tour Operator Involvement',
                        text: '<p>Contact local tour operator companies and ask them if they can guarantee to book a certain amount of seats per annum. Their ability to do this will obviously depend a little bit on the destination in question but meet with as many tour operator companies as possible and encourage them get on board in some way.</p>'
                )
            }
            toolboxSection(
                    heading: 'Case Study',
                    text: '''\
                            <p>So you have researched a lot and looked different ways to create a Business case, but how do you put it all together? Treat all the components of the Business Case like pieces of a puzzle. There are various different - but related - components that all must fit together neatly to create a clear and concise plan for the airline. It is also important to note at this stage, that no two Business Cases will ever (or should ever) be the same. Each market, carrier, destination and airport will all have their own unique set of circumstances that will dictate what sort of Business Case should be presented. Situations also change so rapidly in the aviation industry that even a Business Case that works for an airline this year, won't automatically work next year.</p>
                            <p><strong>ADD DIAGRAMS HERE</strong></p>
                            <p>Below is a fictitious example of a Business Case that could work for a large German Flag Carrier airline. The airport is a small regional airport in Finland called Kaiatsu looking for a connection to Frankfurt Main airport in Germany.</p>''') {
                toolboxItem(
                        heading: 'The Catchment',
                        text: '<p>We have a large catchment of 1.5m people living within 1 hour of the airport. We also have several high tech export companies operating in our market, employing overall 4,500 people. Approximately, 15 of these companies have subsidiaries and/or headquarters in the region around Frankfurt.</p>'
                )
                toolboxItem(
                        heading: 'Tourism Opportunities',
                        text: "<p>In our region there is a long history of German holiday makers visiting the lakes and forests, the historic links date back to the 1800's when German copper mining companies used to explore the region. Last year over 25,000 German tourists visited our area, mostly travelling with other airlines, taking 2 or more flights to get here.</p>"
                )
                toolboxItem(
                        heading: 'Route Potential Analysis',
                        text: '<p>We recently employed the services of RoutePro to carry out route potential anaysis of a route from Frankfurt to Kaiatsu. The results show that there are at least 50,000 travellers from our region going to Frankfurt each year, mostly connecting through other European hubs using 2 or more flights to get there.</p>'
                )
                toolboxItem(
                        heading: 'Private Businesses Involvement',
                        text: '<p>We attach to our proposal 20 signed Letters of Intent guaranteeing that the private companies near our airport will book 35,000 thousand tickets each year on your airline.</p>'
                )
                toolboxItem(
                        heading: 'Local Investments',
                        text: '<p>There has recently been a major investment in a technology park in Kaiatsu that will act as a centre of excellence for European research in nano-technologies. The institute will employ 4,000 researchers and will be sponsored by 250 private European technology companies.</p>'
                )
                toolboxItem(
                        heading: 'Competitive Charges',
                        text: '<p>The airport charge, shall be €11 per departing passenger, fixed for three years, It will be all inclusive of all charges (landing / takeoff, PSC, ramp and passenger handling and security).</p>'
                )
                toolboxItem(
                        heading: 'Marketing',
                        text: '<p>A marketing fund has been offered by the local tourism authority of €150,000 per annum  guaranteed  for three years. An extensive marketing plan on both ends of the route has also been agreed and will be implemented as soon as the route is announced.</p>'
                )
                toolboxItem(
                        heading: 'Tour Operators',
                        text: '<p>We also attach a Letter of Intent from local travel agencies who will sell City Breaks to Frankfurt all year round, and which they estimate could use up to 12,000 seats per annum.</p>'
                )
            }
            toolboxSection(
                    heading: 'Deliver and Communicate',
                    text: "<p>So when you have created a strong Business Case, what do you do next?  Making contact with airlines can sometimes be quite a daunting task, Flag Carrier airlines in particular are often very large companies with thousands of staff, so where do you start? There are some general principles to follow when approaching airlines whether that is by phone, by letter or in a direct face to face meeting:</p>") {
                toolboxItem(
                        heading: 'Be Clear and Concise',
                        text: "<p>Keep the message clear and concise - busy people don't want to be wasting their time listening to lot of superfluous information. Stick to the facts that you have gathered while creating your Business Case and avoid unnecessary information.</p>"
                )
                toolboxItem(
                        heading: 'Do Your Research',
                        text: "<p>As mentioned in the Target a Carrier section, you need to understand the airline you are contacting by doing as much research as possible about them. There is nothing worse than airport managers who ask an airline to perform an operation that they clearly cannot do. This may be because the carrier does not have a base at the other end of the route, or its aircraft may be unsuitable, or it may have said publicly that they are not focusing on that market - there could be many reasons. But understanding about the airlines' business model and strategy will make you stand out.</p>"
                )
                toolboxItem(
                        heading: 'Use the Appropriate Language',
                        text: '<p>If you can speak and write in the local language of the airline, then obviously use it. But if you are not familiar with the language, get assistance with translations so that you can present something that looks good and can be clearly understood.</p>'
                )
                toolboxItem(
                        heading: 'Timing',
                        text: '<p>Remember the IATA schedule periods for summer operations (April - October) and winter (November - March) respectively. If the route you are targeting will clearly only be a summer production, then make sure the airline has enough time to plan for that.</p>'
                )
                toolboxItem(
                        heading: 'Have Patience',
                        text: "<p>If you send a letter or a leave a phone message, don't start to bombard them until you get a response - they will not appreciate this. Give them time and send gentle non-invasive reminders every so often.</p>"
                )
                toolboxItem(
                        heading: "If you don't have any contacts at the airline you want to target, what should you do?",
                        text: '''\
                                <p>There are various industry events that take place all over the world. Many of these either have official meetings with airlines that you pay for or informal networking events. Consider attending and make contact with as many airlines as possible. There are also a few other tricks worth considering, as set out below:</p>
                                <ol>
                                    <li>The Routes Company organises international and regional aviation events, whereby airline delegates can pay to attend meetings with airlines. There are also informal networking events. These events can be expensive, but an excellent forum for meeting airlines.</li>
                                    <li>French Connect in France is a smaller version of the Routes events and quite focused on the French market - but a good forum if this region is on your radar for route development.</li>
                                    <li>IATA runs regular slot conferences and these are always attended by representatives from the main airlines. So if the slot coordinator from your airport is attending, why not tag along and you never know who you might get to meet.</li>
                                    <li>For more informal networking opportunities, look at some specific events taking places in various countries like:
                                        <ul>
                                            <li>The Internationale Tourismus-Börse (ITB) in Berlin</li>
                                            <li>The World Travel Market, London</li>
                                            <li>Borsa Internazionale Del Turismo (BIT) in Milan</li>
                                            <li>International Tourism Trade Fair (FITUR) in Madrid</li>
                                        </ul>
                                    </li>
                                    <li>Writing unsolicited letters to airlines can sometimes seem like a pointless task. And if you write a letter that begins with "Dear Airline.....", you can probably forget about making any progress. Try to find out the name of a key person within the company and then DO NOT send them pages and pages of waffle. This could be your one chance to make an impression - again this all comes back to presenting a solid Business Case so they cannot ignore your offer.</li>
                                    <li>There are many aviation specialists and consultants who have a network of contacts at different airlines, consider using one of them to get some ideas about who is best to contact.</li>
                                    <li>Contact colleagues at other airport to get a name and contact details for the airline you wish to approach.</li>
                                </ol>'''
                )
            }
        }

        allToolboxes << builder.toolbox(name: 'Low Cost Airlines') {
            toolboxSection(
                    heading: 'Research your Catchment',
                    text: '<p>Understanding your catchment is an important first step in creating a Business Case for airlines. From understanding more of the detail about your catchment, you will probably be able to identify more accurately which carriers and more particularly, which destinations you should be focusing your Business Case towards. Take the following as a guide to build up a better picture of your catchment:</p>') {
                toolboxItem(
                        heading: 'How many people live in your catchment?',
                        text: '<p>Generally speaking, Low Cost Carriers (LCC), will seek a large catchment that they can draw people from to use their services. However, it\'s important to note that the "low fare" phenomenon in Europe means that people generally travel longer distances to an airport to avail of the cheaper fares. So don\'t think of just your catchment as the 1/2 hours around the airport - indeed there are many documented cases in Europe of people driving for up to 500km to reach an airport that has low fare flights. Think about any centres population from where people might be willing to travel to access cheap flights and consider them as potentially part of your catchment. This can also be a very important point in relation to passenger marketing and identifying where to market your airport, more about that in the <strong>Passenger Marketing</strong> section.</p>'
                )
                toolboxItem(
                        heading: 'What is the demographic of your catchment?',
                        text: '<p>This is very important. What type of people live in your catchment area? LCC attract a wide cross section of travellers, from sun seekers, to migrant worker and their families and even business travellers. So think about where the people in your catchment are most likely to want to go. Is there is high unemployment with local workers seeking opportunities elsewhere? Or conversely, is there a strong need for workers from other countries to fill vacant positions? Or is there strong demand for flights to leisure destinations? Think about all of the possible opportunities and carry out as much research as possible so you can identify some strong reasons for a LCC carrier to fly to your market.</p>'
                )
                toolboxItem(
                        heading: 'Are there special tourism related activities that should be highlighted?',
                        text: '<p>LCC are prolific generators of tourism traffic. Their low fares mean people can travel more often and will fly to places that they probably never would have considered, because they can afford to do so. Identifying unique and popular tourism opportunities can therefore have a very positive impact on a Business Case for a LCC.</p>'
                )
                toolboxItem(
                        heading: 'How well connected is your airport by public transport?',
                        text: "<p>What is the infrastructure like around your airport, is there a good bus or rail connection to a local city for example? LCC passengers may be more likely to opt for public transport options, so it's important to let the LCC know what is available. If you are a regional or secondary airport, is there an efficient and cheap way to travel to a larger city, if there is not something available, think about partnering with a local bus or rail company to provide and advertise the service.</p>"
                )
            }

            toolboxSection(
                    heading: 'Target a Carrier',
                    text: '<p>As you start to build a better picture of the market surrounding your airport, you should also be able to start identifying new markets that could be served from your airport. When you have destinations in mind, then it is easier to start narrowing down the LCC airlines that can operate such routes. When you have identified certain carrier(s), then you need to start researching their businesses also. Start with these questions:</p>') {
                toolboxItem(
                        heading: 'What countries are they flying to/from?',
                        text: '<p>LCC will often be have extensive operations in several different markets. They operate a base system, whereby they base aircraft in one location and fly out from there to other non-based airports. Look at where there bases are and try to ascertain if they could connect to your airport from one of those bases?  Try to understand a bit more about what their plans are for new markets by researching on the <strong>Media Page</strong>.</p>'
                )
                toolboxItem(
                        heading: 'What sort of aircraft do they use and can those aircraft operate to your airport?',
                        text: '<p>Flag Carrier airlines tend to operate just one or types of aircraft, in many cases large mid-range jets. These aircraft will not be compatible with all small regional airports, so you should have some idea whether or not those aircraft can physically land at your airport.</p>'
                )
                toolboxItem(
                        heading: 'Where is their growth likely to be focused?',
                        text: '<p>LCC are one of the few airline groups that have maintained a steady growth profile in recent years, even when many other carriers were contacting or even going out of business entirely. With some of the largest LCC recently placing big aircraft orders that will materialise in the next 2-3 years, it looks like LCC will continue to lead the way in terms of strong growth. But, LCC are businesses just like any other company and they want to make money. So they will only grow in markets where there is a good opportunity. Research what the LCC have been saying recently in the media about where they want to grow. And look to see where they have recently been opening new routes, airports and bases to give you an indication which direction they are growing in.</p>'
                )
                toolboxItem(
                        heading: 'Are they already flying to your airport?',
                        text: "<p>In the case of smaller regional airports, it is important to comprehend whether or not the market can handle big increases in capacity that LCC tend to bring, because of their larger aircraft type. Examine in as much detail as possible how well the existing flights are performing. Check load factor information and try to look online to build up a picture of the fares that the carrier is offering - high load factors and consistent high fares being available online can be an indication that a route is performing well from the airlines' perspective.</p>"
                )
                toolboxItem(
                        heading: 'Are they flying to other airports near your airport?',
                        text: '<p>If they are, then consider what new opportunities there might be for them at your airport. LCC are considered to be more promiscuous and less loyal than other airline types. So consider what your competitors are doing to attract the airline and could you be doing it too, or even offering something better? For example, LCC are always excited by the opportunity to reduce their costs so they can pass these savings on to passengers in the form of lower fares.</p>'
                )
                toolboxItem(
                        heading: 'Operational Requirements',
                        text: "<p>LCC don't generally require very complicated infrastructure. For example, they often avoid using air bridges so they can facilitate short turnaround times. This may sound simple, but can your airport actually provide for such operations? Try going to another airport to watch how a LCC operates, follow the turnaround process and see if your airport can provide the same level of service. The absence of an efficient operation can sometimes make or break a deal with a LCC.</p>"
                )
            }
            toolboxSection(
                    heading: 'Build the Case',
                    text: "<p>Now you know which routes and which airlines you want to target, so it's time to consider how to build a Business Case that the airline cannot refuse! In relation to LCC, as their name suggests, it is important to remember that cost is a major driver in almost all of their decisions. So this section will focus a lot on creating a competitive cost offer for the airline.</p>") {
                toolboxItem(
                        heading: 'Are the Charges Competitive?',
                        text: '<p>Are the charges themselves competitive with respect to other airports of a similar size whom you could be competing with? LCC are always seeking out the most competitive airport deals. LCC can and do choose cheaper alternatives if there is one available. Make the charges as low as you possibly can, keeping in mind applicable legal requirements.</p>'
                )
                toolboxItem(
                        heading: 'Are the Charges too Complicated?',
                        text: "<p>Simplify the charging structure. This is very important for LCC who like to make everything as simple as possible. Traditionally airports maintain highly complex charging systems, with separate fees for landing, handling, passenger, security, CUTE, baggage, air bridges, etc. Airport fees don't need to be this complicated and LCC will be very relieved to receive a much more simplified (and preferably all inclusive) cost that they can understand straight away without having to get a team of financial analysts to figure them out.</p>"
                )
                toolboxItem(
                        heading: 'Who Controls the Charges?',
                        text: '<p>For cities and regions involved in Work Package 5.1 that do not have direct influence over the fees and charges at the airport they represent, then every effort should be made to lobby the people who do control the fees. Without competitive airport charges, LCC will not even consider your airport - we will define what competitive can mean in the final section of this marketing tool.</p>'
                )
                toolboxItem(
                        heading: 'Incentivise, Incentivise, Incentivise!',
                        text: '''\
                            <p>If you are a small regional airport with little or no traffic, then you in particular should be looking at every possible way to incentivise airlines to fly to your airport. Look at innovative ways of not only lowering charges for new routes and passengers but offering additional marketing, growth or other incentives. And keep in mind that the traditional model of airport revenues is changing. Look at these examples:</p>
                            <p><strong>ADD DIAGRAMS HERE</strong></p>
                            <p>These diagrams show that historically, airports relied upon receiving revenues from airlines as the main source of their income. However, LCC airlines have a lot of flexibility about where they will deliver traffic and they won't fly to expensive airports - that is clear. The new airport revenue model is the way of the future, especially for smaller regional airports. So when you are considering your published charges and introducing new incentives to attract airlines, remember that airports need to consider other methods of generating revenues than simply relying on the airline. Some examples could be as follows:</p>
                            <ul>
                                <li>Undertake a complete review of all non-aeronautical activity at the airport. Assess whether certain areas can be improved - for example can you increase car parking charges? </li>
                                <li>Visit other airports and look around at what they are doing, take inspiration.</li>
                                <li>Seek out new areas for which you can charge people to advertise at your airport.</li>
                                <li>Think about using the airport for other reasons than just transporting passengers, for example charge people to host birthday parties or other events at the airport.</li>
                                <li>Employ third party experts to guide you through a process of improving your non-aeronautical offering.</li>
                            </ul>'''
                )
                toolboxItem(
                        heading: 'Tourism Authority Involvement',
                        text: '<p>LCC have the potential to deliver large volumes of tourists to a local area by virtue of their large aircraft and high volume business models. Local tourism authorities should therefore be fully involved in the discussions with the potential airline. Ask them to contribute towards a marketing fund that can be used to enhance the Business Case for the target airline. Consider asking them to join your meetings with the airline so that they can present the attractions that may exist in the vicinity of the airport - and let them see how hard it can be to attract new traffic to an airport.</p>'
                )
                toolboxItem(
                        heading: 'Tour Operator Involvement',
                        text: '<p>Tour operators who can offer a guarantee to book seats with the carrier can be a good option for LCC, but it is important to note that not all LCC accommodate tour operator bookings. In addition, their ability to book seats will depend a little bit on the destination in question, but meet with as many tour operator companies as possible and encourage them get on board in some way.</p>'
                )
                toolboxItem(
                        heading: 'Strategic Alliances',
                        text: '''\
                                <p>As part of the Pilot Project, we learned that two airports can actually work together to make a business case more attractive:</p>
                                <p><strong>ADD DIAGRAMS HERE</strong></p>
                                <p>This sort of production requires that only light capacity would be offered as the airline flies from one large airport and splits the service between two airports in the destination market. In order for this to fit neatly into an airlines' scheduling system, keep in mind that the two smaller airports, should be a similar flight distance away from the big airport.</p>
                                <p>The two key advantages of this strategy are that it reduces the carriers' risk, because it won't have to worry about oversupplying one market. Secondly, two airports or regions can collectively make a better overall offer to the airline. The challenge with this strategy is that it is focused almost exclusively on the tourism market. The light frequency and split production would not suit business travellers. This potentially limits the amount of airlines that would go for it, but LCC would be certainly be one type of carrier that would be well suited for it. There are two key requirements to fulfill before embarking on this process:</p>

                                <h3>1. Locate Suitable Airports</h3>
                                <p>Is there actually another airport that you can identify, that fits the geographic profile required, in terms of distance from the big airport. This may sound like a simple question, but it can be more complicated than you might think to identify two suitable airports.</p>

                                <h3>2. Is there a Common Objective?</h3>
                                <p>Even if you can find two airports that work from a Geographic perspective, do they have the same objectives as your airport? Both airports who are involved need t o be able to commit the same level of resources and support, so there needs to be a strong and common objective in order for this to succeed.</p>
                                '''
                )
            }
            toolboxSection(
                    heading: 'Case Study',
                    text: '''\
                            <p>So you have researched a lot and looked different ways to create a Business case, but how do you put it all together? Treat all the components of the Business Case like pieces of a puzzle. There are various different - but related - components that all must fit together neatly to create a clear and concise plan for the airline. It is also important to note at this stage, that no two Business Cases will ever (or should ever) be the same. Each market, carrier, destination and airport will all have their own unique set of circumstances that will dictate what sort of Business Case should be presented. Situations also change so rapidly in the aviation industry that even a Business Case that works for an airline this year, won't automatically work next year.</p>
                            <p><strong>ADD DIAGRAMS HERE</strong></p>
                            <p>Below is a fictitious example of a Business Case that could work for a European Low Cost Carrier. The airport is a regional airport in Sweden called Senäp and they are looking for a summer only connection to Barcelona airport in Spain.</p>''') {
                toolboxItem(
                        heading: 'Airport Charges',
                        text: """\
                                <p>The airport will offer a 3 year, zero airport and handling cost incentive to any airline that can transport a minimum of 50,000 passengers to and from any new destination in Spain. In addition, from year  4 onwards, for a further 3 years, the all inclusive airport fee shall be €5 per departing passenger. There will also be a passenger volume incentive that will reduce the airlines' costs as follows:    </p>

                                <table class="item-table">
                                    <thead>
                                        <tr>
                                            <th>Year</th>
                                            <th>Total Pax Volume p.a.</th>
                                            <th>All Inclusive Cost Per Departing Pax</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <tr>
                                            <td>4</td>
                                            <td>150,000</td>
                                            <td>€5</td>
                                        </tr>
                                        <tr>
                                            <td>5</td>
                                            <td>200,000</td>
                                            <td>€3</td>
                                        <tr>
                                        <tr>
                                            <td>5</td>
                                            <td>250,000</td>
                                            <td>€0</td>
                                        <tr>
                                    </tbody>
                                </table>
                                """
                )
                toolboxItem(
                        heading: 'Catchment',
                        text: '<p>There are 750,000 people living within a 1 hour drive of the airport but within just 3-4 hours, there are almost 2m people. The Airport is very well connected by motorway and there is a train station right at the airport that connects with hourly trains to the City of Helmander, which has a population of 350,000 people</p>'
                )
                toolboxItem(
                        heading: 'Marketing Support',
                        text: '<p>The tourism authority has agreed to contribute towards a joint marketing fund of €250,000 per annum for 3 years. It has also been agreed that a comprehensive marketing plan will take place on both sides of the route, to ensure that there is as much inbound traffic from Spain on the route as possible, giving the route a better chance of success for the airline.</p>'
                )
                toolboxItem(
                        heading: 'Route Potential',
                        text: '<p>We already have several charter and scheduled flights to the canary islands, Malaga and Alicante. During the summer months all of these flights are booked with an average load factor of 85% and the averages fares are usually around €250 per round trip.</p>'
                )

            }
        }

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
