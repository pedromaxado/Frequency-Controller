<%-- 
    Document   : _form
    Created on : Mar 13, 2015, 7:39:58 PM
    Author     : pedro
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="row">
    <div class="col-lg-offset-1 col-lg-10">
        <div class="row">
            <div class="col-lg-4">
                <div class="panel panel-primary">
                    <div class="panel-body">
                        <h4 class="text-center">${aClass.studentClassDiscipline.studentClass.course}</h4>
                    </div>
                </div>
            </div>
            <div class="col-lg-4">
                <div class="panel panel-primary">
                    <div class="panel-body">
                        <h4 class="text-center">${aClass.studentClassDiscipline.discipline.name}</h4>
                    </div>
                </div>
            </div>
            <div class="col-lg-4">
                <div class="panel panel-primary">
                    <div class="panel-body">
                        <h4 class="text-center"><fmt:formatDate value="${aClass.date}" pattern="dd/MM/yyyy" /></h4>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="row">
    <div class="col-lg-offset-3 col-lg-6 table-responsive">
        <form role="form" action="ctrl" method="post">
            <input type="hidden" name="op" value="AttendanceController" />
            <input type="hidden" name="action" value="${action}" />
            <input type="hidden" name="id" value="${aClass.id}" />
            <table class="table table-bordered table-striped table-condensed">
                <thead>
                    <tr>
                        <th><h5 class="text-center">ID</h5></th>
                        <th><h5>&nbsp;Aluno</h5></th>
                        <th><h5 class="text-center">Presença</h5></th>
                    </tr>
                </thead>
                <tbody>
                <c:forEach var="student" items="${studentList}">
                    <tr>
                        <td><h5 class="text-center">${student.id}</h5></td>
                        <td><h5>&nbsp;${student.name}</h5></td>
                        <td class="text-center">
                            <input type="checkbox" name="${student.id}" checked />
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <div class="col-lg-offset-4 col-lg-4 form-group">
                <button type="submit" class="btn btn-primary btn-block">Adicionar</button>
            </div>
        </form>
    </div>
</div>

<script type="text/javascript">
    $(document).ready(function() {
    
        $("[type='checkbox']").bootstrapToggle({
            on: 'Presente',
            off: 'Ausente',
            size: 'small'
        });
    
    });
</script>