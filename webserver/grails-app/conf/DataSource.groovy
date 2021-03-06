dataSource {
    pooled = true
    jmxExport = true
    driverClassName = "org.h2.Driver"
    username = "sa"
    password = ""
}

hibernate {
    cache.use_second_level_cache = true
    cache.use_query_cache = false
    cache.region.factory_class = 'org.hibernate.cache.ehcache.SingletonEhCacheRegionFactory' // Hibernate 4
    singleSession = true // configure OSIV singleSession mode
    flush.mode = 'manual' // OSIV session flush mode outside of transactional context
}

// environment specific settings
environments {
    development {

        dataSource {
            dbCreate = "create-drop"
            url = "jdbc:mysql://127.0.0.1:3306/university?useUnicode=yes&characterEncoding=UTF-8"
            pooled = true
            jmxExport = true
            driverClassName = "com.mysql.jdbc.Driver"
            dialect = "org.hibernate.dialect.MySQL5InnoDBDialect"
            username = "root"
            password = "root"
            properties {
                validationQuery = "SELECT 1"
                testOnBorrow = true
                testWhileIdle = true
                testOnReturn = false
                maxActive = 50
                maxIdle = 25
                minIdle = 5
                initialSize = 5
                minEvictableIdleTimeMillis = 1800000
                timeBetweenEvictionRunsMillis = 1800000
                maxWait = 10000
            }
        }
    }

    test {
        dataSource {
            dbCreate = "create-drop"
            url = "jdbc:h2:mem:testDb;MVCC=TRUE;LOCK_TIMEOUT=10000;DB_CLOSE_ON_EXIT=FALSE"
        }
    }

    production {
        dataSource {
            dbCreate = "update"
            url = "jdbc:mysql://127.0.0.1:3306/university?useUnicode=yes&characterEncoding=UTF-8"
            pooled = true
            jmxExport = true
            driverClassName = "com.mysql.jdbc.Driver"
            dialect = "org.hibernate.dialect.MySQL5InnoDBDialect"
            username = "ues22"
            password = "theues22pass"
            properties {
                validationQuery = "SELECT 1"
                testOnBorrow = true
                testWhileIdle = true
                testOnReturn = false
                maxActive = 50
                maxIdle = 25
                minIdle = 5
                initialSize = 5
                minEvictableIdleTimeMillis = 1800000
                timeBetweenEvictionRunsMillis = 1800000
                maxWait = 10000
            }
        }
    }
}
