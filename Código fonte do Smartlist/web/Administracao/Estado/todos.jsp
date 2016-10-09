<%-- 
    Document   : todos.jsp
    Created on : 08/11/2015, 13:12:03
    Author     : ricar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../../head.jsp"/>
<jsp:useBean id="estado" class="org.smartlist.DAO.Hibernate.HibernateEstadoDAO"/>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Todos os estados</title>
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
        <jsp:include page="../../header.jsp"/>
    </header>
    <body style="max-width: 80%; margin: 0px auto; margin-top: 2%;">
        <br>
        <h1>Todos os estados</h1>
        <table id="example" class="display display" cellspacing="0" width="100%">
            <thead>
                <tr>
                    <th>Estado</th>
                    <th>Sigla</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="estados" items="${estado.listAll()}">
                    <tr>
                        <td>${estados.estadoNome}</td>
                        <td>${estados.estadoSigla}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <a href="${pageContext.request.contextPath}/Administracao/Estado/cadastro.jsp">Adicionar estado</a>
        <br>
        <a href="${pageContext.request.contextPath}/Cadastro/AdmSys/painel.jsp">Painel de Administração</a>
    </body>
</html>
