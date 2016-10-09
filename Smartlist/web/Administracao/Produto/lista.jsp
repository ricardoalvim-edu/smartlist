<%@page import="java.util.List"%>
<%@page import="org.smartlist.Model.Produto"%>
<%@page import="org.smartlist.Model.ListaConsumidor"%>
<%@page import="org.smartlist.DAO.InterfaceDAO.ListaConsumidorDAO"%>
<%@page import="org.smartlist.DAO.Hibernate.DAOFactory"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="lista" class="org.smartlist.DAO.Hibernate.HibernateListaConsumidorDAO" />
<!DOCTYPE html>
<html class="no-js">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sua Lista - Smartlist</title>
        <jsp:include page="../../head.jsp"/>
    </head>
    <header>
        <jsp:include page="../../header.jsp"/>
    </header>
    <body>
         <%  
            if (request.getParameter("listaId") == null) { %>
            <span>Não há parametros.</span>
        <% }     
        if (session.getAttribute("usuarioId").equals(null)){%>
            <h1>Você não tem permissão!</h1>
        <%
            }
        DAOFactory daoFactoryState = DAOFactory.getFactory();
        daoFactoryState.getListaConsumidorDAO().beginTransaction();
        ListaConsumidorDAO lDAO = daoFactoryState.getListaConsumidorDAO();
        ListaConsumidor lc = lDAO.getById(Integer.parseInt(request.getParameter("listaId")));
        List<Produto> produtos = lc.getProdutoList();
        if (produtos.isEmpty()){ %>
            <h1>Lista vazia</h1>
        <% } %>
        <%for (Produto p : produtos){%>
        <section id="contact-us">
            <div class="container">
                <div class="row">
                    <div class="col-md-12">
                        <div class="block">
                            <h1>Nome do produto: <%= p.getProNome()%></h1>
                            <h1>Categoria: <%= p.getCategprodIdFk().getCategprodNome()%></h1>
                            <h1>Estabelecimento: <%= p.getEstIdFk().getEstNome()%></h1>
                        </div>
                    </div><!-- .col-md-12 close -->
                </div><!-- .row close -->
            </div><!-- .container close -->
        </section><!-- #contact-us close -->
        <% lDAO.closeSession();} %>
    </body>
</html>
