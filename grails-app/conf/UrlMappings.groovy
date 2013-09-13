import grails.util.GrailsUtil

class UrlMappings {

	static mappings = {
		"/$controller/$action?/$id?"{
			constraints {
				// apply constraints here
			}
		}

        "/"(controller: 'home')
        "/otherCustomers"(view: "/otherCustomers")
        "/googleAdwords"(view: "/googleAdwords")

        "403"(controller: "error", action: "forbidden")
        "404"(controller: "error", action: "notFound")
        "500"(controller: "error", action: "serverError")
	}
}
