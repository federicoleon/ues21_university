<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="management"/>
        <title>Acceso a Autogestión</title>

        <script>
            ga('set', 'page', '/login');
            ga('send', 'pageview');
        </script>
    </head>
    <body>
        <div id="login-container">
            <h1>Ingresa a tu autogestión</h1>

            <g:if test="${passwordChanged}">
                <div class="info">
                    <span class="info-msg">Tu contraseña se ha modificado correctamente. Debes volver a iniciar sesión.</span>
                </div>
            </g:if>
            <g:form name="loginForm" controller="login" action="auth">
                <fieldset>
                    <ol>
                        <li class="form-row">
                            <label for="username" class="required">
                                <g:textField name="username" value="${params.username}" tabindex="1" placeholder="Usuario" />
                            </label>
                        </li>
                        <li class="form-row">
                            <label for="password" class="required">
                                <g:passwordField name="password" value="" tabindex="2" placeholder="Contraseña"/>
                            </label>
                        </li>

                        <li class="form-row recover_section">
                            <div class="content-left">
                                <label for='remember_me'>
                                    <input type="checkbox" class="checkbox" name="remember_me" id="remember_me" checked="checked" tabindex="3"/>
                                    <span>Recordarme</span>
                                </label>
                            </div>
                            <div class="content-right">
                                <a tabindex="4" href="${createLink(uri:'/login/forgotPassword.gsp')}">Recuperar contraseña</a>
                            </div>
                        </li>

                        <g:if test="${invalidLoginData}">
                            <div class="error">
                                <span class="error-msg">Usuario o contraseña incorrectos</span>
                            </div>
                        </g:if>

                        <li class="form-row">
                            <input type="submit" value="Ingresar" id="loginButton" class="button btn-green" tabindex="5" />
                        </li>
                    </ol>
            </g:form>
        </div>
    </body>
</html>
