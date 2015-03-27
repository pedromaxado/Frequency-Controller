<%-- 
    Document   : _form
    Created on : Mar 9, 2015, 4:42:26 PM
    Author     : pedro
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="panel panel-primary">
    <div class="panel-heading">
        <h3 class="panel-title text-center">Adicionar Estudante</h3>
    </div>
    <div class="panel-body">
        <form id="StudentForm" class="form-horizontal" action="ctrl" method="post">
            <fieldset>
                <input type="hidden" name="op" value="StudentController" />
                <input type="hidden" name="action" value="create" />
                <div class="form-group">
                    <label for="StudentNameInput" class="col-lg-2 control-label">Nome:</label>
                    <div class="col-lg-10">
                        <input type="text" class="form-control" id="StudentNameInput" name="studentNameInput" value="${student.name}" placeholder="Nome" />
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
       
        $("#StudentForm").validate({
            rules: {
                studentNameInput: "required"
            },
            
            highlight: function(element) {
                $(element).closest('.form-group').removeClass('has-success').addClass('has-error');
            },
            
            success: function(element) {
                $(element).closest('.form-group').removeClass('has-error').addClass('has-success');
            },
            
            messages: {
                studentNameInput: "Campo obrigatório."
            },
            
            errorClass: "text-danger"
        });
        
    });
    
</script>