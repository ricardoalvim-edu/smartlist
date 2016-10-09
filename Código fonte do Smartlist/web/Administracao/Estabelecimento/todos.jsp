<%-- 
    Document   : todos.jsp
    Created on : 08/11/2015, 13:12:03
    Author     : ricar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../../head.jsp"/>
<jsp:useBean id="estabelecimento" class="org.smartlist.DAO.Hibernate.HibernateEstabelecimentoDAO"/>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Todas os Estabelecimentos</title>
        <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/s/dt/dt-1.10.10/datatables.min.css"/>
        <script type="text/javascript">
            $(document).ready(function () {
                $('#example').DataTable({
                    "lengthMenu": [[5, 10, 20, -1], [5, 10, 20, "Todos"]]

                });
            });
        </script>
    </head>
    <header>
        <jsp:include page="../../header.jsp"/>
    </header>
    <body style="max-width: 80%; margin: 0px auto; margin-top: 4%;">
        <h1>Todas os estabelecimentos</h1>
        <table id="example" class="display display" cellspacing="0" width="100%">
            <thead>
                <tr>
                    <th>Estabelecimento</th>
                    <th>Tipo</th>
                    <th>Cidade</th>
                    <th>Estado</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="estabelecimentos" items="${estabelecimento.listAll()}">
                    <tr>
                        <td>${estabelecimentos.estNome}</td>
                        <td>${estabelecimentos.categestIdFk.catEstNome}</td>
                        <td>${estabelecimentos.cidadeIdFk.cidadeNome}</td>
                        <td>${estabelecimentos.cidadeIdFk.estadoIdFk.estadoNome}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <a href="${pageContext.request.contextPath}/Administracao/Estabelecimento/cadastro.jsp">Adicionar estabelecimento</a>
        <br>
        <a href="${pageContext.request.contextPath}/Administracao/Cidade/cadastro.jsp">Adicionar cidade</a>
        <br>
        <a href="${pageContext.request.contextPath}/Administracao/Estado/cadastro.jsp">Adicionar estado</a>
        <br>
        <a href="${pageContext.request.contextPath}/Cadastro/AdmSys/painel.jsp">Painel de Administração</a>
    </body>
</html>
