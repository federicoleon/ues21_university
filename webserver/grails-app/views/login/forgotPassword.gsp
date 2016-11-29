<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="management"/>
        <title>Recuperar contraseña</title>

        <script>
            ga('set', 'page', '/login/forgot_password');
            ga('send', 'pageview');
        </script>
    </head>
    <body>
        <div id="login-container">
            <h1>Recuperar contraseña</h1>

            <div class="main-message"><span>Si olvidaste o perdiste tu contraseña puedes completar el formulario que se muestra a continuación y te enviaremos un link para que puedas recuperarla de forma rápida y segura.</span></div>
            <g:form name="forgotPasswordForm" controller="login" action="forgotPassword">
                <fieldset>
                    <ol>
                        <li class="form-row">
                            <label for="username" class="required">
                                <g:textField name="username" value="${params.username}" tabindex="1" placeholder="Usuario" />
                            </label>
                        </li>

                        <li class="form-row">
                            <input type="submit" value="Recuperar contraseña" id="loginButton" class="button btn-green" tabindex="3" />
                        </li>
                    </ol>
            </g:form>
        </div>
    </body>
</html>
