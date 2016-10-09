<%@page language="java" session="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html class="no-js">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cadastro do Estabelecimento - Smartlist</title>
        <jsp:include page="../../head.jsp"/>
    </head>
    <header>
        <jsp:include page="../../header.jsp"/>
    </header>
        <% if ((session.getAttribute("tipo") == null) || (!session.getAttribute("tipo").equals("AdministradorSistema")) || (session.getAttribute("obj_up") == null)) { %>
            <h1 class="heading wow fadeInUp" data-wow-duration="500ms" data-wow-delay="300ms">Você não está autorizado a acessar essa página.</h1>
        <% } else { %>
            <body style="max-width: 80%; margin: 0px auto; margin-top: 2%;">  
            <section id="contact-us">
                <div class="container">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="block">
                                <h1 class="heading wow fadeInUp" data-wow-duration="500ms" data-wow-delay="300ms">Adicione uma imagem para o seu estabelecimento</h1>
                                <form action="${pageContext.request.contextPath}/ServletUpload" method="post" enctype="multipart/form-data">
                                    <input type="file" name="file" accept=".png,.jpg"/>     
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