<%@ page import="com.ues21.Career" %>



<div class="fieldcontain ${hasErrors(bean: careerInstance, field: 'name', 'error')} required">
	<label for="name">
		<g:message code="career.name.label" default="Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="name" required="" value="${careerInstance?.name}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: careerInstance, field: 'plans', 'error')} ">
	<label for="plans">
		<g:message code="career.plans.label" default="Plans" />
		
	</label>
	<g:select name="plans" from="${com.ues21.CareerPlan.list()}" multiple="multiple" optionKey="id" size="5" value="${careerInstance?.plans*.id}" class="many-to-many"/>

</div>

