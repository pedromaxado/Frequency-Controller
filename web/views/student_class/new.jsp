<%-- 
    Document   : new
    Created on : Mar 9, 2015, 4:21:15 PM
    Author     : pedro
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <c:import url="../application_layouts/head.jsp"></c:import>
        <title>Controle de Frequência - Nova Turma</title>
    </head>
    <body>
        <c:import url="../application_layouts/navBar.jsp"></c:import>
        
        <div class="row">
            <div class="col-lg-offset-4 col-lg-4">
                <%@include file="_form.jsp" %>
            </div>
        </div>
            
        <c:import url="../application_layouts/footer.jsp"></c:import>
    </body>
</html>
