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
        <title>Controle de Frequência - Aula</title>
    </head>
    <body>
        <c:import url="../application_layouts/navBar.jsp"></c:import>
        
        <div class="container-fluid">
            <div class="row">
                <div class="col-lg-offset-2 col-lg-8">
                    <div class="page-header">
                        <h2>${clss.studentClassDiscipline.studentClass.course} - ${clss.studentClassDiscipline.discipline.name}</h2>
                        <hr />
                        <small>Data: <fmt:formatDate value="${clss.date}" pattern="dd/MM/yyyy" /></small>
                    </div>
                    <c:if test="${not empty attList}">
                        <h3>Presenças <a href="ctrl?op=AttendanceController&action=edit&id=${clss.id}"><small>Editar</small></a></h3>
                        <ul class="list-group">
                            <c:forEach var="att" items="${attList}">
                                <li class="list-group-item">
                                    <span class="badge">
                                        <c:if test="${att.presence}">
                                            Presente
                                        </c:if>
                                        <c:if test="${not att.presence}">
                                            Ausente
                                        </c:if>    
                                    </span>
                                    ${att.student.name}
                                </li>
                            </c:forEach>
                        </ul>
                    </c:if>
                    <c:if test="${sessionScope.currentUserRole eq 'admin'}">
                        <div class="btn-group">
                            <a href="ctrl?op=ClassController&id=${clss.id}&action=edit" class="btn btn-primary">Editar</a>
                            <a href="ctrl?op=ClassController&id=${clss.id}&action=delete" class="btn btn-primary">Deletar</a>
                        </div>
                    </c:if>
                </div>
            </div>
        </div>
                    
        <c:import url="../application_layouts/footer.jsp"></c:import>
    </body>
</html>
