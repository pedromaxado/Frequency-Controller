<%-- 
    Document   : _form
    Created on : Mar 9, 2015, 3:54:26 PM
    Author     : pedro
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="panel panel-primary">
    <div class="panel-heading">
        <h3 class="panel-title text-center">Adicionar Aula</h3>
    </div>
    <div class="panel-body">
        <form id="ClassForm" class="form-horizontal" action="ctrl" method="post">
            <fieldset>
                <input type="hidden" name="op" value="ClassController" />
                <input type="hidden" name="action" value="create" />
                <div class="form-group">
                    <label for="ScdInput" class="col-lg-4 control-label">Turma-Disc.:</label>
                    <div class="col-lg-8">
                        <select class="form-control" id="ScdInput" name="scdInput">
                            <option></option>
                            <c:forEach var="scd" items="${scdList}">
                                <option value="${scd.id}">${scd.studentClass.polo.name} - ${scd.studentClass.course} - ${scd.discipline.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label for="Date" class="col-lg-4 control-label">Data:</label>
                    <div class="col-lg-8">
                        <input type="date" class="form-control" id="Date" name="classDate" value="${clss.date}" />
                    </div>
                </div>
                <div class="form-group">
                    <label for="Hours" class="col-lg-4 control-label">Hora/Aula:</label>
                    <div class="col-lg-8">
                        <input type="text" class="form-control" id="Hours" name="hoursPerDay" value="${clss.hoursPerDay}" placeholder="Hora/Aula"/>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-lg-offset-4 col-lg-4">
                        <button class="btn btn-primary btn-block">Adicionar</button>
                    </div>
                </div>
            </fieldset>
        </form>
    </div>
</div>

<script type="text/javascript">
    $().ready(function() {
        $("#ClassForm").validate({
            
            rules: {
                scdInput:       "required",
                classDate:      "required",
                hoursPerDay:    "required"
            },
            
            messages: {
                scdInput:       "Campo obrigatório.",
                classDate:      "Campo obrigatório.",
                hoursPerDay:    "Campo obrigatório."
            },
            
            highlight: function(element) {
                $(element).closest('.form-group').removeClass('has-success').addClass('has-error');
            },
            
            success: function(element) {
                $(element).closest('.form-group').removeClass('has-error').addClass('has-success');
            },
            
            errorClass: "text-danger"
            
        });
    });
</script>
                    