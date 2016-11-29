<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="management"/>
        <title>Registrar nuevo alumno</title>

        <script>
            ga('set', 'page', '/secretary/register-student');
            ga('send', 'pageview');
        </script>
    </head>
    <body>
        <div id="form-container">
            <h1>Registrar nuevo alumno</h1>
            <g:form name="registerStudent">

            <div class="form-row">
                <label>Nombres:</label>
                <g:textField name="firstName" value="${student?.firstName}" class="${(student?.errors?.getFieldError('firstName')) ? 'error-input-text' : ''}"/><br/>
                <g:hasErrors bean="${student}" field="firstName">
                    <g:eachError bean="${student}" field="firstName">
                        <div class="error-message">
                            <span style="color: red;"><g:message error="${it}"/></span>
                        </div>
                    </g:eachError>
                </g:hasErrors>
            </div>

            <div class="form-row">
                <label>Apellidos:</label>
                <g:textField name="lastName" value="${student?.lastName}" class="${(student?.errors?.getFieldError('lastName')) ? 'error-input-text' : ''}"/><br/>
                <g:hasErrors bean="${student}" field="lastName">
                    <g:eachError bean="${student}" field="lastName">
                        <div class="error-message">
                            <span style="color: red;"><g:message error="${it}"/></span>
                        </div>
                    </g:eachError>
                </g:hasErrors>
            </div>

            <div class="form-row">
                <label>Tipo de documento:</label>
                <g:select name="idType" from="${idTypes}" optionKey="id" optionValue="type"/>
                <g:hasErrors bean="${student}" field="identification.type">
                    <g:eachError bean="${student}" field="identification.type">
                        <p style="color: red;"><g:message error="Revisa el tipo de documento"/></p>
                    </g:eachError>
                </g:hasErrors>
            </div>

            <div class="form-row">
                <label>Número de documento:</label>
                <g:textField name="idNumber" value="${student?.identification?.number}" class="${(student?.errors?.getFieldError('identification.number')) ? 'error-input-text' : ''}"/><br/>
                <g:hasErrors bean="${student}" field="identification.number">
                    <g:eachError bean="${student}" field="identification.number">
                        <div class="error-message">
                            <span style="color: red;"><g:message error="${it}"/></span>
                        </div>
                    </g:eachError>
                </g:hasErrors>
            </div>
            
            <div class="form-row">
                <label>E-mail:</label>
                <g:textField name="email" value="${student?.emails?.first()?.address}" class="${(student?.errors?.getFieldError('emails[0].address')) ? 'error-input-text' : ''}"/><br/>
                <g:hasErrors bean="${student}" field="emails[0].address">
                    <g:eachError bean="${student}" field="emails[0].address">
                        <div class="error-message">
                            <span style="color: red;"><g:message error="${it}"/></span>
                        </div>
                    </g:eachError>
                </g:hasErrors>
            </div>

            <div class="form-row">
                <label>Tipo de teléfono:</label>
                <g:select name="phoneType" from="${phoneTypes}" optionKey="id" optionValue="type"/>
                 <br>
                <label>Código de area:</label>
                <g:textField name="phoneAreaCode" value="${student?.phones?.first()?.areaCode}" class="${(student?.errors?.getFieldError('phones[0].areaCode')) ? 'error-input-text' : ''}"/><br/>
                <g:hasErrors bean="${student}" field="phones[0].areaCode">
                    <g:eachError bean="${student}" field="phones[0].areaCode">
                        <div class="error-message">
                            <span style="color: red;"><g:message error="${it}"/></span>
                        </div>
                    </g:eachError>
                </g:hasErrors>
            </div>

            <div class="form-row">
                <label>Número de teléfono:</label>
                <g:textField name="phoneNumber" value="${student?.phones?.first()?.number}" class="${(student?.errors?.getFieldError('phones[0].number')) ? 'error-input-text' : ''}"/><br/>
                <g:hasErrors bean="${student}" field="phones[0].number">
                    <g:eachError bean="${student}" field="phones[0].number">
                        <div class="error-message">
                            <span style="color: red;"><g:message error="${it}"/></span>
                        </div>
                    </g:eachError>
                </g:hasErrors>
            </div>

            <div class="form-row">
                <g:submitButton name="register" class="button btn-green" value="Registrar" />
            </div>

            </g:form>
        </div>
    </body>
</html>
