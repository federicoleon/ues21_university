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
	<label for="identification">
		<g:message code="student.identification.label" default="Identification" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="identification" name="identification.id" from="${com.ues21.Identification.list()}" optionKey="id" required="" value="${studentInstance?.identification?.id}" class="many-to-one"/>

</div>

<div class="fieldcontain ${hasErrors(bean: studentInstance, field: 'creationDate', 'error')} required">
	<label for="creationDate">
		<g:message code="student.creationDate.label" default="Creation Date" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="creationDate" precision="day"  value="${studentInstance?.creationDate}"  />

</div>

<div class="fieldcontain ${hasErrors(bean: studentInstance, field: 'lastUpdate', 'error')} required">
	<label for="lastUpdate">
		<g:message code="student.lastUpdate.label" default="Last Update" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="lastUpdate" precision="day"  value="${studentInstance?.lastUpdate}"  />

</div>

<div class="fieldcontain ${hasErrors(bean: studentInstance, field: 'fileNumber', 'error')} required">
	<label for="fileNumber">
		<g:message code="student.fileNumber.label" default="File Number" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="fileNumber" required="" value="${studentInstance?.fileNumber}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: studentInstance, field: 'username', 'error')} required">
	<label for="username">
		<g:message code="student.username.label" default="Username" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="username" required="" value="${studentInstance?.username}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: studentInstance, field: 'password', 'error')} required">
	<label for="password">
		<g:message code="student.password.label" default="Password" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="password" required="" value="${studentInstance?.password}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: studentInstance, field: 'careers', 'error')} ">
	<label for="careers">
		<g:message code="student.careers.label" default="Careers" />
		
	</label>
	<g:select name="careers" from="${com.ues21.Career.list()}" multiple="multiple" optionKey="id" size="5" value="${studentInstance?.careers*.id}" class="many-to-many"/>

</div>

<div class="fieldcontain ${hasErrors(bean: studentInstance, field: 'emails', 'error')} ">
	<label for="emails">
		<g:message code="student.emails.label" default="Emails" />
		
	</label>
	<g:select name="emails" from="${com.ues21.Email.list()}" multiple="multiple" optionKey="id" size="5" value="${studentInstance?.emails*.id}" class="many-to-many"/>

</div>

<div class="fieldcontain ${hasErrors(bean: studentInstance, field: 'phones', 'error')} ">
	<label for="phones">
		<g:message code="student.phones.label" default="Phones" />
		
	</label>
	<g:select name="phones" from="${com.ues21.Phone.list()}" multiple="multiple" optionKey="id" size="5" value="${studentInstance?.phones*.id}" class="many-to-many"/>

</div>

