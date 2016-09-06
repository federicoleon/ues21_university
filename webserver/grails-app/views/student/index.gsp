
<%@ page import="com.ues21.Student" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="management">
		<g:set var="entityName" value="${message(code: 'student.label', default: 'Student')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-student" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-student" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
			<thead>
					<tr>
					
						<g:sortableColumn property="firstName" title="${message(code: 'student.firstName.label', default: 'First Name')}" />
					
						<g:sortableColumn property="lastName" title="${message(code: 'student.lastName.label', default: 'Last Name')}" />
					
						<th><g:message code="student.identification.label" default="Identification" /></th>
					
						<g:sortableColumn property="creationDate" title="${message(code: 'student.creationDate.label', default: 'Creation Date')}" />
					
						<g:sortableColumn property="lastUpdate" title="${message(code: 'student.lastUpdate.label', default: 'Last Update')}" />
					
						<g:sortableColumn property="fileNumber" title="${message(code: 'student.fileNumber.label', default: 'File Number')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${studentInstanceList}" status="i" var="studentInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${studentInstance.id}">${fieldValue(bean: studentInstance, field: "firstName")}</g:link></td>
					
						<td>${fieldValue(bean: studentInstance, field: "lastName")}</td>
					
						<td>${fieldValue(bean: studentInstance, field: "identification")}</td>
					
						<td><g:formatDate date="${studentInstance.creationDate}" /></td>
					
						<td><g:formatDate date="${studentInstance.lastUpdate}" /></td>
					
						<td>${fieldValue(bean: studentInstance, field: "fileNumber")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${studentInstanceCount ?: 0}" />
			</div>
		</div>
	</body>
</html>
