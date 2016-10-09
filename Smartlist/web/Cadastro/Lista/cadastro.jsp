<%@page language="java" session="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html class="no-js">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cadastro da Lista - Smartlist</title>
        <jsp:include page="../../head.jsp"/>
    </head>	
    <header>
        <jsp:include page="../../header.jsp"/>
    </header>
    <body>
        <% if ((session.getAttribute("logado")) == null || (!session.getAttribute("tipo").equals("Consumidor"))) { %>
            <h1 class="heading wow fadeInUp" data-wow-duration="500ms" data-wow-delay="300ms">Você não está logado! Cadastre-se antes de fazer essa operação.</h1>
        <% } else { %>
        <section id="contact-us">
            <div class="container">
                <div class="row">
                    <div class="col-md-12">
                        <div class="block">
                            <h1 class="heading wow fadeInUp" data-wow-duration="500ms" data-wow-delay="300ms">Adicionar <span>LISTA</span></h1>
                            <form action="../../acao" method="post">
                                <div class="form-group wow fadeInDown" data-wow-duration="500ms" data-wow-delay="600ms">
                                    <input type="text" name='nome' class="form-control" placeholder="Nome da sua lista" required>
                                </div>
                                <input type="hidden" name="logica" value="CadastroLista">
                                <button type="submit" class="btn btn-default wow bounceIn" data-wow-duration="500ms" data-wow-delay="1300ms">Cadastrar</button>
                            </form>
                        </div>
                    </div><!-- .col-md-12 close -->
                </div><!-- .row close -->
            </div><!-- .container close -->
        </section><!-- #contact-us close -->
        <% } %>
        <jsp:include page="../../footer.jsp"/>
</html>