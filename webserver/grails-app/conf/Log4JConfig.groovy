import org.apache.log4j.ConsoleAppender
import org.apache.log4j.DailyRollingFileAppender
import org.apache.log4j.PatternLayout
import org.apache.log4j.Level

log4j.main = {

    info  'com.ues21'
    
    error  'org.codehaus.groovy.grails.web.servlet',        // controllers
           'org.codehaus.groovy.grails.web.pages',          // GSP
           'org.codehaus.groovy.grails.web.sitemesh',       // layouts
           'org.codehaus.groovy.grails.web.mapping.filter', // URL mapping
           'org.codehaus.groovy.grails.web.mapping',        // URL mapping
           'org.codehaus.groovy.grails.commons',            // core / classloading
           'org.codehaus.groovy.grails.plugins',            // plugins
           'org.codehaus.groovy.grails.orm.hibernate',      // hibernate integration
           'org.springframework',
           'org.hibernate',
           'net.sf.ehcache.hibernate'

    appenders {
        appender new DailyRollingFileAppender(
            name: "appLog",
            append: true,
            threshold: Level.INFO,
            file: "/usr/local/log/ues22.log",
            datePattern: "'.'yyyy-MM-dd",
            layout: new PatternLayout("%d [%t] %-5p %c %x - %m%n"))
    }

    info appLog: 'appLog', additivity: false
    
    root {
        info 'appLog'
    }
}
