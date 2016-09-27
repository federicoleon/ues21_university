<a id="touch-menu" class="mobile-menu" href="#"><i class="icon-reorder"></i>Menu</a>

<nav>
    <ul class="menu">
        <li><g:link controller="management" action="main">Principal</g:link></li>

        <li><a href="#"><i class="icon-home"></i>Alumnos</a>
            <ul class="sub-menu">
                <li><g:link controller="secretary" action="registerStudent">Registrar nuevo alumno</g:link></li>
            </ul>
        </li>

        <li><a href="#"><i class="icon-home"></i>Gestión académica</a>
            <ul class="sub-menu">
                <li><a href="#"><i class="icon-home"></i>Inscripciones</a>
                    <ul class="sub-menu">
                        <li><g:link controller="secretary" action="studentCathedraRegistration">A cursado</g:link></li>
                        <li><g:link controller="secretary" action="registerStudentInExam">A examen</g:link></li>
                    </ul>
                </li>
                <li><a href="#"><i class="icon-home"></i>Mesas de examen</a>
                    <ul class="sub-menu">
                        <li><g:link controller="secretary" action="enableTestsPeriod">Habilitar</g:link></li>
                    </ul>
                </li>
            </ul>
        </li>

        <li><a  href="#"><i class="icon-camera"></i>Consultas</a>
            <ul class="sub-menu">
                <li><a href="#">Semestre</a>
                    <ul>
                        <li><a href="#">Inscripción</a>
                            <ul class="sub-menu">
                                <li><g:link controller="secretary" action="browseCourseRegistration">A cursado</g:link></li>
                                <li><g:link controller="secretary" action="browseExamRegistration">A examen</g:link></li>
                            </ul>
                        </li>

                        <li><li><g:link controller="secretary" action="browseAbsences">Inasistencias</g:link></li></li>
                        <li><a href="#">Exámenes</a></li>
                    </ul>
                </li>
                <li><g:link controller="secretary" action="browseCathedras">Comisiones</g:link></li>
                <li><g:link controller="secretary" action="browseCorrelatives">Correlativas</g:link></li>
            </ul>
        </li>

        <li><g:link controller="login" action="logout">Cerrar sesión</g:link></li>
    </ul>
</nav>
