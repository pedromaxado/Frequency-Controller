<%-- 
    Document   : _form
    Created on : Mar 9, 2015, 4:11:46 PM
    Author     : pedro
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="panel panel-primary">
    <div class="panel-heading">
        <h3 class="panel-title text-center">Adicionar Disciplina</h3>
    </div>
    <div class="panel-body">
        <form id="DisciplineForm" class="form-horizontal" action="ctrl" method="post">
            <fieldset>
                <input type="hidden" name="op" value="DisciplineController" />
                <input type="hidden" name="action" value="create" />
                <div class="form-group">
                    <label for="DisciplineCodeInput" class="col-lg-3 control-label">Código:</label>
                    <div class="col-lg-9">
                        <input type="text" class="form-control" id="DisciplineCodeInput" name="disciplineIdInput" value="${discipline.code}" placeholder="Código" />
                    </div>
                </div>
                <div class="form-group">
                    <label for="DisciplineNameInput" class="col-lg-3 control-label">Disciplina:</label>
                    <div class="col-lg-9">
                        <input type="text" class="form-control" id="DisciplineNameInput" name="disciplineNameInput" value="${discipline.name}" placeholder="Disciplina" />
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
        $("#DisciplineForm").validate({
            rules: {
                
                disciplineIdInput: {
                    required: true,
                    number: true
                },
                
                disciplineNameInput: "required"
                
            },
            
            highlight: function(element) {
                $(element).closest('.form-group').removeClass('has-success').addClass('has-error');
            },
            
            success: function(element) {
                $(element).closest('.form-group').removeClass('has-error').addClass('has-success');
            },
            
            messages: {
                disciplineIdInput: {
                    required:   "Campo obrigatório.",
                    number:     "Digite apenas números!"
                },
                
                disciplineNameInput: "Campo obrigatório."
                
            },
            
            errorClass: "text-danger"
            
        });
    });
</script>
