package org.smartlist.Logica;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.smartlist.DAO.Hibernate.DAOFactory;
import org.smartlist.DAO.InterfaceDAO.CidadeDAO;
import org.smartlist.DAO.InterfaceDAO.EstadoDAO;
import org.smartlist.Model.Cidade;
import org.smartlist.Model.Estado;

public class CadastroCidade implements Logica{

    @Override
    public String executa(HttpServletRequest req, HttpServletResponse res) throws Exception {
        HttpSession session = req.getSession();
        if (session.getAttribute("logado") != null && session.getAttribute("logado").equals("1") 
            && session.getAttribute("tipo") != null 
            && session.getAttribute("tipo").equals("AdministradorSistema")) {
            if (req.getParameter("estado").isEmpty() || req.getParameter("cidade").isEmpty()) {
                   req.setAttribute("sucesso", "Existem campos vazios! Preencha todos ao fazer o cadastro.");
                   req.setAttribute("titulo", "Ops!");
                   return "WEB-INF/sucesso.jsp";
            }
            DAOFactory daoFactoryCity = DAOFactory.getFactory();
            CidadeDAO cidadeDAO = daoFactoryCity.getCidadeDAO();
            daoFactoryCity.getCidadeDAO().beginTransaction();
            if (cidadeDAO.getByNome(req.getParameter("cidade"), Cidade.class) != null){
                req.setAttribute("sucesso", "Essa cidade já foi cadastrada.");
                req.setAttribute("titulo", "Ops!");
                cidadeDAO.closeSession();
                return "WEB-INF/sucesso.jsp";
            }
            DAOFactory daoFactoryState = DAOFactory.getFactory();
            EstadoDAO estadoDAO = daoFactoryState.getEstadoDAO();
            Estado estado = estadoDAO.getById(Integer.parseInt(req.getParameter("estado")));
            Cidade cidade = new Cidade();
            cidade.setCidadeNome(req.getParameter("cidade"));
            cidade.setEstadoIdFk(estado);
            cidadeDAO.save(cidade);
            cidadeDAO.commitTransaction();
            cidadeDAO.closeSession();
            return "Administracao/Cidade/todos.jsp";
        } else {
            req.setAttribute("sucesso", "Você não tem permissão para essa página.");
            req.setAttribute("titulo", "Ops!");
            return "WEB-INF/sucesso.jsp";
        }
    }
}