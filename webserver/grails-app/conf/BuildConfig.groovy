import grails.util.Environment

grails.servlet.version = "3.0" // Change depending on target container compliance (2.5 or 3.0)
grails.project.class.dir = "target/classes"
grails.project.test.class.dir = "target/test-classes"
grails.project.test.reports.dir = "target/test-reports"
grails.project.work.dir = "target/work"
grails.project.target.level = 1.6
grails.project.source.level = 1.6
//grails.project.war.file = "target/${appName}-${appVersion}.war"

grails.project.fork = [
   test: [maxMemory: 1024, minMemory: 64, debug: false, maxPerm: 256, daemon:true],
   run: [maxMemory: 1024, minMemory: 64, debug: false, maxPerm: 256],
   war: [maxMemory: 1024, minMemory: 64, debug: false, maxPerm: 256],
   console: [maxMemory: 1024, minMemory: 64, debug: false, maxPerm: 256]
]

grails.project.dependency.resolver = "maven" // or ivy
grails.project.dependency.resolution = {
    // inherit Grails' default dependencies
    inherits("global") {
        // specify dependency exclusions here; for example, uncomment this to disable ehcache:
        // excludes 'ehcache'
    }
    log "error" // log level of Ivy resolver, either 'error', 'warn', 'info', 'debug' or 'verbose'
    checksums true // Whether to verify checksums on resolve
    legacyResolve false // whether to do a secondary resolve on plugin installation, not advised and here for backwards compatibility

    repositories {
        inherits true // Whether to inherit repository definitions from plugins

        grailsPlugins()
        grailsHome()
        mavenLocal()
        grailsCentral()
        mavenCentral()
    }

    def gebVersion = "0.13.0"
    def seleniumVersion = "2.25.0"

    dependencies {

        runtime 'mysql:mysql-connector-java:5.1.29'

        test "org.grails:grails-datastore-test-support:1.0.2-grails-2.4"
        test "org.spockframework:spock-grails-support:0.7-groovy-2.0"
        test "org.gebish:geb-spock:$gebVersion"
        test "org.seleniumhq.selenium:selenium-firefox-driver:$seleniumVersion"
        test "org.seleniumhq.selenium:selenium-chrome-driver:$seleniumVersion"
        test("org.seleniumhq.selenium:selenium-htmlunit-driver:$seleniumVersion") {
            exclude "xml-apis"
        }
    }

    plugins {
        // plugins for the build system only
        build ":tomcat:7.0.55.2"

        // plugins for the compile step
        compile ':cache:1.1.8'
        compile ":asset-pipeline:2.1.5"
        compile "org.grails.plugins:geb:$gebVersion"
        compile ':webflow:2.1.0'

        // plugins needed at runtime but not for compilation
        runtime ":jquery:1.11.1"
        runtime ":hibernate4:4.3.8.1"

        test "org.grails.plugins:code-coverage:2.0.3-3"
        test(":spock:0.7") {
            exclude "spock-grails-support"
        }
    }
}
