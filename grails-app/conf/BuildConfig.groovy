import grails.util.Environment

grails.servlet.version = "3.0" // Change depending on target container compliance (2.5 or 3.0)
grails.project.class.dir = "target/classes"
grails.project.test.class.dir = "target/test-classes"
grails.project.test.reports.dir = "target/test-reports"
grails.project.target.level = 1.7
grails.project.source.level = 1.7
//grails.project.war.file = "target/${appName}-${appVersion}.war"

grails.project.dependency.resolution = {
    // inherit Grails' default dependencies
    inherits("global") {
        // specify dependency exclusions here; for example, uncomment this to disable ehcache:
        // excludes 'ehcache'
    }
    log "error" // log level of Ivy resolver, either 'error', 'warn', 'info', 'debug' or 'verbose'
    checksums true // Whether to verify checksums on resolve
    legacyResolve true // whether to do a secondary resolve on plugin installation, not advised but here for backwards compatibility

    repositories {
        grailsPlugins()
        grailsHome()
        grailsCentral()

        mavenLocal()
        mavenCentral()
        mavenRepo "http://maven.springframework.org/milestone/"

        // uncomment these (or add new ones) to enable remote dependency resolution from public Maven repositories
        //mavenRepo "http://snapshots.repository.codehaus.org"
        //mavenRepo "http://repository.codehaus.org"
        //mavenRepo "http://download.java.net/maven/2/"
        //mavenRepo "http://repository.jboss.com/maven2/"
    }
    dependencies {
        // specify dependencies here under either 'build', 'compile', 'runtime', 'test' or 'provided' scopes eg.
        runtime 'mysql:mysql-connector-java:5.1.33'
    }

    plugins {
        build   ":tomcat:7.0.52.1"
        runtime ":hibernate:3.6.10.10",
                ":console:1.5.0",
                ":cached-resources:1.0",
                ":jquery:1.8.3",
                ":resources:1.2.8"

        compile ":cache-headers:1.1.7",
                ":yui-war-minify:1.5",
                ":spring-security-core:1.2.7.4",
                ":fields:1.4",
                ":flash-helper:0.9.9",
                ":mail:1.0.1"

        // a transitive dependency of the spring security plugin that should be installed automatically, but wasn't
        // http://grails.1312388.n4.nabble.com/No-thread-bound-request-found-error-td4631072.html
        compile ":webxml:1.4.1"

        if (Environment.current == Environment.PRODUCTION) {
            // don't include in dev, because it prevents static resources from reloading
            // don't include in test, because it will be installed every time tests are run
            runtime ":zipped-resources:1.0"
        }
    }
}
