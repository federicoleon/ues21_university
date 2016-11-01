<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="management"/>
        <title>Correlativas por carrera</title>

        <script>
            ga('set', 'page', '/browse-correlatives');
            ga('send', 'pageview');
        </script>
    </head>
    <body>
        <div id="correlatives-list-selector-container">
            <h1>Correlativas por carrera</h1>

            <div id="career-selector">
                <g:form name="careerSelector">
                    <label for="careerId">Carrera:</label>
                    <g:select name="careerId" from="${careers}" optionKey="id" optionValue="name"/>
                    <g:submitButton name="findCareer" class="button btn-blue" value="Buscar correlativas" />
                </g:form>
            </div>

            <g:if test="${subjects}">
                <div id="correlative-displayer">
                    <table>
                        <tr class="tbl-title">
                            <td colspan="2">Materias</td>
                            <td colspan="2">Correlativas</td>
                        </tr>
                        <tr class="tbl-subtitle">
                            <td class="c-bold">Semestre</td>
                            <td class="c-bold">Materia</td>
                            <td class="c-bold">Semestre</td>
                            <td class="c-bold">Materia</td>
                        </tr>

                        <g:each in="${subjects}" var="subject" status="i">
                            <g:if test="${subject.correlatives}">
                                <tr class="${(i%2 == 0) ? 'row-low' : 'row-high'}">
                                <td>${subject.semester}</td>
                                <td class="c-left">${subject.name}</td>
                                <g:each in="${subject.correlatives}" var="correlative" status="j">
                                    <g:if test="${j == 0}">
                                        <td>${correlative.semester}</td>
                                        <td class="c-left">${correlative.name}</td>
                                    </g:if>
                                    <g:else>
                                        <tr>
                                            <td></td>
                                            <td></td>
                                            <td>${correlative.semester}</td>
                                            <td class="c-left">${correlative.name}</td>
                                        </tr>
                                    </g:else>
                                </g:each>
                            </tr>
                            </g:if>
                        </g:each>
                    </table>
                </div>
            </g:if>
        </div>
    </body>
</html>