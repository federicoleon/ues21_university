grails.project.groupId = appName // change this to alter the default package name and Maven publishing destination

grails.app.context = "/"

grails.config.locations = [
  "classpath:constraints/GebConfig.groovy",
  "classpath:resources/Log4JConfig.groovy"
]

grails.views.javascript.library="jquery"

// The ACCEPT header will not be used for content negotiation for user agents containing the following strings (defaults to the 4 major rendering engines)
grails.mime.disable.accept.header.userAgents = ['Gecko', 'WebKit', 'Presto', 'Trident']
grails.mime.types = [ // the first one is the default format
    all:           '*/*', // 'all' maps to '*' or the first available format in withFormat
    atom:          'application/atom+xml',
    css:           'text/css',
    csv:           'text/csv',
    form:          'application/x-www-form-urlencoded',
    html:          ['text/html','application/xhtml+xml'],
    js:            'text/javascript',
    json:          ['application/json', 'text/json'],
    multipartForm: 'multipart/form-data',
    ods:           'application/vnd.oasis.opendocument.spreadsheet',
    pdf:           'application/pdf',
    rss:           'application/rss+xml',
    rtf:           'application/rtf',
    text:          'text/plain',
    hal:           ['application/hal+json','application/hal+xml'],
    xml:           ['text/xml', 'application/xml']
]

// URL Mapping Cache Max Size, defaults to 5000
//grails.urlmapping.cache.maxsize = 1000

// Legacy setting for codec used to encode data with ${}
grails.views.default.codec = "html"

// The default scope for controllers. May be prototype, session or singleton.
// If unspecified, controllers are prototype scoped.
grails.controllers.defaultScope = 'singleton'

// GSP settings
grails {
    views {
        gsp {
            encoding = 'UTF-8'
            htmlcodec = 'xml' // use xml escaping instead of HTML4 escaping
            codecs {
                expression = 'html' // escapes values inside ${}
                scriptlet = 'html' // escapes output from scriptlets in GSPs
                taglib = 'none' // escapes output from taglibs
                staticparts = 'none' // escapes output from static template parts
            }
        }
        // escapes all not-encoded output at final stage of outputting
        // filteringCodecForContentType.'text/html' = 'html'
    }
}


grails.converters.encoding = "UTF-8"
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

grails.resources.processing.enabled = false

// request parameters to mask when logging exceptions
grails.exceptionresolver.params.exclude = ['password']

// configure auto-caching of queries by default (if false you can cache individual queries with 'cache: true')
grails.hibernate.cache.queries = false

// configure passing transaction's read-only attribute to Hibernate session, queries and criterias
// set "singleSession = false" OSIV mode in hibernate configuration after enabling
grails.hibernate.pass.readonly = false
// configure passing read-only to OSIV session by default, requires "singleSession = false" OSIV mode
grails.hibernate.osiv.readonly = false

grails {
    mail {
        host = "smtp.gmail.com"
        port = 465
        username = "ues.sigloveintidos@gmail.com"
        password = "universidad22"
        props = [
            "mail.smtp.auth":"true",
            "mail.smtp.socketFactory.port":"465",
            "mail.smtp.socketFactory.class":"javax.net.ssl.SSLSocketFactory",
            "mail.smtp.socketFactory.fallback":"false"
        ]
    }
}

environments {
    development {
        grails.logging.jul.usebridge = true
        baseURL = "http://22.edu.ar:8080"
    }

    test {
        baseURL = "http://22.edu.ar:8080"
    }

    production {
        grails.logging.jul.usebridge = false
        baseURL = "http://federicoleon.com"
    }
}

university {
    login {
        maxAttempts = 3
    }
}
