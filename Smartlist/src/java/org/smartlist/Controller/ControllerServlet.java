package org.smartlist.Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.smartlist.Logica.Logica;

@WebServlet(name = "acao", urlPatterns = {"/acao"})
public class ControllerServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String parametro = request.getParameter("logica");
        String nomeDaClasse = "org.smartlist.Logica." + parametro;
        try {
            Class<?> classe = Class.forName(nomeDaClasse);
            Logica logica = (Logica) classe.newInstance();
            // Recebe o String após a execução da lógica
            String pagina = logica.executa(request, response);
            request.getRequestDispatcher(pagina).forward(request, response);
        } catch (Exception e) {
            throw new ServletException(
            "A lógica de negócios causou uma exceção", e);
        }
    }
}