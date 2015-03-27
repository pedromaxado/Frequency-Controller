<%-- 
    Document   : show
    Created on : Mar 5, 2015, 7:13:17 PM
    Author     : pedro
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <c:import url="../application_layouts/head.jsp"></c:import>
        <title>Controle de Frequência - Estudante</title>
    </head>
    <body>
        <c:import url="../application_layouts/navBar.jsp"></c:import>
        
        <div class="container-fluid">
            <div class="row">
                <div class="col-lg-offset-2 col-lg-8">
                    <div class="page-header">
                        <h2>${student.name}</h2>
                        <hr />
                        <small>ID: ${student.id}</small>
                    </div>
                    <p>Turma(s):</p>
                    <ul class="list-group">
                        <c:forEach var="scs" items="${scsList}">
                            <li class="list-group-item">${scs.studentClass.course}</li>
                        </c:forEach>
                    </ul>
                    <div class="btn-group">
                        <a href="ctrl?op=StudentController&id=${student.id}&action=edit" class="btn btn-primary">Editar</a>
                        <a href="ctrl?op=StudentController&id=${student.id}&action=delete" class="btn btn-primary">Deletar</a>
                    </div>
                </div>
            </div>
        </div>
                    
        <c:import url="../application_layouts/footer.jsp"></c:import>
    </body>
</html>
