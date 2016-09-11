<%@ page import="com.ues21.Student" %>



<div class="fieldcontain ${hasErrors(bean: studentInstance, field: 'firstName', 'error')} required">
	<label for="firstName">
		<g:message code="student.firstName.label" default="First Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="firstName" required="" value="${studentInstance?.firstName}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: studentInstance, field: 'lastName', 'error')} required">
	<label for="lastName">
		<g:message code="student.lastName.label" default="Last Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="lastName" required="" value="${studentInstance?.lastName}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: studentInstance, field: 'identification', 'error')} required">
	<label for="identificationType">
		<g:message code="student.identification.type.label" default="Identification" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="identificationType" name="identificationType" from="${identification_types}" optionKey="type" required="" value="${studentInstance?.identification?.type}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: studentInstance, field: 'identification', 'error')} required">
	<label for="identificationNumber">
		<g:message code="student.identification.number.label" default="Identification" />
		<span class="required-indicator">*</span>
	</label>

	<g:textField name="identificationNumber" required="" value="${studentInstance?.identification?.id}"/>
</div>

<div class="fieldcontain required">
	<label for="phone">
		<g:message code="student.phone.label" default="Phone" />
		<span class="required-indicator">*</span>
	</label>

	<g:textField name="phoneNumber" required="" value=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: studentInstance, field: 'fileNumber', 'error')} required">
	<label for="fileNumber">
		<g:message code="student.fileNumber.label" default="File Number" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="fileNumber" required="" value="${studentInstance?.fileNumber}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: studentInstance, field: 'email', 'error')} required">
	<label for="email">
		<g:message code="student.email.label" default="E-mail" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="email" required="" value=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: studentInstance, field: 'emailConfirmation', 'error')} required">
	<label for="emailConfirmation">
		<g:message code="student.email.label" default="E-mail" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="emailConfirmation" required="" value=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: studentInstance, field: 'careers', 'error')} ">
	<label for="careers">
		<g:message code="student.careers.label" default="Careers" />
		
	</label>
	<g:select name="careers" from="${careers}" multiple="multiple" optionKey="id" size="5" value="${careers}"/>

</div>

