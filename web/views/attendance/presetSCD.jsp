<%-- 
    Document   : preset
    Created on : Mar 14, 2015, 11:11:31 AM
    Author     : pedro
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <c:import url="../application_layouts/head.jsp"></c:import>
        <title>Controle de Frequência - Escolher Aula</title>
        
    </head>
    <body>
        <c:import url="../application_layouts/navBar.jsp"></c:import>
        
        <div class="container-fluid">
            <div class="row">
                <div class="col-lg-offset-3 col-lg-6">
                    <div class="panel panel-primary">
                        <div class="panel-heading">
                            <h3 class="panel-title text-center">Escolha a Aula</h3>
                        </div>
                        <div class="panel-body">
                            <form class="form-horizontal" method="post" action="ctrl">
                                <fieldset>
                                    <input type="hidden" name="op" value="AttendanceController" />
                                    <input type="hidden" name="action" value="presetDate" />
                                    <div class="form-group">
                                        <label for="scdInput" class="col-lg-3 control-label">Turma-Disc.:</label>
                                        <div class="col-lg-9">
                                            <select class="form-control" id="scdInput" name="scdInput">
                                                <c:forEach var="scd" items="${scdList}">
                                                    <option value="${scd.id}">${scd.studentClass.polo.name} - ${scd.studentClass.course} - ${scd.discipline.name}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="col-lg-offset-4 col-lg-4">
                                            <button class="btn btn-primary btn-block">Próximo</button>
                                        </div>
                                    </div>
                                </fieldset>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        
        <c:import url="../application_layouts/footer.jsp"></c:import>
    </body>
</html>
