package org.smartlist.Logica;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.mindrot.jbcrypt.BCrypt;
import org.smartlist.DAO.Hibernate.DAOFactory;
import org.smartlist.DAO.InterfaceDAO.AdministradorEstabelecimentoDAO;
import org.smartlist.Model.AdministradorEstabelecimento;

public class LoginAdmEst implements Logica {

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
            AdministradorEstabelecimentoDAO cDAO = daoFactory.getAdministradorEstabelecimentoDAO();
            cDAO.beginTransaction();
            AdministradorEstabelecimento c = (AdministradorEstabelecimento) cDAO.getByUsuario(req.getParameter("usuario"), AdministradorEstabelecimento.class);
            if (c == null) {
                req.setAttribute("sucesso", "Senha ou usuario errado.");
                req.setAttribute("titulo", "Ops!");
                cDAO.closeSession();
                return "WEB-INF/sucesso.jsp";
            }
            if (BCrypt.checkpw(req.getParameter("senha"), c.getAdmestSenha())) {
                session.setAttribute("email", c.getAdmestEmail());
                session.setAttribute("nome", c.getAdmestNome());
                session.setAttribute("usuario", c.getAdmestUsuario());
                session.setAttribute("estabelecimento", c.getEstIdFk().getEstNome());
                session.setAttribute("estabelecimentoId", c.getEstIdFk().getEstId());
                session.setAttribute("logado", "1");
                session.setAttribute("tipo", AdministradorEstabelecimento.class.getSimpleName());
                cDAO.closeSession();
                return "Cadastro/AdmEst/painel.jsp";
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