<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="management"/>
        <title>Registrar nuevo alumno</title>

        <script>
            ga('set', 'page', '/register-student');
            ga('send', 'pageview');
        </script>
    </head>
    <body>
        <div id="form-container">
            <h1>Registrar nuevo alumno</h1>
            <g:form name="registerStudent">
                <label>Carrera:</label>
            <g:select name="carreerIds" from="${careers}" optionKey="id" optionValue="name" multiple="true" />
                <br>
                
                <label>Nombres:</label>
                <g:textField name="firstName" value="${student?.firstName}"/><br/>
                <g:hasErrors bean="${student}" field="firstName">
                    <g:eachError bean="${student}" field="firstName">
                        <p style="color: red;"><g:message error="Revisa los nombres ingresados"/></p>
                    </g:eachError>
                </g:hasErrors>

                <label>Apellidos:</label>
                <g:textField name="lastName" value="${student?.lastName}"/><br/>
                <g:hasErrors bean="${student}" field="lastName">
                    <g:eachError bean="${student}" field="lastName">
                        <p style="color: red;"><g:message error="Revisa los apellidos ingresados"/></p>
                    </g:eachError>
                </g:hasErrors>

                <label>Tipo de documento:</label>
                <g:select name="idType" from="${idTypes}" optionKey="id" optionValue="type"/>
                <g:hasErrors bean="${student}" field="identification.type">
                    <g:eachError bean="${student}" field="identification.type">
                        <p style="color: red;"><g:message error="Revisa el tipo de documento"/></p>
                    </g:eachError>
                </g:hasErrors>

                <br>
                <label>Número de documento:</label>
                <g:textField name="idNumber" value="${student?.identification?.number}"/><br/>
                <g:hasErrors bean="${student}" field="identification?.number">
                    <g:eachError bean="${student}" field="identification?.number">
                        <p style="color: red;"><g:message error="Revisa el número de documento"/></p>
                    </g:eachError>
                </g:hasErrors>

                <label>E-mail:</label>
                <g:textField name="email"/><br/>
                <g:hasErrors bean="${student}" field="email">
                    <g:eachError bean="${student}" field="emails?[0]">
                        <p style="color: red;"><g:message error="Revisa el e-mail ingresado"/></p>
                    </g:eachError>
                </g:hasErrors>

                <label>Tipo de teléfono:</label>
                <g:select name="phoneType" from="${phoneTypes}" optionKey="id" optionValue="type"/>
                 <br>
                <label>Código de area:</label>
                <g:textField name="phoneAreaCode"/><br/>

                <label>Número de teléfono:</label>
                <g:textField name="phoneNumber"/><br/>

                <g:submitButton name="register" class="button btn-green" value="Registrar" />
            </g:form>
        </div>
    </body>
</html>
