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
    <body>
        <table id="example" class="display display" cellspacing="0" style="width: 90%; margin: 0px auto;">
            <thead>
                <tr>
                    <th>Estabelecimento</th>
                    <th>Tipo</th>
                    <th>Produtos</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="estabelecimentos" items="${estabelecimento.ListByCidade(param.cidadeId)}">
                    <tr>
                        <td><c:url value="/Administracao/Estabelecimento/estabelecimento.jsp" 
                                       var="completeURL"> 
                                    <c:param name="id" value="${estabelecimentos.estId}" />                                    
                                </c:url> <a href="${completeURL}">${estabelecimentos.estNome}</a></td>
                        <td>${estabelecimentos.categestIdFk.catEstNome}</td>
                        <td><c:url value="/Administracao/Produto/todosConsumidor.jsp" 
                                       var="completeURL"> 
                                    <c:param name="estabelecimentoId" value="${estabelecimentos.estId}" />
                                    <c:param name="estabelecimentoNome" value="${estabelecimentos.estNome}"/>
                                </c:url> <a href="${completeURL}">Ver produtos</a></td>
                        </td>
                   </tr>
                </c:forEach>
            </tbody>
        </table>
    </body>
</html>
