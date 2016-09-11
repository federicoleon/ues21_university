
<%@ page import="com.ues21.Student" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="management">
		<g:set var="entityName" value="${message(code: 'student.label', default: 'Student')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<div id="show-student" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list student">
			
				<g:if test="${studentInstance?.firstName}">
				<li class="fieldcontain">
					<span id="firstName-label" class="property-label"><g:message code="student.firstName.label" default="First Name" /></span>
					
						<span class="property-value" aria-labelledby="firstName-label"><g:fieldValue bean="${studentInstance}" field="firstName"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${studentInstance?.lastName}">
				<li class="fieldcontain">
					<span id="lastName-label" class="property-label"><g:message code="student.lastName.label" default="Last Name" /></span>
					
						<span class="property-value" aria-labelledby="lastName-label"><g:fieldValue bean="${studentInstance}" field="lastName"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${studentInstance?.identification}">
				<li class="fieldcontain">
					<span id="identification-label" class="property-label"><g:message code="student.identification.label" default="Identification" /></span>
					
						<span class="property-value" aria-labelledby="identification-label"><g:link controller="identification" action="show" id="${studentInstance?.identification?.id}">${studentInstance?.identification?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${studentInstance?.creationDate}">
				<li class="fieldcontain">
					<span id="creationDate-label" class="property-label"><g:message code="student.creationDate.label" default="Creation Date" /></span>
					
						<span class="property-value" aria-labelledby="creationDate-label"><g:formatDate date="${studentInstance?.creationDate}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${studentInstance?.lastUpdate}">
				<li class="fieldcontain">
					<span id="lastUpdate-label" class="property-label"><g:message code="student.lastUpdate.label" default="Last Update" /></span>
					
						<span class="property-value" aria-labelledby="lastUpdate-label"><g:formatDate date="${studentInstance?.lastUpdate}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${studentInstance?.fileNumber}">
				<li class="fieldcontain">
					<span id="fileNumber-label" class="property-label"><g:message code="student.fileNumber.label" default="File Number" /></span>
					
						<span class="property-value" aria-labelledby="fileNumber-label"><g:fieldValue bean="${studentInstance}" field="fileNumber"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${studentInstance?.username}">
				<li class="fieldcontain">
					<span id="username-label" class="property-label"><g:message code="student.username.label" default="Username" /></span>
					
						<span class="property-value" aria-labelledby="username-label"><g:fieldValue bean="${studentInstance}" field="username"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${studentInstance?.password}">
				<li class="fieldcontain">
					<span id="password-label" class="property-label"><g:message code="student.password.label" default="Password" /></span>
					
						<span class="property-value" aria-labelledby="password-label"><g:fieldValue bean="${studentInstance}" field="password"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${studentInstance?.careers}">
				<li class="fieldcontain">
					<span id="careers-label" class="property-label"><g:message code="student.careers.label" default="Careers" /></span>
					
						<g:each in="${studentInstance.careers}" var="c">
						<span class="property-value" aria-labelledby="careers-label"><g:link controller="career" action="show" id="${c.id}">${c?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
				<g:if test="${studentInstance?.emails}">
				<li class="fieldcontain">
					<span id="emails-label" class="property-label"><g:message code="student.emails.label" default="Emails" /></span>
					
						<g:each in="${studentInstance.emails}" var="e">
						<span class="property-value" aria-labelledby="emails-label"><g:link controller="email" action="show" id="${e.id}">${e?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
				<g:if test="${studentInstance?.phones}">
				<li class="fieldcontain">
					<span id="phones-label" class="property-label"><g:message code="student.phones.label" default="Phones" /></span>
					
						<g:each in="${studentInstance.phones}" var="p">
						<span class="property-value" aria-labelledby="phones-label"><g:link controller="phone" action="show" id="${p.id}">${p?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
			</ol>
			<g:form url="[resource:studentInstance, action:'delete']" method="DELETE">
				<fieldset class="buttons">
					<g:link class="edit" action="edit" resource="${studentInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
