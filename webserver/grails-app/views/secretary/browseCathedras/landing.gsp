<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="management"/>
        <title>Listado de comisiones</title>
    </head>
    <body>
        <div id="list-cathedras-form-container">
            <h1>Listado de comisiones</h1>

            <div id="career-selector">
                <g:form name="careerSelector">
                    <label for="careerId">Carrera:</label>
                    <g:select name="careerId" from="${careers}" optionKey="id" optionValue="name"/>
                    <g:submitButton name="findCathedras" class="button btn-blue" value="Buscar comisiones" />
                </g:form>
            </div>

            <g:if test="${cathedras}">
                <div id="cathedra-displayer">
                    <table>
                        <tr class="tbl-title">
                            <td>Materia</td>
                            <td>Sede</td>
                            <td>Edificio</td>
                            <td>Aula</td>
                        </tr>
                        <g:each in="${cathedras}" var="cathedra" status="i">
                            <tr class="${(i%2 == 0) ? 'row-low' : 'row-high'}">
                                <td class="c-left">${cathedra.subject_name}</td>
                                <td>${cathedra.center}</td>
                                <td>${cathedra.building}</td>
                                <td>${cathedra.classroom}</td>
                            </tr>
                        </g:each>
                    </table>
                </div>
            </g:if>
        </div>
    </body>
</html>