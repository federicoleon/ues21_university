<!DOCTYPE html>
<!--[if lt IE 7 ]> <html lang="en" class="no-js ie6"> <![endif]-->
<!--[if IE 7 ]>    <html lang="en" class="no-js ie7"> <![endif]-->
<!--[if IE 8 ]>    <html lang="en" class="no-js ie8"> <![endif]-->
<!--[if IE 9 ]>    <html lang="en" class="no-js ie9"> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!--><html lang="en" class="no-js"><!--<![endif]-->
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <title><g:layoutTitle default="Grails"/></title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="shortcut icon" href="${assetPath(src: 'favicon.ico')}" type="image/x-icon">
        <link rel="apple-touch-icon" href="${assetPath(src: 'apple-touch-icon.png')}">
        <link rel="apple-touch-icon" sizes="114x114" href="${assetPath(src: 'apple-touch-icon-retina.png')}">
        <asset:stylesheet src="main.css"/>
        <asset:javascript src="main.js"/>
        <g:layoutHead/>
    </head>
    <body>
        <div id="main-container">
            <div id="main-header">
                <div id="logo">
                    <span id="logo-title">Universidad</span>
                    <span id="logo-subtitle">Siglo 22</span>
                    <span id="logo-description">La evolución en educación</span>
                </div>
                <div id="header-content">
                    <div id="search-bar">
                
                    </div>
                    <div id="header-menu">
                        <ul>
                            <li><g:link controller="management" action="login">Autogestión</g:link></li>
                        </ul>
                    </div>
                </div>
            </div>

            <div id="main-body">
                <div id="main-menu">
                    <ul>
                        <li><g:link controller="main" action="home">Principal</g:link></li>
                        <li><g:link controller="main" action="university">La universidad</g:link></li>
                        <li><g:link controller="main" action="contact">Contacto</g:link></li>
                    </ul>
                </div>
                <div id="main-content">
                    <g:layoutBody/>
                </div>

                <div id="main-footer">
                    <hr>
                    
                    <div id="footer-menu">
                        <ul>
                            <li><g:link controller="main" action="home">Principal</g:link></li>
                            <li><g:link controller="main" action="university">La universidad</g:link></li>
                            <li><g:link controller="main" action="contact">Contacto</g:link></li>
                        </ul>
                    </div>

                    <div id="footer-legals">
                        <span id="footer-copyright">Universidad Siglo 22 © ${params.year}</span>
                        <span class="policy"><a href="#">Política de privacidad</a></span>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
