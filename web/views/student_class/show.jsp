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
        <title>Controle de Frequência - Turma</title>
    </head>
    <body>
        <c:import url="../application_layouts/navBar.jsp"></c:import>
        
        <div class="container-fluid">
            <div class="row">
                <div class="col-lg-offset-2 col-lg-8">
                    <div class="page-header">
                        <h2>${sc.course}</h2>
                        <hr />
                        <small>ID: ${sc.code}</small>
                    </div>
                    <p>Alunos:</p>
                    <ul class="list-group">
                        <c:forEach var="student" items="${studentList}">
                            <li class="list-group-item">${student.name}</li>
                        </c:forEach>
                    </ul>
                    <div class="btn-group">
                        <a href="ctrl?op=StudentClassController&id=${sc.code}&action=edit" class="btn btn-primary">Editar</a>
                        <a href="ctrl?op=StudentClassController&id=${sc.code}&action=delete" class="btn btn-primary">Deletar</a>
                    </div>
                </div>
            </div>
        </div>
                    
        <c:import url="../application_layouts/footer.jsp"></c:import>
    </body>
</html>
