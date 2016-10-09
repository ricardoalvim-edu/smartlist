<%@page language="java" session="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="cidade" class="org.smartlist.DAO.Hibernate.HibernateCidadeDAO"/>
<!DOCTYPE html>
<html class="no-js">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Página Inicial - Smartlist</title>
        <jsp:include page="head.jsp"/>
    </head>	
    <header>
        <jsp:include page="header.jsp"/>
    </header>
    <body style="margin-top: 4%;">
        <% if (session.getAttribute("tipo") == null) { %>
        <section id="about-us" style="background-image: url('imagens/cart-avenida.png'); background-position: 40% 12%;">
">
            <div class="container">
                <div class="row">
                    <div class="col-md-12">
                        <div class="block">   
                            <h1 class="heading wow fadeInUp" data-wow-duration="500ms" data-wow-delay="300ms" style="color: white">Bem vindo ao Smartlist!</h1>
                            <p class="wow fadeInUp" data-wow-duration="300ms" data-wow-delay="600ms" style="color: white">O SmartList permite que você faça uma lista de compras com os produtos dos melhores estabelecimentos que estão sempre perto de você.</p>
                            <a class="btn btn-default wow bounceIn btn-primary" data-wow-duration="500ms" data-wow-delay="1300ms" href="${pageContext.request.contextPath}/Cadastro/Usuario/cadastro.jsp" role="button">Cadastre-se!</a>                    
                            <a class="btn btn-default wow bounceIn" data-wow-duration="500ms" data-wow-delay="1300ms" href="${pageContext.request.contextPath}/Cadastro/Usuario/login.jsp" role="button">Entre</a>

                        </div><!-- .col-md-12 close -->
                    </div><!-- .row close -->
                </div><!-- .container close -->
        </section>
        <% } else if (session.getAttribute("tipo").equals("AdministradorSistema")) { %>
        <h1 class="heading wow fadeInUp" data-wow-duration="500ms" data-wow-delay="300ms">Você está logado como administrador do sistema.</h1>
        <a class="btn btn-default wow bounceIn" data-wow-duration="500ms" data-wow-delay="1300ms" href="${pageContext.request.contextPath}/Cadastro/AdmSys/painel.jsp" role="button">Pagina de Administração</a>    
        <% } else if (session.getAttribute("tipo").equals("AdministradorEstabelecimento")) { %>
        <h1 class="heading wow fadeInUp" data-wow-duration="500ms" data-wow-delay="300ms">Você está logado como administrador do estabelecimento.</h1>
        <a class="btn btn-default wow bounceIn" data-wow-duration="500ms" data-wow-delay="1300ms" href="${pageContext.request.contextPath}/Cadastro/AdmEst/painel.jsp" role="button">Pagina de Administração</a>    
        <% } else if (session.getAttribute("tipo").equals("Consumidor")) { %>
        <script>
            function showCidades(str, cidade) {
                var xhttp;
                document.getElementById("estabelecimentosCity").innerHTML = "Carregando...";
                document.getElementById("defCity").innerHTML = cidade;
                if (str == "") {
                    document.getElementById("estabelecimentosCity").innerHTML = "";
                    return;
                }
                xhttp = new XMLHttpRequest();
                xhttp.onreadystatechange = function () {
                    if (xhttp.readyState == 4 && xhttp.status == 200) {
                        document.getElementById("estabelecimentosCity").innerHTML = xhttp.responseText;
                    }
                };
                xhttp.open("GET", "Administracao/Estabelecimento/todosCidade.jsp?cidadeId=" + str, true);
                xhttp.send();
            }
        </script>
        <form action="" style="margin: 0px auto; width: 200px"> 
          <ul class="nav navbar-nav">
            <li class="dropdown">
              <a id="defCity" href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="true">
                 Escolha uma cidade<span class="caret"></span>
              </a>
                <ul class="dropdown-menu active">
                <c:forEach var="cidades" items="${cidade.listAll()}">
                    <li><a id="${cidades.cidadeNome}" onclick="showCidades(${cidades.cidadeId}, this.id)">${cidades.cidadeNome} - ${cidades.estadoIdFk.estadoSigla}</a></li>
                </c:forEach>
                </ul>
          </li>
          </ul>
        </form>
        <br>
        <div id="estabelecimentosCity" style="height: 200px"></div>
        <% }%>
        <jsp:include page="footer.jsp"/>
</html>