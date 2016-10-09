package org.smartlist.Logica;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.mindrot.jbcrypt.BCrypt;
import org.smartlist.DAO.Hibernate.DAOFactory;
import org.smartlist.DAO.InterfaceDAO.ConsumidorDAO;
import org.smartlist.Model.Consumidor;

public class LoginConsumidor implements Logica {

    @Override
    public String executa(HttpServletRequest req, HttpServletResponse res) throws Exception {
        HttpSession session = req.getSession();
        if (session.getAttribute("logado") == null) {
            if (req.getParameter("usuario").isEmpty() || req.getParameter("senha").isEmpty()) {
                req.setAttribute("sucesso", "Campos vazios! Preencha todos os campos.");
                req.setAttribute("titulo", "Ops!");
                return "WEB-INF/sucesso.jsp";
            }
            DAOFactory daoFactory = DAOFactory.getFactory();
            ConsumidorDAO cDAO = daoFactory.getConsumidorDAO();
            cDAO.beginTransaction();
            Consumidor c = (Consumidor) cDAO.getByUsuario(req.getParameter("usuario"), Consumidor.class);
            if (c == null) {
                req.setAttribute("sucesso", "Senha ou usuario errado.");
                req.setAttribute("titulo", "Ops!");
                cDAO.closeSession();
                return "WEB-INF/sucesso.jsp";
            }
            if (BCrypt.checkpw(req.getParameter("senha"), c.getConsSenha())) {
                session.setAttribute("email", c.getConsEmail());
                session.setAttribute("nome", c.getConsNome());
                session.setAttribute("usuario", c.getConsUsuario());
                session.setAttribute("usuarioId", c.getConsId());
                session.setAttribute("logado", "1");
                session.setAttribute("id", c.getConsId().toString());
                session.setAttribute("tipo", Consumidor.class.getSimpleName());
                cDAO.closeSession();
                return "/index";
            }
            req.setAttribute("sucesso", "Senha ou usuario errado.");
            req.setAttribute("titulo", "Ops!");
            cDAO.closeSession();
            return "WEB-INF/sucesso.jsp";
        } else {
            req.setAttribute("sucesso", "Você já está logado! Saia desta conta para entrar em outra.");
            req.setAttribute("titulo", "Ops!");
            return "WEB-INF/sucesso.jsp"; 
        }
    }
}