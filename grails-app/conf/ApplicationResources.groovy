import grails.util.Environment
import org.apache.commons.lang.StringUtils

boolean doBundle = !grails.util.GrailsUtil.developmentEnv

modules = {
    base {
        dependsOn 'jquery'
        defaultBundle doBundle ? 'base' : false

        // wrapper closure is a workaround for this bug: http://jira.grails.org/browse/GPRESOURCES-131
        resource url: 'http://fonts.googleapis.com/css', attrs: [type: 'css'], wrapper: {link ->

            StringUtils.replaceOnce(link, '/css', '/css?family=Oswald:400,700')
        }

        resource 'css/smart/skeleton.css'
        resource 'css/smart/style.css'
        resource 'css/smart/inner.css'
        resource 'css/smart/color.css'
        resource 'css/smart/layout.css'
        resource 'css/main.css'

        resource 'js/hoverIntent.js'
        resource 'js/superfish.js'
        resource 'js/supersubs.js'
        resource 'js/tinynav.min.js'
        resource 'js/custom.js'
        resource url: 'http://html5shim.googlecode.com/svn/trunk/html5.js', disposition: 'head',
                wrapper: { s -> "<!--[if lt IE 9]>$s<![endif]-->" }
    }

    toolbox {
        defaultBundle doBundle ? 'toolbox' : false
        resource 'css/toolbox.css'
    }
}