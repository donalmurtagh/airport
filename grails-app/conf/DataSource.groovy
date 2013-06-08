
dataSource {
    pooled = true
    driverClassName = "com.mysql.jdbc.Driver"
    dialect = "org.hibernate.dialect.MySQL5InnoDBDialect"
    logSql = false
    url = "jdbc:mysql://localhost/airport?useUnicode=yes&characterEncoding=UTF-8"
    // password is in external file
    dbCreate = "create" // one of 'create', 'create-drop', 'update', 'validate'
    username = 'root'

    // http://grails.1312388.n4.nabble.com/MySQL-datasource-best-practice-setup-for-production-app-td4344921.html
    properties {
        maxActive = 100
        maxIdle = 25
        minIdle = 5
        initialSize = 10
        minEvictableIdleTimeMillis = 60000
        timeBetweenEvictionRunsMillis = 60000
        maxWait = 10000

        testOnBorrow = true
        testWhileIdle = true
        testOnReturn = false

        validationQuery = "select 1"
    }
}

hibernate {
    cache.use_second_level_cache = true
    cache.use_query_cache = true
    cache.region.factory_class = 'net.sf.ehcache.hibernate.EhCacheRegionFactory'
}

environments {

    development {
        dataSource {
            logSql = true
        }

        hibernate {
            format_sql = true
        }
    }

    production {
        dataSource {
            url = "jdbc:mysql://mysql-airport.jelastic.dogado.eu/airport?useUnicode=yes&characterEncoding=UTF-8"
            password = ''
        }
    }
}
