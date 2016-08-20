<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="management"/>
        <title>Autogestión</title>
    </head>
    <body>
        <div id="login-container">
            <h1>Login</h1>
            <g:form name="loginForm" action="login">
                <fieldset>
                    <ol>
                        <li class="form-row">
                            <label for="quantity" class="required">
                                <span class="field-label">Usuario:</span>
                                <g:textField name="username" value="" tabindex="1"/>
                            </label>
                        </li>
                        <li class="form-row">
                            <label for="password" class="required">
                                <span class="field-label">Constraseña:</span>
                                <g:passwordField name="password" value="" tabindex="2"/>
                            </label>
                        </li>

                        <g:renderErrors bean="${user}" as="list" field="username"/>

                        <li class="form-row">
                            <input type="submit" value="Ingresar" class="button btn-green" tabindex="3" />
                        </li>
                    </ol>
            </g:form>
        </div>
    </body>
</html>
