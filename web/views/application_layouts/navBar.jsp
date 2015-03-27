<%-- 
    Document   : navBar
    Created on : Jan 29, 2015, 9:23:42 AM
    Author     : Pedro
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<nav class="navbar navbar-fixed-top navbar-default">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#meni">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="ctrl?op=Home"><span class="glyphicon glyphicon-home"></span>&nbsp;&nbsp;Home</a>
        </div>
        <div class="collapse navbar-collapse" id="meni">
            <ul class="nav navbar-nav">
                <li class="dropdown">
                    <a href="#" data-toggle="dropdown" role="button" aria-expanded="false" class="dropdown-toggle">Listar <span class="caret"></span></a>
                    <ul role="form" class="dropdown-menu">
                        <c:if test="${sessionScope.currentUserRole eq 'admin'}">
                            <li><a href="ctrl?op=PoloController&action=index">Polos</a></li>
                            <li class="divider"></li>
                            <li><a href="ctrl?op=TeacherController&action=index">Professores</a></li>
                            <li class="divider"></li>
                            <li><a href="ctrl?op=StudentController&action=index">Estudantes</a></li>
                            <li class="divider"></li>
                            <li><a href="ctrl?op=StudentClassController&action=index">Turmas</a></li>
                            <li class="divider"></li>
                            <li><a href="ctrl?op=DisciplineController&action=index">Disciplinas</a></li>
                            <li class="divider"></li>
                            <li><a href="ctrl?op=StudentClassDisciplineController&action=index">Relações Disc. - Turma</a></li>
                            <li class="divider"></li>
                            <li><a href="ctrl?op=ClassController&action=index">Aulas</a></li>
                        </c:if>
                        <c:if test="${sessionScope.currentUserRole eq 'teacher'}">
                            <li><a href="ctrl?op=ClassController&action=index">Aulas</a></li>
                        </c:if>
                    </ul>
                </li>
                <li class="dropdown">
                    <a href="#" data-toggle="dropdown" role="button" aria-expanded="false" class="dropdown-toggle">Adicionar <span class="caret"></span></a>
                    <ul role="form" class="dropdown-menu">
                        <c:if test="${sessionScope.currentUserRole eq 'admin'}">
                            <li><a href="ctrl?op=PoloController&action=_new">Polo</a></li>
                            <li class="divider"></li>
                            <li><a href="ctrl?op=TeacherController&action=_new">Professor</a></li>
                            <li class="divider"></li>
                            <li><a href="ctrl?op=StudentController&action=_new">Estudante</a></li>
                            <li class="divider"></li>
                            <li><a href="ctrl?op=StudentClassController&action=_new">Turma</a></li>
                            <li class="divider"></li>
                            <li><a href="ctrl?op=StudentAssociationController&action=_new">Assoc. Estudante-Turma</a></li>
                            <li class="divider"></li>
                            <li><a href="ctrl?op=DisciplineController&action=_new">Disciplina</a></li>
                            <li class="divider"></li>
                            <li><a href="ctrl?op=StudentClassDisciplineController&action=_new">Relação Disc. - Turma</a></li>
                            <li class="divider"></li>
                            <li><a href="ctrl?op=ClassController&action=_new">Aula</a></li>
                            <li class="divider"></li>
                            <li><a href="ctrl?op=AttendanceController&action=presetSCD">Presenças</a></li>
                        </c:if>
                        <c:if test="${sessionScope.currentUserRole eq 'teacher'}">
                            <li><a href="ctrl?op=AttendanceController&action=presetSCD">Presenças</a></li>
                        </c:if>
                    </ul>
                </li>
                <c:if test="${sessionScope.currentUserRole eq 'admin'}">
                    <li><a href="ctrl?op=PartialSheetController&action=_new">Planilha Parcial</a></li>
                    <li><a href="ctrl?op=GlobalSheetController&action=_new">Planilha Consolidada</a></li>
                </c:if>
            </ul>
            <form action="Logout" class="navbar-form navbar-right" role="logout" method="POST">
                <button type="submit" class="btn btn-default"><span class="glyphicon glyphicon-log-out"></span> Logout</button>
            </form>
            <p class="nav navbar-text navbar-right" style="color: white;">
                Bem vindo ${sessionScope.currentUserName}!
            </p>
        </div>
    </div>
</nav>
