package org.smartlist.Logica;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.smartlist.DAO.Hibernate.DAOFactory;
import org.smartlist.DAO.InterfaceDAO.EstadoDAO;
import org.smartlist.Model.Estado;

public class CadastroEstado implements Logica {
    @Override
    public String executa(HttpServletRequest req, HttpServletResponse res) throws Exception {
        HttpSession session = req.getSession();
        if (session.getAttribute("logado") != null && session.getAttribute("logado").equals("1") 
            && session.getAttribute("tipo") != null 
            && session.getAttribute("tipo").equals("AdministradorSistema")) {
            if (req.getParameter("estado").isEmpty() || req.getParameter("sigla").isEmpty()) {
                   req.setAttribute("sucesso", "Existem campos vazios! Preencha todos ao fazer o cadastro.");
                   req.setAttribute("titulo", "Ops!");
                   return "WEB-INF/sucesso.jsp";
            }
            DAOFactory daoFactory = DAOFactory.getFactory();
            daoFactory.getEstadoDAO().beginTransaction();
            EstadoDAO estadoDAO = daoFactory.getEstadoDAO();
            if (estadoDAO.getByNome(req.getParameter("estado"), Estado.class)!= null){
                req.setAttribute("sucesso", "Esse estado já existe.");
                req.setAttribute("titulo", "Ops!");
                estadoDAO.closeSession();
                return "WEB-INF/sucesso.jsp";
            } 
            Estado estado = new Estado();
            estado.setEstadoNome(req.getParameter("estado"));
            estado.setEstadoSigla(req.getParameter("sigla"));
            estadoDAO.save(estado);
            estadoDAO.commitTransaction();
            estadoDAO.closeSession();
            return "Administracao/Estado/todos.jsp";
        } else {
            req.setAttribute("sucesso", "Você não tem permissão para essa página.");
            req.setAttribute("titulo", "Ops!");
            return "WEB-INF/sucesso.jsp";    
        }        
    }
}