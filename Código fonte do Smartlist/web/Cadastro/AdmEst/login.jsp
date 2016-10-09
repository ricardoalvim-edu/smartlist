<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Entre - Smartlist</title>
        <jsp:include page="../../head.jsp"/>
    </head>
    <header>
    </header>
	<body>
        <% if ((session.getAttribute("logado")) != null) { %>
            <h1 class="heading wow fadeInUp" data-wow-duration="500ms" data-wow-delay="300ms">Você já está logado!</h1>
        <% } else { %>
	<section id="subscribe">
        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <div class="block">
                        <h1 class=" heading wow fadeInUp" data-wow-duration="300ms" data-wow-delay="300ms"><span>Login - Administrador do Estabelecimento</span></h1>
                        <p class="wow fadeInUp" data-wow-duration="300ms" data-wow-delay="400ms"></p>
                        <form action="../../acao" method="post" class="form-inline">
                            <input type="hidden" name="logica" value="LoginAdmEst">
                            <div class="form-group">
                                <div class="input-group">
                                    <input style="margin: 1% 1% 1% 1%;" type="text" class="form-control" placeholder="Seu usuário" name="usuario" required/>
                                    <input style="margin: 1% 1% 1% 1%;" type="password" class="form-control" placeholder="Sua senha" name="senha" required/>
                                    <div class="input-group-addon" >
                                        <button type="submit" class="btn btn-default wow bounceIn" data-wow-duration="500ms" data-wow-delay="1300ms">Entre</button>
                                    </div>
                                </div>
                            </div>
                        </form>
                    </div>
                </div><!-- .col-md-12 close -->
            </div><!-- .row close -->
        </div><!-- .containe close -->
    </section><!-- #subscribe close -->
    <% } %>
    <jsp:include page="../../footer.jsp"/>
</html>