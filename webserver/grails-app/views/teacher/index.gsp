
<%@ page import="com.ues21.Teacher" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="management">
		<g:set var="entityName" value="${message(code: 'teacher.label', default: 'Teacher')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-teacher" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-teacher" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
			<thead>
					<tr>
					
						<g:sortableColumn property="firstName" title="${message(code: 'teacher.firstName.label', default: 'First Name')}" />
					
						<g:sortableColumn property="lastName" title="${message(code: 'teacher.lastName.label', default: 'Last Name')}" />
					
						<th><g:message code="teacher.identification.label" default="Identification" /></th>
					
						<g:sortableColumn property="creationDate" title="${message(code: 'teacher.creationDate.label', default: 'Creation Date')}" />
					
						<g:sortableColumn property="lastUpdate" title="${message(code: 'teacher.lastUpdate.label', default: 'Last Update')}" />
					
						<g:sortableColumn property="fileNumber" title="${message(code: 'teacher.fileNumber.label', default: 'File Number')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${teacherInstanceList}" status="i" var="teacherInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${teacherInstance.id}">${fieldValue(bean: teacherInstance, field: "firstName")}</g:link></td>
					
						<td>${fieldValue(bean: teacherInstance, field: "lastName")}</td>
					
						<td>${fieldValue(bean: teacherInstance, field: "identification")}</td>
					
						<td><g:formatDate date="${teacherInstance.creationDate}" /></td>
					
						<td><g:formatDate date="${teacherInstance.lastUpdate}" /></td>
					
						<td>${fieldValue(bean: teacherInstance, field: "fileNumber")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${teacherInstanceCount ?: 0}" />
			</div>
		</div>
	</body>
</html>
