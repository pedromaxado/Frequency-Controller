<%-- 
    Document   : index
    Created on : Mar 5, 2015, 7:11:13 PM
    Author     : pedro
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <c:import url="../application_layouts/head.jsp"></c:import>
        <title>Controle de Frequência - Relações Disc. - Turma</title>
    </head>
    <body>
        <c:import url="../application_layouts/navBar.jsp"></c:import>
        
        <div class="container-fluid">
            <div class="row">
                <div class="col-lg-offset-1 col-lg-10">
                    <h3 class="text-center">Lista de Relações Disc. - Turma</h3>
                    <div class="table-responsive">
                        <table class="table table-bordered table-striped">
                            <thead>
                                <tr>
                                    <th class="text-center">ID</th>
                                    <th>Turma</th>
                                    <th>Disciplina</th>
                                    <th class="text-center">Carga Horária</th>
                                    <th class="text-center">Início</th>
                                    <th class="text-center">Fim</th>
                                    <th>Professor</th>
                                    <th class="text-center">Editar</th>
                                    <th class="text-center">Apagar</th>
                                    <th class="text-center">Detalhes</th>
                                </tr>
                            </thead>
                            <tbody>
                            <c:forEach var="scd" items="${scdList}">
                                <tr>
                                    <td class="text-center">${scd.id}</td>
                                    <td>${scd.studentClass.course}</td>
                                    <td>${scd.discipline.name}</td>
                                    <td class="text-center">${scd.hours}</td>
                                    <td class="text-center"><fmt:formatDate value="${scd.startDate}" pattern="dd/MM/yyyy" /></td>
                                    <td class="text-center"><fmt:formatDate value="${scd.endDate}" pattern="dd/MM/yyyy" /></td>
                                    <td>${scd.teacher.name}</td>
                                    <td class="text-center">
                                        <a href="ctrl?op=StudentClassDisciplineController&id=${scd.id}&action=edit">
                                            <span class="glyphicon glyphicon-pencil"></span>
                                        </a>
                                    </td>
                                    <td class="text-center">
                                        <a href="ctrl?op=StudentClassDisciplineController&id=${scd.id}&action=delete">
                                            <span class="glyphicon glyphicon-trash"></span>
                                        </a>
                                    </td>
                                    <td class="text-center">
                                        <a href="ctrl?op=StudentClassDisciplineController&id=${scd.id}&action=show">Detalhes</a>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
        
        <c:import url="../application_layouts/footer.jsp"></c:import>
    </body>
</html>
