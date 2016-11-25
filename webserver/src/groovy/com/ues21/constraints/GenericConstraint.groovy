package com.ues21.constraints

import grails.util.Holders
import org.codehaus.groovy.grails.validation.AbstractConstraint
import org.codehaus.groovy.grails.validation.ConstrainedProperty
import org.codehaus.groovy.grails.validation.Constraint
import org.springframework.validation.Errors

class GenericConstraint extends AbstractConstraint {

    private static final String NAME = "genericConstraint"

    def grailsApplication = Holders.getGrailsApplication()
    
    public String getName() {
        return NAME
    }

    public boolean supports(Class type) {
        return true
    }
    
    def getConstraintsFor(String fieldName) {
        def constraints = grailsApplication.config.constraints.get(fieldName)
        return constraints
    }
        
    protected boolean skipNullValues() {
        return false
    }
    
    protected boolean skipBlankValues() {
        return false
    }

    protected void processValidate(Object target, Object property, Errors errors) {
        def constraints = getConstraintsFor(getPropertyName())
        constraints.each {
            def constraint = instantiateConstraint(ConstrainedProperty.constraints.get(it.key), it.value)
            def result = constraint.validateConstraint(target, property?.toString(), errors)
            if (result instanceof CharSequence) {
                errors.rejectValue(constraintPropertyName, target.getClass().getSimpleName() + "." + getPropertyName() + "." + getName() + "." + constraint.NAME, result.toString())
            }
        }
        target._errors = errors
    }
    
    private instantiateConstraint(constraintClass, parameter) {
        if(constraintClass instanceof List) {
            constraintClass = (Class) constraintClass.first()
        }
        Constraint instance = (Constraint) ((Class<?>)constraintClass).newInstance()
        instance.setParameter(parameter)
        instance.setPropertyName(constraintPropertyName)
        return instance
    }
}
