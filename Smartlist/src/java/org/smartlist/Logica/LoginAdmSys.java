package org.smartlist.Logica;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.mindrot.jbcrypt.BCrypt;
import org.smartlist.DAO.Hibernate.DAOFactory;
import org.smartlist.DAO.InterfaceDAO.AdministradorSistemaDAO;
import org.smartlist.Model.AdministradorSistema;

public class LoginAdmSys implements Logica {

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
            AdministradorSistemaDAO cDAO = daoFactory.getAdministradorSistemaDAO();
            cDAO.beginTransaction();
            AdministradorSistema c = (AdministradorSistema) cDAO.getByUsuario(req.getParameter("usuario"), AdministradorSistema.class);
            if (c == null) {
                req.setAttribute("sucesso", "Senha ou usuario errado.");
                req.setAttribute("titulo", "Ops!");
                cDAO.closeSession();
                return "WEB-INF/sucesso.jsp";
            }
            if (BCrypt.checkpw(req.getParameter("senha"), c.getAdmsisSenha())) {
                session.setAttribute("email", c.getAdmsisEmail());
                session.setAttribute("nome", c.getAdmsisNome());
                session.setAttribute("usuario", c.getAdmsisUsuario());
                session.setAttribute("logado", "1");
                session.setAttribute("tipo", AdministradorSistema.class.getSimpleName());
                cDAO.closeSession();
                return "Cadastro/AdmSys/painel.jsp";
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