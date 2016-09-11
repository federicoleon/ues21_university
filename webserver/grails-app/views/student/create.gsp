<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="management">
		<g:set var="entityName" value="${message(code: 'student.label', default: 'Student')}" />
		<title><g:message code="student.register.label" args="[entityName]" /></title>
	</head>
	<body>
		<div id="create-student" class="content scaffold-create" role="main">
			<h1><g:message code="student.register.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<g:hasErrors bean="${studentInstance}">
			<ul class="errors" role="alert">
				<g:eachError bean="${studentInstance}" var="error">
				<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
				</g:eachError>
			</ul>
			</g:hasErrors>
			<g:form url="[resource:studentInstance, action:'save']" >
				<fieldset class="form">
					<g:render template="form"/>
				</fieldset>
				<fieldset class="buttons">
					<g:submitButton name="create" class="save" value="${message(code: 'general.register', default: 'Crear')}" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
