<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="management"/>
        <title>Acceso a autogestión</title>
    </head>
    <body>
        <div id="login-container">
            <h1>Ingresa a tu autogestión</h1>
            <g:form name="loginForm" action="login">
                <fieldset>
                    <ol>
                        <li class="form-row">
                            <label for="quantity" class="required">
                                <g:textField name="username" value="" tabindex="1" placeholder="Usuario" />
                            </label>
                        </li>
                        <li class="form-row">
                            <label for="password" class="required">
                                <g:passwordField name="password" value="" tabindex="2" placeholder="Contraseña"/>
                            </label>
                        </li>

                        <g:if test="${invalidLoginData}">
                            <div class="error">
                                <span class="error-msg">Usuario o contraseña incorrectos</span>
                            </div>
                        </g:if>

                        <li class="form-row">
                            <input type="submit" value="Ingresar" id="loginButton" class="button btn-green" tabindex="3" />
                        </li>
                    </ol>
            </g:form>
        </div>
    </body>
</html>
