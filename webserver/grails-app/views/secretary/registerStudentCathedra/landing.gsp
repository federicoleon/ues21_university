<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="management"/>
        <title>Registrar alumno en comisión</title>
    </head>
    <body>
        <div id="teacher-list-cathedras-form-container">
            <h1>Registrar alumno en comisión</h1>

            <label>Alumno:</label>
            <g:select name="student" from="${students}" optionKey="id" optionValue="fullName"/>

            <table>
                <tr>
                    <td>Materia</td>
                    <td>Sede</td>
                    <td>Edificio</td>
                    <td>Aula</td>
                    <td>Inscripción</td>
                </tr>
                <g:each in="${cathedras}" var="cathedra">
                    <tr>
                        <td>${cathedra.subject_name}</td>
                        <td>${cathedra.center}</td>
                        <td>${cathedra.building}</td>
                        <td>${cathedra.classroom}</td>
                        <td><g:checkBox name="cathedraS" value="${cathedra.id}" checked="false"/></td>
                    </tr>
                </g:each>
            </table>
        </div>
    </body>
</html>