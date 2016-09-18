<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="management"/>
        <title>Consulta de inscripción a cursado</title>
    </head>
    <body>
        <div id="list-cathedras-form-container">
            <h1>Consulta de inscripción a cursado</h1>

            <div id="career-selector">
                <g:form name="careerSelector">
                    <label for="careerId">Carrera:</label>
                    <g:select name="careerId" from="${careers}" optionKey="id" optionValue="name"/>
                    <g:submitButton name="findCathedras" class="button btn-blue" value="Buscar alumnos" />
                </g:form>
            </div>

            <g:if test="${students}">
                <div id="student-selector">
                    <g:form name="studentSelector">
                        <label for="studentId">Alumno:</label>
                        <g:select name="studentId" from="${students}" optionKey="id" optionValue="full_name"/>
                        <g:submitButton name="findRegistrations" class="button btn-blue" value="Buscar inscripciones" />
                    </g:form>
                </div>
            </g:if>
            <g:if test="${students == null}">
                <div class="info">
                    <span>No se encontraron alumnos registrados en la carrera seleccionada</span>
                </div>
            </g:if>

            <g:if test="${registrations}">
                <div id="registrations-displayer">
                    <table>
                        <tr class="tbl-title">
                            <td>Materia</td>
                            <td>Sede</td>
                            <td>Edificio</td>
                            <td>Aula</td>
                        </tr>
                        <g:each in="${registrations}" var="registration" status="i">
                            <tr class="${(i%2 == 0) ? 'row-low' : 'row-high'}">
                                <td class="c-left">${registration.subject_name}</td>
                                <td>${registration.center}</td>
                                <td>${registration.building}</td>
                                <td>${registration.classroom}</td>
                            </tr>
                        </g:each>
                    </table>
                </div>
            </g:if>
            
        </div>
    </body>
</html>