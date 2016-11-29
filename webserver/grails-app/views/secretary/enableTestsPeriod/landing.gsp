<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="management"/>
        <title>Habilitar periodo de examen</title>
        <script>
            ga('set', 'page', '/secretary/enable-test-period');
            ga('send', 'pageview');
        </script>
    </head>
    <body>
        <div id="enable-test-period-form-container">
            <h1>Habilitar periodo de examen</h1>

            <div id="career-selector">
                <g:form name="careerSelector">
                    <label for="careerId">Carrera:</label>
                    <g:select name="careerId" from="${careers}" optionKey="id" optionValue="name"/>
                    <g:submitButton name="findCathedras" class="button btn-blue" value="Buscar periodos disponibles" />
                </g:form>
            </div>

            <g:if test="${errorMessage}">
                <div class="error">
                    <span>${errorMessage}</span>
                </div>
            </g:if>
            <g:else>
                <g:if test="${examPeriod}">
                    <g:form name="examPeriodConfirmation">
                        <div id="exam-period-container">
                            <span>Periodo a habilitar: </span><strong>${examPeriod.name}</strong>
                            <g:hiddenField name="examPeriodId" value="${examPeriod.type}" />
                            <br><br>
                            <g:submitButton name="enablePeriod" class="button btn-green" value="Habilitar mesas" />
                        </div>
                    </g:form>
                </g:if>
            </g:else>
        </div>
    </body>
</html>