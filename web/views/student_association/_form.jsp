<%-- 
    Document   : _form
    Created on : Mar 10, 2015, 5:58:28 PM
    Author     : pedro
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="panel panel-primary">
    <div class="panel-heading">
        <h3 class="panel-title text-center">Associar Estudante</h3>
    </div>
    <div class="panel-body">
        <form class="form-horizontal" action="ctrl" method="post">
            <fieldset>
                <input type="hidden" name="op" value="StudentAssociationController" />
                <input type="hidden" name="action" value="create" />
                <div class="form-group">
                    <label for="studentIdAssocInput" class="col-lg-3 control-label">Estudante:</label>
                    <div class="col-lg-9">
                        <select class="form-control" id="studentIdAssocInput" name="studentIdAssocInput">
                            <c:forEach var="stdnt" items="${studentList}">
                                <option value="${stdnt.id}">${stdnt.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label for="studentStudentClassInput" class="col-lg-3 control-label">Turma:</label>
                    <div class="col-lg-9">
                        <select class="form-control" id="studentStudentClassInput" name="studentStudentClassAssocInput">
                            <c:forEach var="studentclass" items="${scList}">
                                <option value="${studentclass.code}">${studentclass.course}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-lg-offset-4 col-lg-4">
                        <button type="submit" class="btn btn-primary btn-block">Adicionar</button>
                    </div>
                </div>
            </fieldset>
        </form>
    </div>
</div>
