<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="management"/>
        <title>Registrar alumno en comisión</title>

        <script>
            ga('set', 'page', '/secretary/register-student-cathedra');
            ga('send', 'pageview');
        </script>
    </head>
    <body>
        <div id="teacher-list-cathedras-form-container">
            <h1>Registrar alumno en comisión</h1>

            <div id="career-selector">
                <g:form name="careerSelector">
                    <label for="careerId">Carrera:</label>
                    <g:select name="careerId" from="${careers}" optionKey="id" optionValue="name"/>
                    <g:submitButton name="findStudents" class="button btn-blue" value="Buscar alumnos" />
                </g:form>
            </div>

            <g:if test="${students}">
                <div id="student-selector">
                    <g:form name="studentSelector">
                        <label for="studentId">Alumno:</label>
                        <g:select name="studentId" from="${students}" optionKey="id" optionValue="full_name"/>
                        <g:submitButton name="findAvailableCathedras" class="button btn-blue" value="Buscar comisiones" />
                    </g:form>
                </div>
            </g:if>
            <g:if test="${students == null}">
                <div class="info">
                    <span>No se encontraron alumnos registrados en la carrera seleccionada.</span>
                </div>
            </g:if>

            <g:if test="${errorMessage}">
                <div class="event-message-container ${errorClass}">
                    <span>${errorMessage}</span>
                </div>
            </g:if>

            <g:if test="${cathedras}">
                <div id="cathedra-displayer">
                    <g:form name="studentCathedraRegistration">
                        <table id="register-student-cathedras">
                            <tr class="tbl-title">
                                <td>Materia</td>
                                <td>Año</td>
                                <td>Semestre</td>
                                <td>Sede</td>
                                <td>Edificio</td>
                                <td>Aula</td>
                                <td>Inscripción</td>
                            </tr>
                            <g:each in="${cathedras}" var="cathedra" status="i">
                                <tr class="${(i%2 == 0) ? 'row-low' : 'row-high'}">
                                    <td class="c-left">${cathedra.subject_name}</td>
                                    <td>${cathedra.subject_academic_year}</td>
                                    <td>${cathedra.subject_semester}</td>
                                    <td>${cathedra.center}</td>
                                    <td>${cathedra.building}</td>
                                    <td>${cathedra.classroom}</td>
                                    <td><g:checkBox name="cat_selection" value="${cathedra.id}" checked="false" /></td>
                                </tr>
                            </g:each>
                        </table>

                        <div id="reg-student-cathedras-actions">
                            <g:submitButton name="confirmRegistration" class="button btn-green" value="Inscribir alumno en comisiones" />
                        </div>
                    </g:form>

                </div>
            </g:if>
        </div>
    </body>
</html>