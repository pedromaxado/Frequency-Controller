<%-- 
    Document   : _form
    Created on : Mar 9, 2015, 5:36:59 PM
    Author     : pedro
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="panel panel-primary">
    <div class="panel-heading">
        <h3 class="panel-title text-center">Adicionar Turma</h3>
    </div>
    <div class="panel-body">
        <form id="StudentClassForm" action="ctrl" method="post" role="form" class="form-horizontal">
            <fieldset>
                <input type="hidden" name="op" value="StudentClassController" />
                <input type="hidden" name="action" value="create" />
                <div class="form-group">
                    <label for="StudentClassCodeInput" class="col-lg-4 control-label">Código:</label>
                    <div class="col-lg-8">
                        <input type="text" class="form-control" id="StudentClassCodeInput" name="studentClassCodeInput" value="${sc.code}" placeholder="Código" />
                    </div>
                </div>
                <div class="form-group">
                    <label for="CourseInput" class="col-lg-4 control-label">Curso:</label>
                    <div class="col-lg-8">
                        <input type="text" class="form-control" id="CourseInput" name="courseInput" value="${sc.course}" placeholder="Curso" />
                    </div>
                </div>
                <div class="form-group">
                    <label for="StartTimeInput" class="col-lg-4 control-label">Hora(Início):</label>
                    <div class="col-lg-8">
                        <input type="time" class="form-control" id="StarTimeInput" name="starTimeInput" value="${sc.startTime}" placeholder="Hora(Início)" />
                    </div>
                </div>
                <div class="form-group">
                    <label for="EndTimeInput" class="col-lg-4 control-label">Hora(Término):</label>
                    <div class="col-lg-8">
                        <input type="time" class="form-control" id="EndTimeInput" name="endTimeInput" value="${sc.endTime}" placeholder="Hora(Término)" />
                    </div>
                </div>
                <div class="form-group">
                    <label for="ShiftInput" class="col-lg-4 control-label">Turno:</label>
                    <div class="col-lg-8">
                        <input type="text" class="form-control" id="ShitInput" name="shiftInput" value="${sc.shift}" placeholder="Turno" />
                    </div>
                </div>
                <div class="form-group">
                    <label for="StudentClassPoloInput" class="col-lg-4 control-label">Polo:</label>
                    <div class="col-lg-8">
                        <select class="form-control" id="StudentClassPoloInput" name="studentClassPoloInput">
                            <c:forEach var="polo" items="${poloList}">
                                <option value="${polo.id}">${polo.name}</option>
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
        $("#StudentClassForm").validate({
            rules: {
                studentClassCodeInput: {
                    required: true,
                    number: true
                },
                courseInput:    "required",
                startTimeInput: "required",
                endTimeInput:   "required",
                shiftInput:     "required"
            },
            
            highlight: function(element) {
                $(element).closest('.form-group').removeClass('has-success').addClass('has-error');
            },
            
            success: function(element) {
                $(element).closest('.form-group').removeClass('has-error').addClass('has-success');
            },
            
            messages: {
                studentClassCodeInput: {
                    required:   "Campo obrigatório.",
                    number:     "Digite apenas números!"
                },
                courseInput:    "Campo obrigatório.",
                startTimeInput: "Campo obrigatório.",
                endTimeInput:   "Campo obrigatório.",
                shiftInput:     "Campo obrigatório."
            },
            
            errorClass: "text-danger"
        });
    });
</script>