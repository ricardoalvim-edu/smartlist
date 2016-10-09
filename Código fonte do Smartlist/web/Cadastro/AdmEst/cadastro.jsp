<jsp:useBean id="estabelecimento" class="org.smartlist.DAO.Hibernate.HibernateEstabelecimentoDAO"/>
<%@page language="java" session="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cadastro de novo administrador do estabelecimento - Smartlist</title>
        <jsp:include page="../../head.jsp"/>
    </head>
    <header>
        <jsp:include page="../../header.jsp"/>
    </header>
    <body>
        <% if ((session.getAttribute("tipo") == null) || (!session.getAttribute("tipo").equals("AdministradorSistema"))) { %>
        <h1 class="heading wow fadeInUp" data-wow-duration="500ms" data-wow-delay="300ms">Você não está autorizado a acessar essa página.</h1>
        <% } else { %>   
        <c:if test="${empty estabelecimento.listAll()}">
            <h1 class="heading wow fadeInUp" data-wow-duration="500ms" data-wow-delay="300ms">Não existem estabelecimentos cadastrados! Cadastre um antes.</h1>
        </c:if>
        <c:if test="${not empty estabelecimento.listAll()}">   
            <section id="subscribe">
                <div class="container">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="block">
                                <h1 class=" heading wow fadeInUp" data-wow-duration="300ms" data-wow-delay="300ms"><span>Cadastro - Administração do Estabelecimento</span></h1>
                                <p class="wow fadeInUp" data-wow-duration="300ms" data-wow-delay="400ms"></p>
                                <form action="../../acao" method="post" class="form-inline">
                                    <input type="hidden" name="logica" value="CadastroAdmEst">
                                    <div class="form-group">
                                        <div class="input-group">
                                            <input style="margin: 1% 1% 1% 1%;" name='nome' type="text" class="form-control" placeholder="Seu nome completo" required/>
                                            <input style="margin: 1% 1% 1% 1%;" name='usuario' type="text" class="form-control" placeholder="Seu usuário" required/>
                                            <input style="margin: 1% 1% 1% 1%;" name='senha' type="password" class="form-control" placeholder="Sua senha" required/>
                                            <input style="margin: 1% 1% 1% 1%;" name='email' type="email" class="form-control" placeholder="Seu email" required/>
                                            <p>Estabelecimento</p>
                                            <select name="estabelecimento">
                                                <c:forEach var="estabelecimentos" items="${estabelecimento.listAll()}">
                                                    <option value="${estabelecimentos.estId}">${estabelecimentos.estNome}</option>
                                                </c:forEach>
                                            </select>
                                            <div class="input-group-addon">
                                                <button style="margin: 2% 2% 2% 2%;" class="btn">Cadastrar</button>
                                            </div>
                                        </div>
                                    </div>
                                </form>
                            </c:if>
                            <% }%>
                        </div>
                    </div><!-- .col-md-12 close -->
                </div><!-- .row close -->
            </div><!-- .containe close -->
        </section><!-- #subscribe close -->
        <jsp:include page="../../footer.jsp"/>
</html>
