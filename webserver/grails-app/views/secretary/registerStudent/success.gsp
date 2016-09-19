<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="management"/>
        <title>Registrar nuevo alumno</title>
    </head>
    <body>
        <h1>Alumno registrado correctamente.</h1>
        
        <div class="info">
            <span>Puede imprimir su constancia de inscripción en el link que se encuentra a continuación.</span>
        </div>

        <div class="qr-code">
            <qrcode:text>${vcardData}</qrcode:text>
        </div>

        <div id="downloader-container">
            <g:link controller="management" action="main">Finalizar</g:link>
        </div>
    </body>
</html>
