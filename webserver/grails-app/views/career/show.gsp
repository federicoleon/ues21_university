
<%@ page import="com.ues21.Career" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="management">
		<g:set var="entityName" value="${message(code: 'career.label', default: 'Career')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-career" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-career" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list career">
			
				<g:if test="${careerInstance?.name}">
				<li class="fieldcontain">
					<span id="name-label" class="property-label"><g:message code="career.name.label" default="Name" /></span>
					
						<span class="property-value" aria-labelledby="name-label"><g:fieldValue bean="${careerInstance}" field="name"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${careerInstance?.plans}">
				<li class="fieldcontain">
					<span id="plans-label" class="property-label"><g:message code="career.plans.label" default="Plans" /></span>
					
						<g:each in="${careerInstance.plans}" var="p">
						<span class="property-value" aria-labelledby="plans-label"><g:link controller="careerPlan" action="show" id="${p.id}">${p?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
			</ol>
			<g:form url="[resource:careerInstance, action:'delete']" method="DELETE">
				<fieldset class="buttons">
					<g:link class="edit" action="edit" resource="${careerInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
