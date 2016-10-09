<%@page import="org.smartlist.DAO.InterfaceDAO.EstabelecimentoDAO"%>
<%@page import="org.smartlist.DAO.Hibernate.DAOFactory"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="org.smartlist.Model.Estabelecimento"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Estabelecimento</title>
        <jsp:include page="../../head.jsp"/>
        <script
            src="http://maps.googleapis.com/maps/api/js">
        </script>
        <%  Estabelecimento e = new Estabelecimento();
            if (request.getParameter("id") == null) { %>
        <span>Fail! Estabelecimento nulo!</span>
        <% } else {
            Integer id = Integer.parseInt(request.getParameter("id"));
            DAOFactory daoFactory = DAOFactory.getFactory();
            EstabelecimentoDAO eDAO = daoFactory.getEstabelecimentoDAO();
            eDAO.beginTransaction();
            e = (Estabelecimento) eDAO.getById(id);
            eDAO.closeSession();%>
        <% }%>
        <script>
            var myCenter = new google.maps.LatLng(<%= e.getEstLat()%>, <%= e.getEstLong()%>);
            var marker;

            function initialize()
            {
                var mapProp = {
                    center: myCenter,
                    zoom: 15,
                    mapTypeId: google.maps.MapTypeId.ROADMAP
                };

                var map = new google.maps.Map(document.getElementById("googleMap"), mapProp);

                var marker = new google.maps.Marker({
                    position: myCenter,
                    animation: google.maps.Animation.BOUNCE
                });

                marker.setMap(map);
                
                var infowindow = new google.maps.InfoWindow({
                    content:"<b><%= e.getEstNome()%></b></br><%= e.getEstLogradouro()%> - <%= e.getEstBairro()%> em <%= e.getCidadeIdFk().getCidadeNome()%>"
                });

                infowindow.open(map,marker);
            }
            

            google.maps.event.addDomListener(window, 'load', initialize);
        </script>
    </head>
    <header>
        <jsp:include page="../../header.jsp"/>
    </header>
    <body style="max-width: 80%; margin: 0px auto; margin-top: 4%;">

        
        <img src="${pageContext.request.contextPath}/imagens/<%= e.getUrl_imagem()%>" style="
             width: 180px;
             display: block;
             float: left;
             margin: 1% 1% 1% 1%;
             ">

        <h1><%= e.getEstNome()%></h1>
        <hr>
        <span><%= e.getCategestIdFk().getCatEstNome()%></span>
        <div id="googleMap" style="width:640px;height:320px;"></div>
                  
        <c:url value="/Administracao/Produto/todosConsumidor.jsp" 
               var="completeURL"> 
            <c:param name="estabelecimentoId" value="<%= e.getEstId().toString()%>" />
            <c:param name="estabelecimentoNome" value="<%= e.getEstNome()%>"/>
         
        </c:url> <a href="${completeURL}">Ver produtos</a>
        
    </body>
</html>
