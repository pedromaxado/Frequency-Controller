<%-- 
    Document   : show
    Created on : Mar 5, 2015, 7:13:17 PM
    Author     : pedro
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <c:import url="../application_layouts/head.jsp"></c:import>
        <title>Controle de Frequência - Relação Disc. - Turma</title>
    </head>
    <body>
        <c:import url="../application_layouts/navBar.jsp"></c:import>
        
        <div class="container-fluid">
            <div class="row">
                <div class="col-lg-offset-2 col-lg-8">
                    <div class="page-header">
                        <h2>${scd.studentClass.course}</h2>
                        <hr />
                        <small>Disciplina: ${scd.discipline.name}</small>
                    </div>
                    <ul class="list-group">
                        <li class="list-group-item"><strong>Carga Horária: </strong>${scd.hours}</li>
                        <li class="list-group-item"><strong>Início: </strong><fmt:formatDate value="${scd.startDate}" pattern="dd/MM/yyyy" /></li>
                        <li class="list-group-item"><strong>Fim: </strong><fmt:formatDate value="${scd.endDate}" pattern="dd/MM/yyyy" /></li>
                        <li class="list-group-item"><strong>Professor: </strong>${scd.teacher.name}</li>
                    </ul>
                    <div class="btn-group">
                        <a href="ctrl?op=StudentClassDisciplineController&id=${scd.id}&action=edit" class="btn btn-primary">Editar</a>
                        <a href="ctrl?op=StudentClassDisciplineController&id=${scd.id}&action=delete" class="btn btn-primary">Deletar</a>
                    </div>
                </div>
            </div>
        </div>
                    
        <c:import url="../application_layouts/footer.jsp"></c:import>
    </body>
</html>
