<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
    <head></head>
    <body>
        <div id="report-container">
            <div id="header">
                <div id="title">
                    <h1>Universidad Empresarial Siglo 22</h1>
                    <h2>Su cuenta ha sido bloquada!</h2>
                </div>
            </div>

            <div id="body">
                <h2>Hola ${firstName}.</h2>
                <span>Se han agotado los intentos de acceso a su cuenta y, por seguridad, la hemos bloqueado para evitar el acceso de terceros.</span>
                <br>
                <span>Para recuperar tu cuenta puedes hacer <a href="${recoverURL}">CLICK AQUÍ</a></span>
            </div>
        </div>
    </body>
</html>
