<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="management"/>
        <title>Modificar contraseña</title>

        <script>
            ga('set', 'page', '/login/change_password');
            ga('send', 'pageview');
        </script>
    </head>
    <body>
        <div id="login-container">
            <h1>Modificar contraseña</h1>

            <div class="main-message">
                <g:if test="${isUnlockingAccount}">
                    <span>Ya estas a un paso! Ingresa tu nueva contraseña en el formulario que se muestra a continuación.</span>
                </g:if><g:else>
                    <span>Puedes modificar tu contraseña en cualquier momento. La misma se actualiza al instante y se sincroniza con todos los servicios de la UES22.</span>
                </g:else>
            </div>

            <g:form name="changePasswordForm" controller="login" action="changePassword">
                <fieldset>
                    <ol>
                        <g:if test="${!isUnlockingAccount}">
                            <div class="form-row">
                                <label for="password" class="required">
                                    <g:passwordField name="password" tabindex="1" placeholder="Contraseña actual" />
                                </label>
                            </div>
                        </g:if>

                        <div class="form-row">
                            <label for="newPassword" class="required">
                                <g:passwordField name="newPassword" tabindex="2" placeholder="Nueva contraseña" />
                            </label>
                        </div>

                        <div class="form-row">
                            <label for="confirmPassword" class="required">
                                <g:passwordField name="confirmPassword" tabindex="3" placeholder="Confirmar contraseña" />
                            </label>
                        </div>

                        <g:hasErrors bean="${user}" field="password">
                            <g:eachError bean="${user}" field="password">
                                <div class="error">
                                    <span><g:message error="${it}"/></span>
                                </div>
                            </g:eachError>
                        </g:hasErrors>

                        <li class="form-row">
                            <input type="submit" value="Modificar contraseña" id="changePasswordButton" class="button btn-green" tabindex="4" />
                        </li>
                    </ol>
            </g:form>
        </div>
    </body>
</html>
