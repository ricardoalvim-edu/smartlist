<jsp:useBean id="cidade" class="org.smartlist.DAO.Hibernate.HibernateCidadeDAO"/>
<jsp:useBean id="categoria" class="org.smartlist.DAO.Hibernate.HibernateCategoriaEstabelecimentoDAO"/>
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
    <body style="max-width: 80%; margin: 0px auto; margin-top: 2%;">
        <% if ((session.getAttribute("tipo") == null) || (!session.getAttribute("tipo").equals("AdministradorSistema"))) { %>
        <h1 class="heading wow fadeInUp" data-wow-duration="500ms" data-wow-delay="300ms">Você não está autorizado a acessar essa página.</h1>
        <% } else { %>
        <c:if test="${empty cidade.listAll()}">
            <h1 class="heading wow fadeInUp" data-wow-duration="500ms" data-wow-delay="300ms">Não existem Cidades cadastrados! Cadastre um antes.</h1>
        </c:if>
        <c:if test="${empty categoria.listAll()}">
            <h1 class="heading wow fadeInUp" data-wow-duration="500ms" data-wow-delay="300ms">Não existem categorias para estabelecimento cadastradas! Cadastre um antes.</h1>
        </c:if>    
        <c:if test="${not empty cidade.listAll()}">
            <c:if test="${not empty categoria.listAll()}">
                <section id="contact-us">
                    <div class="container">
                        <div class="row">
                            <div class="col-md-12">
                                <div class="block">
                                    <h1 class="heading wow fadeInUp" data-wow-duration="500ms" data-wow-delay="300ms">Adicionar <span>ESTABELECIMENTO</span></h1>
                                    <form action="../../acao" method="post">
                                        <div class="form-group wow fadeInDown" data-wow-duration="500ms" data-wow-delay="600ms">
                                            <input type="text" name='nome' class="form-control" placeholder="Nome do estabelecimento" required>
                                        </div>
                                        <div class="form-group wow fadeInDown" data-wow-duration="500ms" data-wow-delay="800ms">
                                            <input type="text" name='razaosocial' class="form-control" placeholder="Razão social" required>
                                        </div>
                                        <div class="form-group wow fadeInDown" data-wow-duration="500ms" data-wow-delay="800ms">
                                            <input type="text" name='CNPJ' id="CNPJ" class="form-control" placeholder="CNPJ" required>
                                        </div>
                                        <div class="form-group wow fadeInDown" data-wow-duration="500ms" data-wow-delay="800ms">
                                            <input type="text" name='logradouro' class="form-control" placeholder="Rua do estabelecimento" required>
                                        </div>
                                        <div class="form-group wow fadeInDown" data-wow-duration="500ms" data-wow-delay="800ms">
                                            <input type="number" name='numerorua' class="form-control" placeholder="Numero (endereço) do estabelecimento" required>
                                        </div>
                                        <div class="form-group wow fadeInDown" data-wow-duration="500ms" data-wow-delay="800ms">
                                            <input type="text" name='bairro' class="form-control" placeholder="Bairro" required>
                                        </div>
                                        <div class="form-group wow fadeInDown" data-wow-duration="500ms" data-wow-delay="800ms">
                                            <input type="text" name='CEP' class="form-control" placeholder="CEP" required>
                                        </div>
                                        <div class="form-group wow fadeInDown" data-wow-duration="500ms" data-wow-delay="800ms">
                                            <input type="text" name='lat' class="form-control" placeholder="Latitude" required>
                                        </div>
                                        <div class="form-group wow fadeInDown" data-wow-duration="500ms" data-wow-delay="800ms">
                                            <input type="text" name='lon' class="form-control" placeholder="Longitude" required>
                                        </div>
                                        <div class="form-group wow fadeInDown" data-wow-duration="500ms" data-wow-delay="800ms">
                                            <input type="hidden" name="logica" value="CadastroEstabelecimento">
                                            <p>Categoria do estabelecimento</p>
                                            <select name="categoria">
                                                <c:forEach var="categorias" items="${categoria.listAll()}">
                                                    <option value="${categorias.catEstId}">${categorias.catEstNome}</option>
                                                </c:forEach>
                                            </select>
                                            <br></br>
                                            <p>Cidade do estabelecimento</p>
                                            <select name="cidade">
                                                <c:forEach var="cidades" items="${cidade.listAll()}">
                                                    <option value="${cidades.cidadeId}">${cidades.cidadeNome} - ${cidades.estadoIdFk.estadoSigla}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
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
        <a href="${pageContext.request.contextPath}/Administracao/Estado/todos.jsp">Todas os estados</a>
    </body>
    <jsp:include page="../../footer.jsp"/>
</html>