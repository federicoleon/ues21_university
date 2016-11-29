<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="management"/>
        <title>Inscribir a mesa de examen</title>

        <script>
            ga('set', 'page', '/secretary/register-in-exam');
            ga('send', 'pageview');
        </script>
    </head>
    <body>
        <div id="test-periods-form-container">
            <h1>Inscribir a mesa de examen</h1>

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
                        <g:submitButton name="findAvailableExams" class="button btn-blue" value="Buscar mesas de examen" />
                    </g:form>
                </div>
            </g:if>
            <g:if test="${exams == null}">
                <div class="info">
                    <span>No se encontraron mesas de examen habilitadas.</span>
                </div>
            </g:if>

            <g:if test="${errorMessage}">
                <div class="event-message-container ${errorClass}">
                    <span>${errorMessage}</span>
                </div>
            </g:if>

            <g:if test="${exams}">
                <div id="cathedra-displayer">
                    <g:form name="examRegistrationFoerm">
                        <table id="register-student-exam">
                            <tr class="tbl-title">
                                <td>Materia</td>
                                <td>Examen</td>
                                <td>Inscripción</td>
                            </tr>
                            <g:each in="${exams}" var="exam" status="i">
                                <tr class="${(i%2 == 0) ? 'row-low' : 'row-high'}">
                                    <td class="c-left">${exam.subject}</td>
                                    <td>${exam.exam_type}</td>
                                    <td><g:checkBox name="exam_selection" value="${exam.exam_id}" checked="false" /></td>
                                </tr>
                            </g:each>
                        </table>

                        <div id="reg-student-exam-actions">
                            <g:submitButton name="confirmRegistration" class="button btn-green" value="Inscribir alumno en mesas seleccionadas" />
                        </div>
                    </g:form>

                </div>
            </g:if>
        </div>
    </body>
</html>