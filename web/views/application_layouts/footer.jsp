<%-- 
    Document   : footer
    Created on : Mar 20, 2015, 10:50:56 PM
    Author     : pedro
--%>

<%@page import="java.util.Calendar"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<footer class="footer" role="navigation">
    <div class="container">
        <div class="row">
            <div class="col-xs-6">
                <p class="text-muted text-left">Desenvolvido por Pedro Otávio e Pedro Papa</p>
            </div>
            <div class="col-xs-6">
                <p class="text-muted text-right"><%= Calendar.getInstance().get(Calendar.YEAR) %> Colégio Técnico - UFMG</p>
            </div>
        </div>
    </div>
</footer>
