<%-- 
    Document   : index
    Created on : Mar 5, 2015, 7:11:13 PM
    Author     : pedro
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <c:import url="../application_layouts/head.jsp"></c:import>
        <title>Controle de Frequência - Polos</title>
    </head>
    <body>
        <c:import url="../application_layouts/navBar.jsp"></c:import>
        
        <div class="container-fluid">
            <div class="row">
                <div class="col-lg-offset-3 col-lg-6">
                    <h3 class="text-center">Lista de Polos</h3>
                    <div class="table-responsive">
                        <table class="table table-bordered table-striped">
                            <thead>
                                <tr>
                                    <th class="text-center">ID</th>
                                    <th>Nome</th>
                                    <th class="text-center">Editar</th>
                                    <th class="text-center">Apagar</th>
                                    <th class="text-center">Detalhes</th>
                                </tr>
                            </thead>
                            <tbody>
                            <c:forEach var="polo" items="${poloList}">
                                <tr>
                                    <td class="text-center">${polo.id}</td>
                                    <td>${polo.name}</td>
                                    <td class="text-center">
                                        <a href="ctrl?op=PoloController&id=${polo.id}&action=edit">
                                            <span class="glyphicon glyphicon-pencil"></span>
                                        </a>
                                    </td>
                                    <td class="text-center">
                                        <a href="ctrl?op=PoloController&id=${polo.id}&action=delete">
                                            <span class="glyphicon glyphicon-trash"></span>
                                        </a>
                                    </td>
                                    <td class="text-center">
                                        <a href="ctrl?op=PoloController&id=${polo.id}&action=show">Detalhes</a>
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
