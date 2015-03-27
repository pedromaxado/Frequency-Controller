<%-- 
    Document   : _form.jsp
    Created on : Mar 9, 2015, 3:24:21 PM
    Author     : pedro
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="panel panel-primary">
    <div class="panel-heading">
        <h3 class="panel-title text-center">Adicionar Polo</h3>
    </div>
    <div class="panel-body">
        <form id="PoloForm" action="ctrl" method="post" role="form" class="form-horizontal">
            <fieldset>
                <input type="hidden" name="op" value="PoloController" />
                <input type="hidden" name="action" value="create" />
                <div class="form-group">
                    <label class="col-lg-2 control-label">Polo:</label>
                    <div class="col-lg-10">
                        <input type="text" name="poloInput" placeholder="Polo" class="form-control" value="${polo.name}" />
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
        $("#PoloForm").validate({
            rules: {
                poloInput: "required"
            },
            
            highlight: function(element) {
                $(element).closest('.form-group').removeClass('has-success').addClass('has-error');
            },
            
            success: function(element) {
                $(element).closest('.form-group').removeClass('has-error').addClass('has-success');
            },
            
            messages: {
                poloInput: "Campo obrigatório."
            },
            
            errorClass: "text-danger"
        });
    });
</script>
