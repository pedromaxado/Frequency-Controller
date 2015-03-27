<%-- 
    Document   : _form
    Created on : Mar 20, 2015, 8:41:31 PM
    Author     : pedro
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="panel panel-primary">
    <div class="panel-heading">
        <h3 class="panel-title">Planilha Consolidada</h3>
    </div>
    <div class="panel-body">
        <form class="form-horizontal" action="ctrl">
            <fieldset>
                <input type="hidden" name="op" value="GlobalSheetController" />
                <input type="hidden" name="action" value="create" />
                <div class="form-group">
                    <label for="scInput" class="col-lg-4 control-label">Turma:</label>
                    <div class="col-lg-8">
                        <select id="scInput" name="studentClass" class="form-control">
                            <c:forEach var="sc" items="${scList}">
                                <option value="${sc.code}">${sc.course}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label for="StartDate" class="col-lg-4 control-label">Data(Início):</label>
                    <div class="col-lg-8">
                        <input type="date" class="form-control" id="StartDate" name="startDate" />
                    </div>
                </div>
                <div class="form-group">
                    <label for="EndDate" class="col-lg-4 control-label">Data(Fim):</label>
                    <div class="col-lg-8">
                        <input type="date" class="form-control" id="EndDate" name="endDate" />
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-lg-offset-4 col-lg-4">
                        <button class="btn btn-primary btn-block">Gerar</button>
                    </div>
                </div>
            </fieldset>
        </form>
    </div>
</div>
