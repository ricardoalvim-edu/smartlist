<jsp:useBean id="estabelecimento" class="org.smartlist.DAO.Hibernate.HibernateEstabelecimentoDAO"/>
<%@page language="java" session="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Painel de Gerenciamento - Administrador do Estabelecimento - Smartlist</title>
        <jsp:include page="../../head.jsp"/>
    </head>
    <header>
        <jsp:include page="../../header.jsp"/>
    </header>
    <article style="max-width: 80%; margin: 0px auto; margin-top: 2%;">
    <% if ((session.getAttribute("tipo") == null) || (!session.getAttribute("tipo").equals("AdministradorEstabelecimento"))) { %>
        <h1 class="heading wow fadeInUp" data-wow-duration="500ms" data-wow-delay="300ms">Você não está autorizado a acessar essa página.</h1>
    <% } else { %>
        <c:if test="${empty estabelecimento.listAll()}">
            <h1 class="heading wow fadeInUp" data-wow-duration="500ms" data-wow-delay="300ms">Não existem estabelecimentos cadastrados! Cadastre um antes.</h1>
            <a href="${pageContext.request.contextPath}/Administracao/Estabelecimento/cadastro.jsp">Adicionar estabelecimento</a>
        </c:if>
        <c:if test="${not empty estabelecimento.listAll()}">
            <h2 class="heading wow fadeInUp" data-wow-duration="500ms" data-wow-delay="300ms">Produtos</h2>
            <a href="${pageContext.request.contextPath}/Administracao/Produto/cadastro.jsp">Adicionar produto</a>
            <br>
            <a href="${pageContext.request.contextPath}/Administracao/Produto/todos.jsp">Ver todos os produtos</a>
            <br>
            <a href="#">Procurar produtos</a>
        
            </c:if>
        <% } %>
    </article>
</html>
