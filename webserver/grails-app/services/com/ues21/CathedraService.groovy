package com.ues21

import com.ues21.enums.*

class CathedraService {
    
    public List getCathedraModelView(List <Cathedra> cathedras) {
        List result = []
        cathedras?.each { cathedra ->
            result << [
                id: cathedra.id,
                subject_name: cathedra.subject.name,
                classroom: cathedra.classroom.name,
                center: UniversityCentersEnum.byId(cathedra.classroom.center).type(),
                building: UniversityBuildingsEnum.byId(cathedra.classroom.building).type()
            ]
        }
        return result
    }
}