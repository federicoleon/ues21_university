
<%@ page import="com.ues21.Teacher" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="management">
		<g:set var="entityName" value="${message(code: 'teacher.label', default: 'Teacher')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-teacher" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-teacher" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list teacher">
			
				<g:if test="${teacherInstance?.firstName}">
				<li class="fieldcontain">
					<span id="firstName-label" class="property-label"><g:message code="teacher.firstName.label" default="First Name" /></span>
					
						<span class="property-value" aria-labelledby="firstName-label"><g:fieldValue bean="${teacherInstance}" field="firstName"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${teacherInstance?.lastName}">
				<li class="fieldcontain">
					<span id="lastName-label" class="property-label"><g:message code="teacher.lastName.label" default="Last Name" /></span>
					
						<span class="property-value" aria-labelledby="lastName-label"><g:fieldValue bean="${teacherInstance}" field="lastName"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${teacherInstance?.identification}">
				<li class="fieldcontain">
					<span id="identification-label" class="property-label"><g:message code="teacher.identification.label" default="Identification" /></span>
					
						<span class="property-value" aria-labelledby="identification-label"><g:link controller="identification" action="show" id="${teacherInstance?.identification?.id}">${teacherInstance?.identification?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${teacherInstance?.creationDate}">
				<li class="fieldcontain">
					<span id="creationDate-label" class="property-label"><g:message code="teacher.creationDate.label" default="Creation Date" /></span>
					
						<span class="property-value" aria-labelledby="creationDate-label"><g:formatDate date="${teacherInstance?.creationDate}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${teacherInstance?.lastUpdate}">
				<li class="fieldcontain">
					<span id="lastUpdate-label" class="property-label"><g:message code="teacher.lastUpdate.label" default="Last Update" /></span>
					
						<span class="property-value" aria-labelledby="lastUpdate-label"><g:formatDate date="${teacherInstance?.lastUpdate}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${teacherInstance?.fileNumber}">
				<li class="fieldcontain">
					<span id="fileNumber-label" class="property-label"><g:message code="teacher.fileNumber.label" default="File Number" /></span>
					
						<span class="property-value" aria-labelledby="fileNumber-label"><g:fieldValue bean="${teacherInstance}" field="fileNumber"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${teacherInstance?.username}">
				<li class="fieldcontain">
					<span id="username-label" class="property-label"><g:message code="teacher.username.label" default="Username" /></span>
					
						<span class="property-value" aria-labelledby="username-label"><g:fieldValue bean="${teacherInstance}" field="username"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${teacherInstance?.password}">
				<li class="fieldcontain">
					<span id="password-label" class="property-label"><g:message code="teacher.password.label" default="Password" /></span>
					
						<span class="property-value" aria-labelledby="password-label"><g:fieldValue bean="${teacherInstance}" field="password"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${teacherInstance?.emails}">
				<li class="fieldcontain">
					<span id="emails-label" class="property-label"><g:message code="teacher.emails.label" default="Emails" /></span>
					
						<g:each in="${teacherInstance.emails}" var="e">
						<span class="property-value" aria-labelledby="emails-label"><g:link controller="email" action="show" id="${e.id}">${e?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
				<g:if test="${teacherInstance?.phones}">
				<li class="fieldcontain">
					<span id="phones-label" class="property-label"><g:message code="teacher.phones.label" default="Phones" /></span>
					
						<g:each in="${teacherInstance.phones}" var="p">
						<span class="property-value" aria-labelledby="phones-label"><g:link controller="phone" action="show" id="${p.id}">${p?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
			</ol>
			<g:form url="[resource:teacherInstance, action:'delete']" method="DELETE">
				<fieldset class="buttons">
					<g:link class="edit" action="edit" resource="${teacherInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
