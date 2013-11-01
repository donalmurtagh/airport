import grails.util.GrailsUtil

class UrlMappings {

	static mappings = {
		"/$controller/$action?/$id?"{
			constraints {
				// apply constraints here
			}
		}

        "/"(controller: 'home')

        def staticPages = ['otherCustomers', 'digital', 'reports', 'pilot', 'media', 'otherPassengers']

        staticPages.each {page ->
            "/$page"(view: "/$page")
        }

        "403"(controller: "error", action: "forbidden")
        "404"(controller: "error", action: "notFound")
        "500"(controller: "error", action: "serverError")
	}
}
