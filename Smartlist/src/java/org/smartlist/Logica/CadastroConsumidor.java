package org.smartlist.Logica;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.mindrot.jbcrypt.BCrypt;
import org.smartlist.DAO.Hibernate.DAOFactory;
import org.smartlist.DAO.InterfaceDAO.ConsumidorDAO;
import org.smartlist.Model.Consumidor;

public class CadastroConsumidor implements Logica {

    @Override
    public String executa(HttpServletRequest req, HttpServletResponse res) throws Exception {
        HttpSession session = req.getSession();
        if (session.getAttribute("logado") == null || (session.getAttribute("logado").equals("1"))) {
            if (req.getParameter("usuario").isEmpty() || req.getParameter("senha").isEmpty() 
                    || req.getParameter("email").isEmpty() 
                    || req.getParameter("nome").isEmpty()) {
                req.setAttribute("sucesso", "Existem campos vazios! Preencha todos ao fazer o cadastro.");
                req.setAttribute("titulo", "Ops!");
                return "WEB-INF/sucesso.jsp";
            }
            DAOFactory daoFactory = DAOFactory.getFactory();
            ConsumidorDAO cDAO = daoFactory.getConsumidorDAO();
            cDAO.beginTransaction();
            if (cDAO.getByUsuario(req.getParameter("usuario"), Consumidor.class) != null) {
                req.setAttribute("sucesso", "Esse usuario já existe.");
                req.setAttribute("titulo", "Ops!");
                cDAO.closeSession();
                return "WEB-INF/sucesso.jsp";
            }
            if (cDAO.getByEmail(req.getParameter("email"), Consumidor.class) != null) {
                req.setAttribute("sucesso", "Esse email já foi utilizado.");
                req.setAttribute("titulo", "Ops!");
                cDAO.closeSession();
                return "WEB-INF/sucesso.jsp";
            }
            Consumidor c = new Consumidor();
            c.setConsNome(req.getParameter("nome"));
            c.setConsUsuario(req.getParameter("usuario"));
            c.setConsSenha(BCrypt.hashpw(req.getParameter("senha"), BCrypt.gensalt()));
            c.setConsEmail(req.getParameter("email"));
            cDAO.save(c);
            cDAO.commitTransaction();
            cDAO.closeSession();
            return "Cadastro/Usuario/login.jsp"; 
        } else {
            req.setAttribute("sucesso", "Você já está logado! Para criar outra conta, saia desta.");
            req.setAttribute("titulo", "Ops!");
            return "WEB-INF/sucesso.jsp";
        }
    }
}