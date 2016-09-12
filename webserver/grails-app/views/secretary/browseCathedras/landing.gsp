<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="management"/>
        <title>Listado de cátedras</title>
    </head>
    <body>
        <div id="teacher-list-cathedras-form-container">
            <h1>Listado de cátedras</h1>
            <table>
                <tr>
                    <td>Materia</td>
                    <td>Sede</td>
                    <td>Edificio</td>
                    <td>Aula</td>
                </tr>
                <g:each in="${cathedras}" var="cathedra">
                    <tr>
                        <td>${cathedra.subject_name}</td>
                        <td>${cathedra.center}</td>
                        <td>${cathedra.building}</td>
                        <td>${cathedra.classroom}</td>
                    </tr>
                </g:each>
            </table>
        </div>
    </body>
</html>