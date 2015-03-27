<%-- 
    Document   : error-page
    Created on : Mar 18, 2015, 5:18:45 PM
    Author     : pedro
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <c:import url="views/application_layouts/head.jsp"></c:import>
        <title>Controle de Frequência - ERRO!</title>
    </head>
    <body>
        <c:import url="views/application_layouts/navBar.jsp"></c:import>
        
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <div class="jumbotron text-center">
                        <h1>Bem, isto é constrangedor... :(</h1>
                        <p>Algum erro desconhecido ocorreu. Entre em contato com o administrador ou tente outra vez.</p>
                        <p>Use o botão de voltar do navegador</p>
                        <p><strong>Ou você pode simplesmente clicar neste botão:</strong></p>
                        <p><a class="btn btn-primary btn-lg" href="ctrl?op=Home" role="button"><span class="glyphicon glyphicon-home"></span> Home</a></p>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
