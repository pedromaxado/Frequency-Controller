<%-- 
    Document   : _form
    Created on : Mar 9, 2015, 6:08:32 PM
    Author     : pedro
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="panel panel-primary">
    <div class="panel-heading">
        <h3 class="panel-title text-center">Relação Disciplina-Turma</h3>
    </div>
    <div class="panel-body">
        <form id="StudentClassDisciplineForm" action="ctrl" method="post" role="form" class="form-horizontal">
            <fieldset>
                <input type="hidden" name="op" value="StudentClassDisciplineController" />
                <input type="hidden" name="action" value="create" />
                <div class="form-group">
                    <label for="StudentClassSCDInput" class="col-lg-4 control-label">Turma:</label>
                    <div class="col-lg-8">
                        <select class="form-control" id="StudentClassSCDInput" name="studentClassSCDInput">
                            <option></option>
                            <c:forEach var="studentclass" items="${studentClassList}">
                                <option value="${studentclass.code}">${studentclass.course}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label for="DisciplineSCDInput" class="col-lg-4 control-label">Disciplina:</label>
                    <div class="col-lg-8">
                        <select class="form-control" id="DisciplineSCDInput" name="disciplineSCDInput">
                            <option></option>
                            <c:forEach var="discipline" items="${disciplineList}">
                                <option value="${discipline.code}">${discipline.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label for="HoursSCDInput" class="col-lg-4 control-label">Carga Horária:</label>
                    <div class="col-lg-8">
                        <input type="number" class="form-control" id="HoursSCDInput" name="hoursSCDInput" value="${scd.hours}" placeholder="Carga Horária" />
                    </div>
                </div>
                <div class="form-group">
                    <label for="StartDateSCDInput" class="col-lg-4 control-label">Data(Início):</label>
                    <div class="col-lg-8">
                        <input type="date" class="form-control" id="StarDateSCDInput" name="startDateSCDInput" value="${scd.startDate}" placeholder="Hora(Início)" />
                    </div>
                </div>
                <div class="form-group">
                    <label for="EndDateSCDInput" class="col-lg-4 control-label">Data(Término):</label>
                    <div class="col-lg-8">
                        <input type="date" class="form-control" id="EndDateSCDInput" name="endDateSCDInput" value="${scd.endDate}" placeholder="Hora(Término)" />
                    </div>
                </div>
                <div class="form-group">
                    <label for="TeacherSCDInput" class="col-lg-4 control-label">Professor:</label>
                    <div class="col-lg-8">
                        <select class="form-control" id="TeacherSCDInput" name="teacherSCDInput">
                            <option></option>
                            <c:forEach var="tcher" items="${teacherList}">
                                <option value="${tcher.cpf}">${tcher.name}</option>
                            </c:forEach>
                        </select>
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
        $("#StudentClassDisciplineForm").validate({
            rules: {
                studentClassSCDInput:   "required",
                disciplineSCDInput:     "required",
                hoursSCDInput:          "required",
                startDateSCDInput:      "required",
                endDateSCDInput:        "required",
                teacherSCDInput:        "required"
            },
            
            highlight: function(element) {
                $(element).closest('.form-group').removeClass('has-success').addClass('has-error');
            },
            
            success: function(element) {
                $(element).closest('.form-group').removeClass('has-error').addClass('has-success');
            },
            
            messages: {
                studentClassSCDInput:   "Campo obrigatório.",
                disciplineSCDInput:     "Campo obrigatório.",
                hoursSCDInput:          "Campo obrigatório.",
                startDateSCDInput:      "Campo obrigatório.",
                endDateSCDInput:        "Campo obrigatório.",
                teacherSCDInput:        "Campo obrigatório."
            },
            
            errorClass: "text-danger"
        });
    });
</script>
