<%-- 
    Document   : show
    Created on : Mar 13, 2015, 7:40:21 PM
    Author     : pedro
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <c:import url="../application_layouts/navBar.jsp"></c:import>

        <div class="container-fluid">
            <div class="row">
                <div class="col-lg-offset-2 col-lg-8">
                    <div class="row">
                        <div class="col-lg-4">
                            <div class="panel panel-primary">
                                <div class="panel-body">
                                    <h4 class="text-center">${att.aClass.studentClassDiscipline.studentClass.course}</h4>
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-4">
                            <div class="panel panel-primary">
                                <div class="panel-body">
                                    <h4 class="text-center">${att.aClass.studentClassDiscipline.discipline.name}</h4>
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-4">
                            <div class="panel panel-primary">
                                <div class="panel-body">
                                    <h4 class="text-center">${att.aClass.date}</h4>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-offset-2 col-lg-8 table-responsive">
                    <table class="table table-bordered table-striped">
                        <thead>
                            <tr>
                                <th><h4 class="text-center">Aluno</h4></th>
                                <th><h4 class="text-center">Presença</h4></th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="att" items="${attList}">
                                
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
                                
        <c:import url="../application_layouts/footer.jsp"></c:import>
    </body>
</html>
