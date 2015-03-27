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
        <title>Controle de Frequência - Aulas</title>
    </head>
    <body>
        <c:import url="../application_layouts/navBar.jsp"></c:import>
        
        <div class="container-fluid">
            <div class="row">
                <div class="col-lg-offset-2 col-lg-8">
                    <h3 class="text-center">Lista de Aulas</h3>
                    <div class="table-responsive">
                        <table class="table table-bordered table-striped">
                            <thead>
                                <tr>
                                    <th class="text-center">ID</th>
                                    <th>Turma</th>
                                    <th>Disciplina</th>
                                    <th class="text-center">Data</th>
                                    <th class="text-center">Carga Horária/dia</th>
                                    <c:if test="${sessionScope.currentUserRole eq 'admin'}">
                                        <th class="text-center">Editar</th>
                                        <th class="text-center">Apagar</th>
                                    </c:if>
                                    <th class="text-center">Detalhes</th>
                                </tr>
                            </thead>
                            <tbody>
                            <c:forEach var="clss" items="${clssList}">
                                <tr>
                                    <td class="text-center">${clss.id}</td>
                                    <td>${clss.studentClassDiscipline.studentClass.course}</td>
                                    <td>${clss.studentClassDiscipline.discipline.name}</td>
                                    <td class="text-center"><fmt:formatDate value="${clss.date}" pattern="dd/MM/yyyy" /></td>
                                    <td class="text-center">${clss.hoursPerDay}</td>
                                    <c:if test="${sessionScope.currentUserRole eq 'admin'}">
                                        <td class="text-center">
                                            <a href="ctrl?op=ClassController&id=${clss.id}&action=edit">
                                                <span class="glyphicon glyphicon-pencil"></span>
                                            </a>
                                        </td>
                                        <td class="text-center">
                                            <a href="ctrl?op=ClassController&id=${clss.id}&action=delete">
                                                <span class="glyphicon glyphicon-trash"></span>
                                            </a>
                                        </td>
                                    </c:if>
                                    <td class="text-center">
                                        <a href="ctrl?op=ClassController&id=${clss.id}&action=show">Detalhes</a>
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
