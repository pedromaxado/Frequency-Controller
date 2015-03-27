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
        <title>Controle de Frequência - Professor</title>
    </head>
    <body>
        <c:import url="../application_layouts/navBar.jsp"></c:import>
        
        <div class="container-fluid">
            <div class="row">
                <div class="col-lg-offset-2 col-lg-8">
                    <div class="page-header">
                        <h2>${teacher.name}</h2>
                        <hr />
                        <small>CPF: ${teacher.cpf}</small>
                    </div>
                    <ul class="list-group">
                        <li class="list-group-item"><strong>Banco: </strong>${teacher.bank}</li>
                        <li class="list-group-item"><strong>Agência: </strong>${teacher.agency}</li>
                        <li class="list-group-item"><strong>Conta: </strong>${teacher.account}</li>
                        <li class="list-group-item"><strong>Contato: </strong>${teacher.contact}</li>
                    </ul>
                    <div class="btn-group">
                        <a href="ctrl?op=TeacherController&id=${teacher.cpf}&action=edit" class="btn btn-primary">Editar</a>
                        <a href="ctrl?op=TeacherController&id=${teacher.cpf}&action=delete" class="btn btn-primary">Deletar</a>
                    </div>
                </div>
            </div>
        </div>
                    
        <c:import url="../application_layouts/footer.jsp"></c:import>
    </body>
</html>
