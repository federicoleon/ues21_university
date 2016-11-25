package com.ues21.constraints

import org.codehaus.groovy.grails.validation.AbstractConstraint
import org.springframework.validation.Errors

abstract class BasicConstraint extends AbstractConstraint {
    
    protected String getDefaultMessage(String param) {
        return param
    }

    public boolean supports(Class type) {
        return true
    }

    public String getName() {
        NAME
    }

    protected void processValidate(Object target, Object property, Errors errors) {
        def result = validateConstraint(target, propertyValue, errors)
        if (result instanceof CharSequence) {
            errors.rejectValue(
                constraintPropertyName, 
                target.getClass().getSimpleName() + "." + getPropertyName() + "." + getName(), 
                result.toString()
            )
        }
        target._errors = errors
    }

    public void validate(Object target, Object property, Errors errors) {
        checkState()
        processValidate(target, property, errors);
    }

    abstract def validateConstraint(String target, String value, Object errors)
}
