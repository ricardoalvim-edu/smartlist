<jsp:useBean id="cidade" class="org.smartlist.DAO.Hibernate.HibernateCidadeDAO"/>
<jsp:useBean id="categoria" class="org.smartlist.DAO.Hibernate.HibernateCategoriaProdutoDAO"/>
<jsp:useBean id="adm" class="org.smartlist.DAO.Hibernate.HibernateAdministradorEstabelecimentoDAO"/>
<%@page language="java" session="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cadastro de novo Produto - Smartlist</title>
        <jsp:include page="../../head.jsp"/>
    </head>
    <header>
        <jsp:include page="../../header.jsp"/>
    </header>
    <body style="max-width: 80%; margin: 0px auto; margin-top: 2%;">
        <% if ((session.getAttribute("tipo") == null) || (!session.getAttribute("tipo").equals("AdministradorEstabelecimento"))) { %>
        <h1 class="heading wow fadeInUp" data-wow-duration="500ms" data-wow-delay="300ms">Você não está autorizado a acessar essa página!</h1>
        <% } else { %>
        <c:if test="${empty cidade.listAll()}">
            <h1 class="heading wow fadeInUp" data-wow-duration="500ms" data-wow-delay="300ms">Não existem Cidades cadastradas! Cadastre uma antes.</h1>
        </c:if>
        <c:if test="${empty categoria.listAll()}">
            <h1 class="heading wow fadeInUp" data-wow-duration="500ms" data-wow-delay="300ms">Não existem categorias para produtos cadastradas! Cadastre uma antes.</h1>
        </c:if>
        <c:if test="${not empty cidade.listAll()}" >
            <c:if test="${not empty categoria.listAll()}">
                    <section id="contact-us">
                        <div class="container">
                            <div class="row">
                                <div class="col-md-12">
                                    <div class="block">
                                        <h1 class="heading wow fadeInUp" data-wow-duration="500ms" data-wow-delay="300ms">Adicionar <span>PRODUTO</span></h1>
                                        <form action="../../acao" method="post">
                                            <div class="form-group wow fadeInDown" data-wow-duration="500ms" data-wow-delay="600ms">
                                                <input type="text" name='nome' class="form-control" placeholder="Nome do produto" required>
                                            </div>
                                            <div class="form-group wow fadeInDown" data-wow-duration="500ms" data-wow-delay="800ms">
                                                <input type="text" name='preco' class="form-control" placeholder="Preço (R$)" required>
                                            </div>
                                            <div class="form-group wow fadeInDown" data-wow-duration="500ms" data-wow-delay="800ms">
                                                <input type="number" name='quantidade' class="form-control" placeholder="Quantidade" required>
                                            </div>
                                            <div class="form-group wow fadeInDown" data-wow-duration="500ms" data-wow-delay="800ms">
                                                <input type="text" name='peso' class="form-control" placeholder="Peso" required>
                                            </div>
                                            <div class="form-group wow fadeInDown" data-wow-duration="500ms" data-wow-delay="800ms">
                                                <input type="text" name='unidade' class="form-control" placeholder="Unidade de Medida" required>
                                            </div>
                                            <input type="hidden" name="logica" value="CadastroProduto">
                                            <p>Categoria</p>
                                            <select name="categoria">
                                                <c:forEach var="categorias" items="${categoria.listAll()}">
                                                    <option value="${categorias.categprodId}">${categorias.categprodNome}</option>
                                                </c:forEach>
                                            </select>                                    
                                            <button type="submit" class="btn btn-default wow bounceIn" data-wow-duration="500ms" data-wow-delay="1300ms">Cadastrar</button>
                                        </form>
                                    </div>
                                </div><!-- .col-md-12 close -->
                            </div><!-- .row close -->
                        </div><!-- .container close -->
                    </section><!-- #contact-us close -->
                </c:if>
            </c:if>
        <% }%>
        <jsp:include page="../../footer.jsp"/>
</html>