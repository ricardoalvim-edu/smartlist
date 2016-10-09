<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cadastro de novo administrador do sistema - Smartlist</title>
        <jsp:include page="../../head.jsp"/>
    </head>
     <header>
        <jsp:include page="../../header.jsp"/>
    </header>
    <body>
        <%if ((session.getAttribute("tipo") == null) || (!session.getAttribute("tipo").equals("AdministradorSistema"))) { %>
            <h1 class="heading wow fadeInUp" data-wow-duration="500ms" data-wow-delay="300ms">Você não está autorizado a acessar essa página.</h1>
        <% } else { %>
        <section id="subscribe">
            <div class="container">
                <div class="row">
                    <div class="col-md-12">
                        <div class="block">
                            <h1 class=" heading wow fadeInUp" data-wow-duration="300ms" data-wow-delay="300ms"><span>Cadastro Administração</span></h1>
                            <p class="wow fadeInUp" data-wow-duration="300ms" data-wow-delay="400ms"></p>
                            <form action="../../acao" method="post" class="form-inline">
                                <input type="hidden" name="logica" value="CadastroAdmSys">
                                <div class="form-group">
                                    <div class="input-group">
                                        <input style="margin: 1% 1% 1% 1%;" name='nome' type="text" class="form-control" placeholder="Seu nome completo" required/>
                                        <input style="margin: 1% 1% 1% 1%;" name='usuario' type="text" class="form-control" placeholder="Seu usuário" required/>
                                        <input style="margin: 1% 1% 1% 1%;" name='senha' type="password" class="form-control" placeholder="Sua senha" required/>
                                        <input style="margin: 1% 1% 1% 1%;" name='email' type="email" class="form-control" placeholder="Seu email" required/>
                                        <div class="input-group-addon">
                                            <button style="margin: 2% 2% 2% 2%;" class="btn">Cadastrar</button>
                                        </div>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div><!-- .col-md-12 close -->
                </div><!-- .row close -->
            </div><!-- .containe close -->
        </section><!-- #subscribe close -->
        <% }%>
        <jsp:include page="../../footer.jsp"/>
</html>
