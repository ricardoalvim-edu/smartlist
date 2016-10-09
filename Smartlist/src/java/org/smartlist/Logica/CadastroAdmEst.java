package org.smartlist.Logica;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.mindrot.jbcrypt.BCrypt;
import org.smartlist.DAO.Hibernate.DAOFactory;
import org.smartlist.DAO.InterfaceDAO.AdministradorEstabelecimentoDAO;
import org.smartlist.DAO.InterfaceDAO.EstabelecimentoDAO;
import org.smartlist.Model.AdministradorEstabelecimento;
import org.smartlist.Model.Estabelecimento;

public class CadastroAdmEst implements Logica {

    @Override
    public String executa(HttpServletRequest req, HttpServletResponse res) throws Exception {
        HttpSession session = req.getSession();
        if (session.getAttribute("logado") != null && session.getAttribute("logado").equals("1") 
            && session.getAttribute("tipo") != null 
            && session.getAttribute("tipo").equals("AdministradorSistema")) {
            if (req.getParameter("usuario").isEmpty() || req.getParameter("senha").isEmpty() 
                || req.getParameter("email").isEmpty() 
                || req.getParameter("nome").isEmpty()) {
                   req.setAttribute("sucesso", "Existem campos vazios! Preencha todos ao fazer o cadastro.");
                   req.setAttribute("titulo", "Ops!");
                   return "WEB-INF/sucesso.jsp";
            }
            DAOFactory daoFactoryState = DAOFactory.getFactory();
            daoFactoryState.getAdministradorEstabelecimentoDAO().beginTransaction();
            AdministradorEstabelecimentoDAO asDAO = daoFactoryState.getAdministradorEstabelecimentoDAO();
            if (asDAO.getByUsuario(req.getParameter("usuario"), AdministradorEstabelecimento.class) != null) {
                req.setAttribute("sucesso", "Esse usuario já existe.");
                req.setAttribute("titulo", "Ops!");
                asDAO.closeSession();
                return "WEB-INF/sucesso.jsp";
            }
            if (asDAO.getByEmail(req.getParameter("email"), AdministradorEstabelecimento.class) != null) {
                req.setAttribute("sucesso", "Esse usuario já existe.");
                req.setAttribute("titulo", "Ops!");
                asDAO.closeSession();
                return "WEB-INF/sucesso.jsp";
            }
            AdministradorEstabelecimento as = new AdministradorEstabelecimento();
            as.setAdmestEmail(req.getParameter("email"));
            as.setAdmestNome(req.getParameter("nome"));
            as.setAdmestSenha(BCrypt.hashpw(req.getParameter("senha"), BCrypt.gensalt()));
            as.setAdmestUsuario(req.getParameter("usuario"));
            EstabelecimentoDAO eDAO = daoFactoryState.getEstabelecimentoDAO();
            Estabelecimento e = eDAO.getById(Integer.parseInt(req.getParameter("estabelecimento")));
            as.setEstIdFk(e);
            e.getAdministradorestabelecimentoList().add(as);
            eDAO.save(e);
            asDAO.save(as);
            asDAO.commitTransaction();
            req.setAttribute("sucesso", as.getAdmestUsuario() + " cadastrado com sucesso!");
            req.setAttribute("titulo", "Cadastro feito - SmartList");
            asDAO.closeSession();
            return "WEB-INF/sucesso.jsp";
        } else {
            req.setAttribute("sucesso", "Você não tem permissão para essa página.");
            req.setAttribute("titulo", "Ops!");
            return "WEB-INF/sucesso.jsp";
        }
    }   
}