<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="management"/>
        <title>Inscribir alumno en carrera</title>

        <script>
            ga('set', 'page', '/secretary/register-in-career');
            ga('send', 'pageview');
        </script>
    </head>
    <body>
        <div id="test-periods-form-container">
            <h1>Inscribir alumno en carrera</h1>

            <g:if test="${students}">
            <div id="student-selector">
                <g:form name="studentSelector">
                    <label for="studentId">Alumno:</label>
                    <g:select name="studentId" from="${students}" optionKey="id" optionValue="full_name"/>
                    <g:submitButton name="findAvailableCareers" class="button btn-blue" value="Buscar carreras disponibles" />
                </g:form>
            </div>
            </g:if>
            <g:else>
                <div class="info">
                    <span>No se encontraron alumnos registrados.</span>
                </div>
            </g:else>

            <g:if test="${careers}">
                <div id="career-selector">
                    <g:form name="careerSelector">
                        <label for="careerId">Carrera:</label>
                        <g:select name="careerId" from="${careers}" optionKey="id" optionValue="name"/>
                        <g:submitButton name="confirmRegistration" class="button btn-blue" value="Confirmar registro" />
                    </g:form>
                </div>
            </g:if>


            <g:if test="${careers == null}">
                <div class="info">
                    <span>No se encontraron carreras disponibles para inscripción.</span>
                </div>
            </g:if>

            <g:if test="${errorMessage}">
                <div class="event-message-container ${errorClass}">
                    <span>${errorMessage}</span>
                </div>
            </g:if>
        </div>
    </body>
</html>