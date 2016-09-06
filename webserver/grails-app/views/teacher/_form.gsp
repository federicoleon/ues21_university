<%@ page import="com.ues21.Teacher" %>



<div class="fieldcontain ${hasErrors(bean: teacherInstance, field: 'firstName', 'error')} required">
	<label for="firstName">
		<g:message code="teacher.firstName.label" default="First Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="firstName" required="" value="${teacherInstance?.firstName}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: teacherInstance, field: 'lastName', 'error')} required">
	<label for="lastName">
		<g:message code="teacher.lastName.label" default="Last Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="lastName" required="" value="${teacherInstance?.lastName}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: teacherInstance, field: 'identification', 'error')} required">
	<label for="identification">
		<g:message code="teacher.identification.label" default="Identification" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="identification" name="identification.id" from="${com.ues21.Identification.list()}" optionKey="id" required="" value="${teacherInstance?.identification?.id}" class="many-to-one"/>

</div>

<div class="fieldcontain ${hasErrors(bean: teacherInstance, field: 'creationDate', 'error')} required">
	<label for="creationDate">
		<g:message code="teacher.creationDate.label" default="Creation Date" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="creationDate" precision="day"  value="${teacherInstance?.creationDate}"  />

</div>

<div class="fieldcontain ${hasErrors(bean: teacherInstance, field: 'lastUpdate', 'error')} required">
	<label for="lastUpdate">
		<g:message code="teacher.lastUpdate.label" default="Last Update" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="lastUpdate" precision="day"  value="${teacherInstance?.lastUpdate}"  />

</div>

<div class="fieldcontain ${hasErrors(bean: teacherInstance, field: 'fileNumber', 'error')} required">
	<label for="fileNumber">
		<g:message code="teacher.fileNumber.label" default="File Number" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="fileNumber" required="" value="${teacherInstance?.fileNumber}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: teacherInstance, field: 'username', 'error')} required">
	<label for="username">
		<g:message code="teacher.username.label" default="Username" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="username" required="" value="${teacherInstance?.username}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: teacherInstance, field: 'password', 'error')} required">
	<label for="password">
		<g:message code="teacher.password.label" default="Password" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="password" required="" value="${teacherInstance?.password}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: teacherInstance, field: 'emails', 'error')} ">
	<label for="emails">
		<g:message code="teacher.emails.label" default="Emails" />
		
	</label>
	<g:select name="emails" from="${com.ues21.Email.list()}" multiple="multiple" optionKey="id" size="5" value="${teacherInstance?.emails*.id}" class="many-to-many"/>

</div>

<div class="fieldcontain ${hasErrors(bean: teacherInstance, field: 'phones', 'error')} ">
	<label for="phones">
		<g:message code="teacher.phones.label" default="Phones" />
		
	</label>
	<g:select name="phones" from="${com.ues21.Phone.list()}" multiple="multiple" optionKey="id" size="5" value="${teacherInstance?.phones*.id}" class="many-to-many"/>

</div>

