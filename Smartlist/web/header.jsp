    <nav class="navbar navbar-default navbar-fixed-top">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-controls="navbar">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
            <img src="${pageContext.request.contextPath}/smart_logo.png" style="display: block; width: 20px; margin: 20% 10% -47% -41%;"/> <a class="navbar-brand" href="${pageContext.request.contextPath}/index" style="float: right;">Smartlist</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
          <ul class="nav navbar-nav">
            <li class="active"><a href="${pageContext.request.contextPath}/index">Home</a></li>
            <li class="dropdown">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true">Ações<span class="caret"></span></a>
              <ul class="dropdown-menu">
                 <% if ((session.getAttribute("tipo") == null) && (session.getAttribute("logado") == null)) { %>
                    <li class="dropdown-header">Login</li>
                    <li><a data-wow-duration="500ms" data-wow-delay="1300ms" href="${pageContext.request.contextPath}/Cadastro/AdmEst/login.jsp" role="button">Administrador do Estabelecimento</a></li>
                    <li><a data-wow-duration="500ms" data-wow-delay="1300ms" href="${pageContext.request.contextPath}/Cadastro/AdmSys/login.jsp" role="button">Administrador do Sistema</a></li>   
                 <% } %>
                <% if ((session.getAttribute("tipo") != null) && (session.getAttribute("tipo").equals("AdministradorSistema"))) { %>
                    <li class="dropdown-header">Estabelecimentos</li>
                    <li><a href="${pageContext.request.contextPath}/Administracao/Estabelecimento/cadastro.jsp">Adicionar estabelecimento</a></li>
                    <li><a href="${pageContext.request.contextPath}/Administracao/Estabelecimento/todos.jsp">Todos os estabelecimentos</a></li>
                    <li><a href="${pageContext.request.contextPath}/Administracao/catEstab/cadastro.jsp">Adicionar categoria de estabelecimento</a></li>
                    <li><a href="${pageContext.request.contextPath}/Administracao/catEstab/todos.jsp">Todas as categorias de estabelecimento</a></li>
                    <li class="dropdown-header">Cidades e Estados</li>
                    <li><a href="${pageContext.request.contextPath}/Administracao/Estado/cadastro.jsp">Adicionar estado</a></li>
                    <li><a href="${pageContext.request.contextPath}/Administracao/Estado/todos.jsp">Todos os estados</a></li>
                    <li><a href="${pageContext.request.contextPath}/Administracao/Cidade/cadastro.jsp">Adicionar cidade</a></li>
                    <li><a href="${pageContext.request.contextPath}/Administracao/Cidade/todos.jsp">Todas as cidades</a></li>
                    <li class="dropdown-header">Produtos</li>
                    <li><a href="${pageContext.request.contextPath}/Administracao/Produto/todosADM.jsp">Todos os produtos</a></li>
                    <li><a href="${pageContext.request.contextPath}/Administracao/catProd/todos.jsp">Todas as categorias de produtos</a></li>
                    <li><a href="#">Procurar produtos</a></li>
                    <li class="dropdown-header">Usuários</li>
                    <li><a href="${pageContext.request.contextPath}/Cadastro/AdmSys/cadastro.jsp">Adicionar usuário de sistema</a></li>
                    <li><a href="${pageContext.request.contextPath}/Cadastro/AdmEst/cadastro.jsp">Adicionar usuário de estabelecimento</a></li>
                <% } %>
                <% if ((session.getAttribute("tipo") != null) && (session.getAttribute("tipo").equals("AdministradorEstabelecimento"))){ %>
                    <li class="dropdown-header">Produtos</li>
                    <li><a href="${pageContext.request.contextPath}/Administracao/Produto/cadastro.jsp">Cadastrar produto</a></li>
                    <li><a href="${pageContext.request.contextPath}/Administracao/Produto/todos.jsp">Todos os produtos</a></li>
                    <li><a href="${pageContext.request.contextPath}/Administracao/catProd/todos.jsp">Todas as categorias de produtos</a></li>
                    <li><a href="#">Procurar produtos</a></li>
                    
                <% } %>
                <% if ((session.getAttribute("tipo") != null) && session.getAttribute("tipo").equals("Consumidor")) { %>
                    <li class="dropdown-header">Minhas listas</li>
                    <li><a href="${pageContext.request.contextPath}/Cadastro/Lista/cadastro.jsp">Cadastrar lista de compra</a></li>
                    <li><a href="${pageContext.request.contextPath}/Cadastro/Lista/todas.jsp">Todas as minhas listas de compra</a></li>
                    <li class="dropdown-header">Produtos</li>
                    <li><a href="${pageContext.request.contextPath}/todosProdutos.jsp">Todos os produtos disponíveis</a></li>
                <% } %>
              </ul>
            </li>
          </ul>
          <ul class="nav navbar-nav navbar-right">
            <li class="dropdown">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
                  <% if ((session.getAttribute("tipo") != null) && (session.getAttribute("logado").equals("1"))) { %>
                  Olá <%= session.getAttribute("usuario") %>.<span class="caret"></span>
                  <% } %>
                </a>
              <ul class="dropdown-menu active">
                <% if ((session.getAttribute("tipo") != null) && (session.getAttribute("logado").equals("1"))) { %>
                    <% if ((session.getAttribute("tipo") != null) && (session.getAttribute("tipo").equals("AdministradorSistema"))){%>
                        <li class="dropdown-header">Administrador do Sistema</li>
                        <hr>
                        <li class="dropdown-header">Opções</li>
                        <li><a href="${pageContext.request.contextPath}/Cadastro/AdmSys/painel.jsp">Painel de Administração</a></li>
                    <%}%>
                    
                    <% if ((session.getAttribute("tipo") != null) && (session.getAttribute("tipo").equals("AdministradorEstabelecimento"))){%>
                        <li class="dropdown-header">Administrador do <%= session.getAttribute("estabelecimento") %></li>
                        <hr>
                        <li class="dropdown-header">Opções</li>
                        <li><a href="${pageContext.request.contextPath}/Cadastro/AdmEst/painel.jsp">Painel de Administração</a></li>
                    <%}%>
                    <li><a href="${pageContext.request.contextPath}/Logout">Sair</a></li>
                <%  } %>                
              </ul>
            </li>

          </ul>
        </div><!--/.nav-collapse -->
      </div>
    </nav>
