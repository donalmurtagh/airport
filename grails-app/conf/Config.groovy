// locations to search for config files that get merged into the main config;
// config files can be ConfigSlurper scripts, Java properties files, or classes
// in the classpath in ConfigSlurper format

// grails.config.locations = [ "classpath:${appName}-config.properties",
//                             "classpath:${appName}-config.groovy",
//                             "file:${userHome}/.grails/${appName}-config.properties",
//                             "file:${userHome}/.grails/${appName}-config.groovy"]

// if (System.properties["${appName}.config.location"]) {
//    grails.config.locations << "file:" + System.properties["${appName}.config.location"]
// }

grails.project.groupId = appName // change this to alter the default package name and Maven publishing destination
grails.mime.file.extensions = true // enables the parsing of file extensions from URLs into the request format
grails.mime.use.accept.header = false
grails.mime.types = [
    all:           '*/*',
    atom:          'application/atom+xml',
    css:           'text/css',
    csv:           'text/csv',
    form:          'application/x-www-form-urlencoded',
    html:          ['text/html','application/xhtml+xml'],
    js:            'text/javascript',
    json:          ['application/json', 'text/json'],
    multipartForm: 'multipart/form-data',
    rss:           'application/rss+xml',
    text:          'text/plain',
    xml:           ['text/xml', 'application/xml']
]

// URL Mapping Cache Max Size, defaults to 5000
//grails.urlmapping.cache.maxsize = 1000

// What URL patterns should be processed by the resources plugin
grails.resources.adhoc.patterns = ['/images/*', '/css/*', '/js/*', '/plugins/*']

// The default codec used to encode data with ${}
grails.views.default.codec = "none" // none, html, base64
grails.views.gsp.encoding = "UTF-8"
grails.converters.encoding = "UTF-8"
// enable Sitemesh preprocessing of GSP pages
grails.views.gsp.sitemesh.preprocess = true

grails.sitemesh.default.layout = 'main'

// scaffolding templates configuration
grails.scaffolding.templates.domainSuffix = 'Instance'

// Set to false to use the new Grails 1.2 JSONBuilder in the render method
grails.json.legacy.builder = false
// enabled native2ascii conversion of i18n properties files
grails.enable.native2ascii = true
// packages to include in Spring bean scanning
grails.spring.bean.packages = []
// whether to disable processing of multi part requests
grails.web.disable.multipart=false

// request parameters to mask when logging exceptions
grails.exceptionresolver.params.exclude = ['password']

// configure auto-caching of queries by default (if false you can cache individual queries with 'cache: true')
grails.hibernate.cache.queries = false

environments {
    development {
        grails.logging.jul.usebridge = true
    }
    production {
        grails.logging.jul.usebridge = false
        // TODO: grails.serverURL = "http://www.changeme.com"
    }
}

log4j = {
    appenders {
        def logPattern = '%d{dd-MM-yyyy HH:mm:ss,SSS} %5p %c{2} - %m%n'
        console name: 'stdout', layout: pattern(conversionPattern: logPattern)

        file name: "airportLog", file: "airport.log"

        environments {
            production {
                // Change the location of the built-in unfiltered stacktrace logger's file output
                // Must be a location tomcat7 user can write to: http://joshuakehn.com/2012/2/9/Grails-in-Production.html
                rollingFile name: "stacktrace", file: "/var/log/tomcat7/stacktrace.log"

                // Also change location of FileAppender output (see above)
                file name: "airportLog", file: "/var/log/tomcat7/airport.log"
            }
        }
    }

    root {
        error 'stdout', 'airportLog'
    }

    environments {
        development {
            debug 'com.icaviation', 'grails.app'
        }

        production {
            info 'com.icaviation', 'grails.app'
        }
    }

    // resource plugins log at debug level by default
    error 'grails.app.service.org.grails.plugin.resource',
            'grails.app.resourceMappers.org.grails.plugin.resource',
            'grails.app.resourceMappers.org.grails.plugin.zippedresources',
            'grails.app.resourceMappers.com.blockconsult.yuiminifyresources'

    // Suppress this message that appears when every page is loaded
    // "Invocation of <r:resource> for a resource that apparently doesn't exist: http://localhost:8080/summer-festivals/images/logo-home.png"
    error 'grails.app.taglib.org.grails.plugin.resource'
}

// Added by the Spring Security Core plugin:
grails.plugins.springsecurity.userLookup.userDomainClassName = 'com.icaviation.User'
grails.plugins.springsecurity.userLookup.authorityJoinClassName = 'com.icaviation.UserRole'
grails.plugins.springsecurity.authority.className = 'com.icaviation.Role'

grails.plugins.springsecurity.useSecurityEventListener = true


airport {
    userRoleName = 'ROLE_USER'
    adminRoleName = 'ROLE_ADMIN'
}

grails {

    mail {
        host = "smtp.gmail.com"
        port = 465
        username = "festivals@festivals.ie"
        password = "murtagh8"

        props = ["mail.transport.protocol":"smtps",
                "mail.smtps.host":"smtp.gmail.com",
                "mail.smtps.port":"465",
                "mail.smtps.auth":"true"]
    }
}

flashHelper.keys = ['info', 'warn']
flashHelper.separator = ' '