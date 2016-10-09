<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page language="java" session="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../../head.jsp"/>
<jsp:useBean id="lista" class="org.smartlist.DAO.Hibernate.HibernateListaConsumidorDAO"/>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Todas as suas listas</title>
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
    <body>
        <br>
        <table id="example" class="display display" cellspacing="0" style="width: 90%; margin: 0px auto;">
            <thead>
                <tr>
                    <th>Nome da lista</th>
                    <th>Ver lista</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="listas" items="${lista.getByUsuario(sessionScope.id)}">
                    <tr>
                        <td>${listas.lstNome}</td>
                        <td><c:url value="/Administracao/Produto/lista.jsp" 
                                       var="completeURL"> 
                                    <c:param name="listaId" value="${listas.lstId}" />
                                </c:url> <a href="${completeURL}">Ver lista</a></td>
                        
                   </tr>
                </c:forEach>
            </tbody>
        </table>
    </body>
</html>

