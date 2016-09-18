package com.ues21

import com.ues21.enums.*

import grails.transaction.Transactional

@Transactional
class SubjectService {

    def careerService

    public List<Subject> getCorrelatives(Subject subject) {
        if(!subject) {
            return []
        }
        return subject.correlatives
    }

    public List getCorrelativesModelView(Long careerId) {
        List<Subject> subjects = careerService.getAllSubjects(careerId)
        List result = []
        subjects?.each { subject ->
            def current = [
                id: subject.id,
                name: subject.name,
                semester: subject.semester,
                correlatives: []
            ]

            if(subject.correlatives) {
                subject.correlatives.each { correlative ->
                    current.correlatives << [
                        id: correlative.id,
                        name: correlative.name,
                        semester: correlative.semester
                    ]
                }
            }
            result << current
        }
        return result
    }

    public boolean canRegisterForSubject(Subject subject, List<Subject> approvedSubjects, List<Subject> onGoingSubjects) {
        // If subject is null, then return false
        if(!subject) {
            return false
        }

        // If student is actually registered in this subject, return false.
        if(onGoingSubjects?.contains(subject)) {
            return false
        }

        // If there are no correlatives for this subject, return true
        if(!subject.correlatives) {
            return true
        }

        // If there is at least one correlative but there are no approved subjects, return false
        if(!approvedSubjects) {
            return false
        }

        // If at least one of the correlatives is not approved, return false
        subject.correlatives.each { correlative ->
            if(!approvedSubjects.contains(correlative)) {
                return false
            }
        }

        // The subject is ready for registration :-)
        return true
    }
}
