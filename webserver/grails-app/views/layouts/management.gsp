<!DOCTYPE html>
<!--[if lt IE 7 ]> <html lang="en" class="no-js ie6"> <![endif]-->
<!--[if IE 7 ]>    <html lang="en" class="no-js ie7"> <![endif]-->
<!--[if IE 8 ]>    <html lang="en" class="no-js ie8"> <![endif]-->
<!--[if IE 9 ]>    <html lang="en" class="no-js ie9"> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!--><html lang="en" class="no-js"><!--<![endif]-->
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <title><g:layoutTitle default="Autogestión"/></title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="shortcut icon" href="${assetPath(src: 'favicon.ico')}" type="image/x-icon">
        <link rel="apple-touch-icon" href="${assetPath(src: 'apple-touch-icon.png')}">
        <link rel="apple-touch-icon" sizes="114x114" href="${assetPath(src: 'apple-touch-icon-retina.png')}">
        <g:javascript library="jquery" plugin="jquery"/>
        <asset:stylesheet src="main.css"/>
        <asset:javascript src="main.js"/>
        <asset:javascript src="ga.js"/>
        <g:layoutHead/>
    </head>
    <body>
        <div id="main-container">
            <div id="main-menu">
                <g:if test="${session.person?.getRole() == 'ROLE_STUDENT'}">
                    <g:render template="/management/student/menu"/>
                </g:if>
                <g:elseif test="${session.person?.getRole() == 'ROLE_TEACHER'}">
                    <g:render template="/management/teacher/menu"/>
                </g:elseif>
                <g:elseif test="${session.person?.getRole() == 'ROLE_SECRETARY'}">
                    <g:render template="/management/secretary/menu"/>
                </g:elseif>
            </div>
            <div id="main-body">
                <g:layoutBody/>
            </div>
            <div id="main-footer">
                <div id="footer-copyright">
                    <span>Copyright ${new Date()[Calendar.YEAR]} - Universidad Siglo 22. Todos los derechos reservados.</span>
                </div>
            </div>
        </div>
    </body>
</html>
