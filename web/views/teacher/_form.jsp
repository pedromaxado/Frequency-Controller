<%-- 
    Document   : _form
    Created on : Mar 9, 2015, 4:23:09 PM
    Author     : pedro
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="panel panel-primary">
    <div class="panel-heading">
        <h3 class="panel-title text-center">Adicionar Professor</h3>
    </div>
    <div class="panel-body">
        <form id="TeacherForm" class="form-horizontal" action="ctrl" method="post">
            <fieldset>
                <input type="hidden" name="op" value="TeacherController" />
                <input type="hidden" name="action" value="create" />
                <div class="form-group">
                    <label for="cpfInput" class="col-lg-4 control-label">CPF:</label>
                    <div class="col-lg-8">
                        <input type="text" class="form-control" id="cpfInput" name="cpfInput" value="${teacher.cpf}" placeholder="CPF" />
                    </div>
                </div>
                <div class="form-group">
                    <label for="TeacherNameInput" class="col-lg-4 control-label">Nome:</label>
                    <div class="col-lg-8">
                        <input type="text" class="form-control" id="TeacherNameInput" name="teacherNameInput" value="${teacher.name}" placeholder="Nome" />
                    </div>
                </div>
                <div class="form-group">
                    <label for="BankInput" class="col-lg-4 control-label">Banco:</label>
                    <div class="col-lg-8">
                        <input type="text" class="form-control" id="BankInput" name="bankInput" value="${teacher.bank}" placeholder="Banco" />
                    </div>
                </div>
                <div class="form-group">
                    <label for="AgencyInput" class="col-lg-4 control-label">Agência:</label>
                    <div class="col-lg-8">
                        <input type="text" class="form-control" id="AgencyInput" name="agencyInput" value="${teacher.agency}" placeholder="Agência" />
                    </div>
                </div>
                <div class="form-group">
                    <label for="AccountInput" class="col-lg-4 control-label">Conta:</label>
                    <div class="col-lg-8">
                        <input type="text" class="form-control" id="AccountInput" name="accountInput" value="${teacher.account}" placeholder="Conta" />
                    </div>
                </div>
                <div class="form-group">
                    <label for="ContactInput" class="col-lg-4 control-label">Contato</label>
                    <div class="col-lg-8">
                        <input type="text" class="form-control" id="ContactInput" name="contactInput" value="${teacher.contact}" placeholder="Contato" />
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
        $("#TeacherForm").validate({
            rules: {
                
                cpfInput: {
                    required: true,
                    number: true,
                    minlength: 11,
                    maxlength: 11
                },
                
                teacherNameInput:   "required",
                bankInput:          "required",
                agencyInput:        "required",
                accountInput:       "required",
                contactInput:       "required"
                
            },
            
            messages: {
                cpfInput: {
                    required:   "Campo obrigatório.",
                    number:     "Digite apenas números!",
                    minlength:  "O CPF contém exatos 9 dígitos.",
                    maxlength:  "O CPF contém exatos 9 dígitos."
                },
                
                teacherNameInput:   "Campo obrigatório",
                bankInput:          "Campo obrigatório",
                agencyInput:        "Campo obrigatório",
                accountInput:       "Campo obrigatório",
                contactInput:       "Campo obrigatório"
                
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