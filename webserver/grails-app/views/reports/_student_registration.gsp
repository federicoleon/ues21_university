<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
    <head></head>
    <body>
        <div id="report-container">
            <div id="header">
                <div id="title">
                    <h1>Universidad Empresarial Siglo 22</h1>
                    <h2>Secretaría de Asuntos Estudiantiles</h2>
                    <h3>Comprobante de inscripción</h3>
                </div>
            </div>

            <div id="body">
                <table>
                    <tr>
                        <td>Legajo</td>
                        <td>Carrera</td>
                        <td>Alumno</td>
                        <td>Ingreso</td>
                    </tr>
                    <tr>
                        <td>${fileNumber}</td>
                        <td>${careers[0].name}</td>
                        <td>${fullName}</td>
                        <td>${creationDate}</td>
                    </tr>
                </table>
            </div>
            <div id="qr-code">
                
            </div>
        </div>
    </body>
</html>