<%-- 
    Document   : show
    Created on : Mar 20, 2015, 10:38:45 PM
    Author     : pedro
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <c:import url="../application_layouts/head.jsp"></c:import>
        <title>Controle de Frequência - Planilha Parcial</title>
    </head>
    <body>
        
        <c:import url="../application_layouts/navBar.jsp"></c:import>
        
        <div class="container-fluid">
            
            <div class="row">
                <div class="col-lg-offset-3 col-lg-6">
                    <div class="panel panel-primary">
                        <div class="panel-heading">
                            <h3 class="panel-title">Planilha Parcial</h3>
                        </div>
                        <div class="panel-body">
                            <p>Selecione sua opção:</p>
                            
                            <table class="table table-striped table-bordered">
                            <thead>
                                <tr>
                                    <th>#</th>
                                    <th>Arquivo</th>
                                    <th class="text-center">Opções</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td><h5>1</h5></td>
                                    <td><h5>PlanilhaParcial.xlsx</h5></td>
                                    <td class="text-center">
                                        <div class="btn-group" role="group" aria-label="...">
                                            <a class="btn btn-success btn-sm" href="download?sheet=Parcial&mode=attachment"><span class="glyphicon glyphicon-save"></span> Baixar</a>
                                            <a class="btn btn-primary btn-sm" href="download?sheet=Parcial&mode=inline" target="_blank"><span class="glyphicon glyphicon-open"></span> Pré-Visualizar</a>
                                        </div>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                        </div>
                    </div>
                </div>
            </div>
            
        </div>
        
        <c:import url="../application_layouts/footer.jsp"></c:import>
    </body>
</html>
