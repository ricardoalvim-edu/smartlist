<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="org.smartlist.DAO.InterfaceDAO.ProdutoDAO"%>
<%@page import="org.smartlist.DAO.Hibernate.DAOFactory"%>
<%@page import="org.smartlist.Model.Produto"%>
<jsp:useBean id="list" class="org.smartlist.DAO.Hibernate.HibernateListaConsumidorDAO" />
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Produto</title>
        <jsp:include page="../../head.jsp"/>
    </head>
    <header>
        <jsp:include page="../../header.jsp"/>
    </header>
    <body style="max-width: 80%; margin: 0px auto; margin-top: 4%;">
        
        <%  Produto p = new Produto();
            if (request.getParameter("id") == null) { %>
            <span>Não há argumentos.</span>
        <% } else { 
            Integer id = Integer.parseInt(request.getParameter("id"));
            DAOFactory daoFactory = DAOFactory.getFactory();
            ProdutoDAO pDAO = daoFactory.getProdutoDAO();
            pDAO.beginTransaction();
            p = (Produto) pDAO.getById(id);
            pDAO.closeSession();
        %>
           
        <% } %>
        <br>
        <h1><%= p.getProNome() %></h1>
        <h2>Categoria: <%= p.getCategprodIdFk().getCategprodNome()%></h2>
        <h2>Preço: R$<%= p.getProPreco()%></h2>
        <% if (session.getAttribute("tipo").equals("Consumidor")) {%>
        <c:if test="${not empty list.ListListasByUsuario(sessionScope['id'])}">
            <ul class="nav navbar-nav navbar-right">
            <li class="dropdown">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="true">
                 Adicionar a lista<span class="caret"></span>
              </a>
              <ul class="dropdown-menu active">
                <c:forEach var="listas" items="${list.ListListasByUsuario(sessionScope['id'])}">
                    <c:url value="/AddToList" 
                                       var="completeURL"> 
                                    <c:param name="produtoId" value="<%= p.getProId()+"" %>" />
                                    <c:param name="listaId" value="${listas.lstId}" />
                    </c:url><li> <a href="${completeURL}">${listas.lstNome}</a></li></td>
                </c:forEach>
                    <hr>
                    <li><a href="Cadastro/Lista/Cadastro.jsp">Adicionar uma lista</a></li>
              </ul>
            </li>
          </ul>
        </c:if>
        <% } %>
    </body>
</html>
