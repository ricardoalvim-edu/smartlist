<%@page import="org.smartlist.Model.Produto"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="head.jsp"/>
<!DOCTYPE html>
<jsp:useBean id="produto" class="org.smartlist.DAO.Hibernate.HibernateProdutoDAO" />
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Todos os produtos</title>
        <link rel="stylesheet" type="text/css" href="//cdn.datatables.net/1.10.10/css/jquery.dataTables.css">
        <!-- jQuery -->
        <script type="text/javascript" charset="utf-8" src="//code.jquery.com/jquery-1.10.2.min.js"></script>
        <!-- DataTables -->
        <script type="text/javascript" charset="utf-8" src="//cdn.datatables.net/1.10.10/js/jquery.dataTables.js"></script>
        <script type="text/javascript" charset="utf-8" src="https://cdn.datatables.net/1.10.10/js/jquery.dataTables.min.js"></script>
        <script type="text/javascript" charset="utf-8" src="https://cdn.datatables.net/1.10.10/js/dataTables.bootstrap.min.js"></script>
        <script type="text/javascript">
            $(document).ready(function () {
                $('#example').DataTable({
                    "lengthMenu": [[5, 10, 20, -1], [5, 10, 20, "Todos"]]

                });
            });
        </script>
    </head>
    <header>
        <jsp:include page="header.jsp"/>
    </header>
    <body style="max-width: 80%; margin: 0px auto; margin-top: 4%;">
        <h1>Todos os produtos</h1>
        <table id="example" class="display display" cellspacing="0" width="100%">
            <thead>
                <tr>
                    <th>Produto</th>
                    <th>Preço</th>
                    <th>Peso</th>
                    <th>Uni.</th>
                    <th>Qtd.</th>
                    <th>Categoria</th>
                </tr>
            </thead>
            <tbody>
                <c:if test="${empty produto.listAll()}">
                    <h1>Não existem produtos ainda :(</h1>
                </c:if>
                <c:forEach var="produtos" items="${produto.listAll()}">
                        <tr>
                            <td>
                                <c:url value="Administracao/Produto/produto.jsp" 
                                       var="completeURL"> 
                                    <c:param name="id" value="${produtos.proId}" />
                                </c:url> <a href="${completeURL}">${produtos.proNome}</a></td>
                            <td>${produtos.proPreco}</td>
                            <td>${produtos.proPeso}</td>
                            <td>${produtos.proUnidade}</td>
                            <td>${produtos.proQuantidade}</td>
                            <td>${produtos.categprodIdFk.categprodNome}</td>                            
                        </tr>
                    </c:forEach>
            </tbody>
        </table>       
    </body>
</html>
