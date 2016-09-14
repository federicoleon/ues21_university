package com.ues21

class CareerService {

    public List getCareersModelView() {
        def careers = Career.list()
        def result = []
        careers?.each { career ->
            result << [
                id: career.id,
                name: career.name
            ]
        }
        return result
    }
}
