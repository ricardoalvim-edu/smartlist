<jsp:useBean id="estado" class="org.smartlist.DAO.Hibernate.HibernateEstadoDAO"/>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html class="no-js">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cadastro da Cidade - Smartlist</title>
        <jsp:include page="../../head.jsp"/>
    </head>
    <header>
        <jsp:include page="../../header.jsp"/>
    </header>
    <body style="max-width: 80%; margin: 0px auto; margin-top: 2%;">
        <% if ((session.getAttribute("tipo") == null) || (!session.getAttribute("tipo").equals("AdministradorSistema"))) { %>
        <h1 class="heading wow fadeInUp" data-wow-duration="500ms" data-wow-delay="300ms">Você não está autorizado a acessar essa página.</h1>
        <% } else { %> 
        <section id="contact-us">
            <div class="container">
                <div class="row">
                    <div class="col-md-12">
                        <div class="block">
                            <h1 class="heading wow fadeInUp" data-wow-duration="500ms" data-wow-delay="300ms">Adicionar <span>CIDADE</span></h1>
                            <c:if test="${empty estado.listAll()}">
                                <h1 class="heading wow fadeInUp" data-wow-duration="500ms" data-wow-delay="300ms">Não existem estados cadastrados! Cadastre um antes.</h1>
                            </c:if>
                            <c:if test="${not empty estado.listAll()}">   
                                <form action="../../acao" method="post">
                                    <input type="hidden" name="logica" value="CadastroCidade">
                                    <div class="form-group wow fadeInDown" data-wow-duration="500ms" data-wow-delay="600ms">
                                        <input type="text" name="cidade" class="form-control" placeholder="Nome da cidade" required>
                                    </div>
                                    <div class="form-group wow fadeInDown" data-wow-duration="500ms" data-wow-delay="800ms">
                                        <select name="estado">
                                            <c:forEach var="estados" items="${estado.listAll()}">
                                                <option value="${estados.estadoId}">${estados.estadoNome}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                    <button type="submit" class="btn btn-default wow bounceIn" data-wow-duration="500ms" data-wow-delay="1300ms">Cadastrar</button>
                                </form>
                            </c:if>
                        </div>
                    </div><!-- .col-md-12 close -->
                </div><!-- .row close -->
            </div><!-- .container close -->
        </section><!-- #contact-us close -->
        <% }%> 
        <a href="${pageContext.request.contextPath}/Administracao/Cidade/todos.jsp">Todas as cidades</a>
    </body>
    <jsp:include page="../../footer.jsp"/>
</html>
