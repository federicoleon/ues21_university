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
                        <g:submitButton name="findAbsences" class="button btn-blue" value="Buscar inasistencias" />
                    </g:form>
                </div>
            </g:if>
            <g:if test="${students == null}">
                <div class="info">
                    <span>No se encontraron alumnos registrados en la carrera seleccionada</span>
                </div>
            </g:if>

            <g:if test="${absences}">
                <div id="absences-displayer">
                    <table>
                        <tr class="tbl-title">
                            <td>Materia</td>
                            <td>Inasistencias</td>
                            <td>Máximo permitido</td>
                            <td>Fecha</td>
                        </tr>
                        <g:each in="${absences}" var="absence" status="i">
                            <tr class="${(i%2 == 0) ? 'row-low' : 'row-high'}">
                                <td class="c-left">${absence.key}</td>
                                <td>${absence.value.absences.size()}</td>
                                <td>${absence.value.max_permitted}</td>
                                <td>
                                    <g:each in="${absence.value.absences}" var="aux" status="j">
                                        ${aux}<br>
                                    </g:each>
                                </td>
                            </tr>
                        </g:each>
                    </table>
                </div>
            </g:if>
            <g:if test="${absences == null}">
                <div class="success">
                    <span>El alumno seleccionado no tiene inasistencias registradas.</span>
                </div>
            </g:if>
            
        </div>
    </body>
</html>