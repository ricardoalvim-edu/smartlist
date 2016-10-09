<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../../head.jsp"/>
<jsp:useBean id="catEstab" class="org.smartlist.DAO.Hibernate.HibernateCategoriaEstabelecimentoDAO"/>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Todas as categorias de estabelecimento</title>
        <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/s/dt/dt-1.10.10/datatables.min.css"/>
        <script type="text/javascript" src="https://cdn.datatables.net/s/dt/dt-1.10.10/datatables.min.js"></script>
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
        <h1>Todas as categorias de estabelecimento</h1>
        <table id="example" class="display display" cellspacing="0" width="100%">
            <thead>
                <tr>
                    <th>Categoria</th>
                    <th>Descrição</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="catEstabs" items="${catEstab.listAll()}">
                    <tr>
                        <td>${catEstabs.catEstNome}</td>
                        <td>${catEstabs.catEstDescricao}</td>
                    </tr>
                </c:forEach>
            </tbody>
    </body>
</html>