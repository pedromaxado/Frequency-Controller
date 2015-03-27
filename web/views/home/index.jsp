<%-- 
    Document   : home
    Created on : Jan 26, 2015, 12:08:23 PM
    Author     : Pedro
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <c:import url="../application_layouts/head.jsp" />
        <title>Controle de Frequência - PRONATEC</title>

        <style type="text/css">
/*            .nav>li>a:hover {
                text-decoration: none;
                background-color: #424242;
            }*/
        </style>

    </head>
    <body style="padding-top: 150px;">

        <c:import url="../application_layouts/navBar.jsp"></c:import>

        <div class="container-fluid">

            <div class="row">

                <div class="col-lg-offset-2 col-lg-8">
                    <div class="panel panel-primary">
                        <div class="panel-heading">
                            <h3 class="panel-title text-center">Controle de Frequência - PRONATEC</h3>
                        </div>
                        <div class="panel-body">
                            <ul class="nav nav-pills nav-stacked">
                                <c:if test="${sessionScope.currentUserRole eq 'admin'}">
                                    <li class="text-center"><a href="ctrl?op=PartialSheetController&action=_new">Planilha Parcial</a></li>
                                    <li class="text-center"><a href="ctrl?op=GlobalSheetController&action=_new">Planilha Consolidada</a></li>
                                </c:if>
                                <c:if test="${sessionScope.currentUserRole eq 'teacher'}">
                                    <li class="text-center"><a href="ctrl?op=ClassController&action=index">Listar Aulas</a></li>
                                    <li class="text-center"><a href="ctrl?op=AttendanceController&action=presetSCD">Inserir Presenças</a></li>
                                </c:if>    
                            </ul>
                        </div>
                    </div>
                </div>

            </div>

        </div>
        
        <c:import url="../application_layouts/footer.jsp"></c:import>
    </body>
</html>
