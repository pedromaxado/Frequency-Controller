<%--
    Document   : login
    Created on : Jan 23, 2015, 4:27:36 PM
    Author     : Pedro
--%>

<% 

    if(session.getAttribute("currentUser") != null) {
        request.getRequestDispatcher("views/home/index.jsp").forward(request, response);
    }
    
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri ="http://java.sun.com/jsp/jstl/core" prefix ="c" %>
<!DOCTYPE html>
<html>
    <head>
        <c:import url="views/application_layouts/head.jsp" />
        <title>Controle de Frequência - Login</title>

    </head>
    <body style="padding-top: 50px;">

        <div class="container">
            <div class="row">
                <div class="col-lg-offset-4 col-xs-12 col-lg-4">

                    <div class="panel panel-primary">
                        <div class="panel-heading">
                            <h1 class="panel-title text-center">Controle de Frequência</h1>
                        </div>
                        <div class="panel-body">
                            <form role="form" action="Login" method="POST">
                                <input type="hidden" name="op" value="Login" />
                                <div class="form-group">
                                    <label for="User">Usuário</label>
                                    <input type="text" class="form-control" id="User" name="user" placeholder="Usuário" >
                                </div>
                                <div class="form-group">
                                    <label for="Pwd">Senha</label>
                                    <input type="password" class="form-control" id="Pwd" name="pwd" placeholder="Senha">
                                </div>
                                <div class="form-group">
                                    <button type="submit" class="btn btn-primary btn-block">Entrar <span class="glyphicon glyphicon-arrow-right"></span></button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    </body>
</html>
