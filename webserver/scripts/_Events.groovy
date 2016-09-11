eventCompileEnd = {
    ant.copy(todir: classesDirPath) {
        fileset(dir:"grails-app/conf") {
            include(name:"**/*Config.groovy")
        }
    }
}
