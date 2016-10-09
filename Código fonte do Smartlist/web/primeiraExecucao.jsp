<jsp:useBean id="administradorsistema" class="org.smartlist.DAO.Hibernate.HibernateAdministradorSistemaDAO"/>
<%@page language="java" session="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
        <c:if test="${not empty administradorsistema.listAll()}">
                        
        </c:if>
        <c:if test="${empty administradorsistema.listAll()}"> 

	<head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cadastro de novo administrador do sistema - Smartlist</title>
        <jsp:include page="head.jsp"/>
    </head>
	<body>
    <section id="subscribe">
        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <div class="block">
                        <h1 class=" heading wow fadeInUp" data-wow-duration="300ms" data-wow-delay="300ms"><span>Cadastro do primeiro usuario administrativo. Aproveite o Smartlist!</span></h1>
                        <p class="wow fadeInUp" data-wow-duration="300ms" data-wow-delay="400ms"></p>
                        <form action="${pageContext.request.contextPath}/acao" method="post" class="form-inline">
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
    <jsp:include page="footer.jsp"/>
    </c:if>
</html>
