<%-- 
    Document   : presetDate
    Created on : Mar 27, 2015, 11:00:07 AM
    Author     : pedro
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <c:import url="../application_layouts/head.jsp"></c:import>
        <title>Controle de Frequência - Escolher Data</title>
    </head>
    <body>
        <c:import url="../application_layouts/navBar.jsp"></c:import>
        
        <div class="container-fluid">
            <div class="row">
                <div class="col-lg-offset-4 col-lg-4">
                    <div class="panel panel-primary">
                        <div class="panel-heading">
                            <h3 class="panel-title text-center">Escolha a Data</h3>
                        </div>
                        <div class="panel-body">
                            <form action="ctrl" class="form-horizontal" method="post">
                                <fieldset>
                                    <input type="hidden" name="op" value="AttendanceController" />
                                    <input type="hidden" name="action" value="_new"/>
                                    <div class="form-group">
                                        <label for="classDateInput" class="col-lg-3 control-label">Data:</label>
                                        <div class="col-lg-9">
                                            <select class="form-control" name="classInput" id="classDateInput">
                                            <c:if test="${not empty classList}">
                                                <c:forEach var="aClass" items="${classList}">
                                                    <option value="${aClass.id}"><fmt:formatDate value="${aClass.date}" pattern="dd/MM/yyyy" /></option>
                                                </c:forEach>
                                            </c:if>
                                            <c:if test="${empty classList}">
                                                <option>Nenhuma data disponível</option>
                                            </c:if>
                                            </select>
                                        </div>
                                    </div>
                                    <c:if test="${not empty classList}">
                                    <div class="form-group">
                                        <div class="col-lg-offset-4 col-lg-4">
                                            <button class="btn btn-primary btn-block">Próximo</button>
                                        </div>
                                    </div>
                                    </c:if>
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
