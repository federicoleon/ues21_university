<a id="touch-menu" class="mobile-menu" href="#"><i class="icon-reorder"></i>Menu principal</a>

<nav>
    <ul class="menu">
        <li><g:link controller="management" action="main">Principal</g:link></li>
        <li><a href="#"><i class="icon-home"></i>Gestión académica</a>
            <ul class="sub-menu">
                <li><a href="#">Inscribir a comisión</a></li>
                <li><a href="#">Inscribir a mesa de examen</a></li>
            </ul>
        </li>
        <li><a  href="#"><i class="icon-camera"></i>Consultas</a>
            <ul class="sub-menu">
                <li><a href="#">Semestre</a>
                    <ul>
                        <li><a href="#">Inscripción</a></li>
                        <li><a href="#">Inasistencias</a></li>
                        <li><a href="#">Exámenes</a></li>
                    </ul>
                </li>
            </ul>
        </li>
        <li><a  href="#"><i class="icon-camera"></i>Opciones</a>
            <ul class="sub-menu">
                <li><g:link controller="management" action="main">Datos personales</g:link></li>
            </ul>
        </li>
        <li><g:link controller="login" action="logout">Cerrar sesión</g:link></li>
    </ul>
</nav>