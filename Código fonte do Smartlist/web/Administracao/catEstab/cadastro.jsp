<!DOCTYPE html>
<html class="no-js">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cadastro do Estabelecimento - Smartlist</title>
        <jsp:include page="../../head.jsp"/>
    </head>
    <header>
        <jsp:include page="../../header.jsp"/>
    </header>
    <body style="max-width: 80%; margin: 0px auto; margin-top: 2%;">
        <% if ((session.getAttribute("tipo") == null) || (!session.getAttribute("tipo").equals("AdministradorSistema"))) { %>
        <h1 class="heading wow fadeInUp" data-wow-duration="500ms" data-wow-delay="300ms">Você não está autorizado a acessar essa página.</h1>
        <% } else { %>   
        <section id="contact-us">
            <div class="container">
                <div class="row">
                    <div class="col-md-12">
                        <div class="block">
                            <h1 class="heading wow fadeInUp" data-wow-duration="500ms" data-wow-delay="300ms">Adicionar categoria de <span>ESTABELECIMENTO</span></h1>
                            <form action="../../acao" method="post">
                                <input type="hidden" name="logica" value="CadastroCatEstab">
                                <div class="form-group wow fadeInDown" data-wow-duration="500ms" data-wow-delay="600ms">
                                    <input type="text" name='nome' class="form-control" placeholder="Categoria">
                                </div>
                                <div class="form-group wow fadeInDown" data-wow-duration="500ms" data-wow-delay="600ms">
                                    <input type="text" name='descricao' class="form-control" placeholder="Descrição">
                                </div>							
                                <button type="submit" class="btn btn-default wow bounceIn" data-wow-duration="500ms" data-wow-delay="1300ms">Cadastrar</button>
                            </form>
                        </div>
                    </div><!-- .col-md-12 close -->
                </div><!-- .row close -->
            </div><!-- .container close -->
        </section><!-- #contact-us close -->
        <% }%>
        <jsp:include page="../../footer.jsp"/>
    </body>   
</html>

