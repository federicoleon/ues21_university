<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="management"/>
        <title>Modificar contraseña</title>

        <script>
            ga('set', 'page', '/account_recovery/new_password');
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

            <br>

            <g:form name="changePasswordForm" controller="login" action="newPassword">
                <fieldset>
                    <ol>
                        <h3>Bienvenido ${user?.username}</h3>

                        <div class="form-row">
                            <label for="newPassword" class="required">
                                <g:passwordField name="newPassword" tabindex="1" placeholder="Nueva contraseña" />
                            </label>
                        </div>

                        <div class="form-row">
                            <label for="confirmPassword" class="required">
                                <g:passwordField name="confirmPassword" tabindex="2" placeholder="Confirmar contraseña" />
                            </label>
                        </div>

                        <g:hasErrors bean="${user}" field="password">
                            <g:eachError bean="${user}" field="password">
                                <div class="error">
                                    <span><g:message error="${it}"/></span>
                                </div>
                            </g:eachError>
                        </g:hasErrors>

                        <g:hiddenField name="token" value="${tokenId}" />

                        <li class="form-row">
                            <input type="submit" value="Modificar contraseña" id="changePasswordButton" class="button btn-green" tabindex="3" />
                        </li>
                    </ol>
            </g:form>
        </div>
    </body>
</html>
