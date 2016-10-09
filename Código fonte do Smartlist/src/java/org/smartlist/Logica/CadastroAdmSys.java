package org.smartlist.Logica;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.mindrot.jbcrypt.BCrypt;
import org.smartlist.DAO.Hibernate.DAOFactory;
import org.smartlist.DAO.InterfaceDAO.AdministradorSistemaDAO;
import org.smartlist.Model.AdministradorSistema;

public class CadastroAdmSys implements Logica {

    @Override
    public String executa(HttpServletRequest req, HttpServletResponse res) throws Exception {
        HttpSession session = req.getSession();
        if ((session.getAttribute("logado") != null && session.getAttribute("logado").equals("1") 
            && session.getAttribute("tipo") != null && session.getAttribute("tipo").equals("AdministradorSistema")) 
            || (session.getAttribute("exec") != null && session.getAttribute("exec").equals("primeira"))) {
            if (req.getParameter("usuario").isEmpty() || req.getParameter("senha").isEmpty() 
                || req.getParameter("email").isEmpty() 
                || req.getParameter("nome").isEmpty()) {
                   req.setAttribute("sucesso", "Existem campos vazios! Preencha todos ao fazer o cadastro.");
                   req.setAttribute("titulo", "Ops!");
                   return "WEB-INF/sucesso.jsp";
            }
            DAOFactory daoFactoryState = DAOFactory.getFactory();
            daoFactoryState.getAdministradorSistemaDAO().beginTransaction();
            AdministradorSistemaDAO asDAO = daoFactoryState.getAdministradorSistemaDAO();
            if (asDAO.getByUsuario(req.getParameter("usuario"), AdministradorSistema.class) != null) {
                req.setAttribute("sucesso", "Esse usuario já existe.");
                req.setAttribute("titulo", "Ops!");
                asDAO.closeSession();
                return "WEB-INF/sucesso.jsp";
            }
            if (asDAO.getByEmail(req.getParameter("email"), AdministradorSistema.class) != null) {
                req.setAttribute("sucesso", "Esse email já foi utilizado.");
                req.setAttribute("titulo", "Ops!");
                asDAO.closeSession();
                return "WEB-INF/sucesso.jsp";
            }
            AdministradorSistema as = new AdministradorSistema();
            as.setAdmsisEmail(req.getParameter("email"));
            as.setAdmsisNome(req.getParameter("nome"));
            as.setAdmsisSenha(BCrypt.hashpw(req.getParameter("senha"), BCrypt.gensalt()));
            as.setAdmsisUsuario(req.getParameter("usuario"));
            asDAO.save(as);
            asDAO.commitTransaction();
            asDAO.closeSession();
            if (session.getAttribute("exec").equals("primeira")) {
                session.setAttribute("email", as.getAdmsisEmail());
                session.setAttribute("nome", as.getAdmsisNome());
                session.setAttribute("usuario", as.getAdmsisUsuario());
                session.setAttribute("logado", "1");
                session.setAttribute("tipo", AdministradorSistema.class.getSimpleName());
            }
            return "Cadastro/AdmSys/painel.jsp"; 
        } else {
            req.setAttribute("sucesso", "Você não tem permissão para essa página.");
            req.setAttribute("titulo", "Ops!");
            return "WEB-INF/sucesso.jsp";
        }
    }
}