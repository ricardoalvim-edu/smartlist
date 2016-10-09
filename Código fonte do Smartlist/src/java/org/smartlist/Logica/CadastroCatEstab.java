package org.smartlist.Logica;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.smartlist.DAO.Hibernate.DAOFactory;
import org.smartlist.DAO.InterfaceDAO.CategoriaEstabelecimentoDAO;
import org.smartlist.Model.CategoriaEstabelecimento;

public class CadastroCatEstab implements Logica {

    @Override
    public String executa(HttpServletRequest req, HttpServletResponse res) throws Exception {
        HttpSession session = req.getSession();
        if (session.getAttribute("logado") != null && session.getAttribute("logado").equals("1")
                && session.getAttribute("tipo") != null 
                && session.getAttribute("tipo").equals("AdministradorSistema")){
            if(req.getParameter("nome").isEmpty() || req.getParameter("descricao").isEmpty()){
                   req.setAttribute("sucesso", "Existem campos vazios! Preencha todos ao fazer o cadastro.");
                   req.setAttribute("titulo", "Ops!");
                   return "WEB-INF/sucesso.jsp";
            }
            DAOFactory daoFactory = DAOFactory.getFactory();
            CategoriaEstabelecimentoDAO catDAO = daoFactory.getCategoriaEstabelecimentoDAO();
            catDAO.beginTransaction();
            if (catDAO.getByNome(req.getParameter("nome"), CategoriaEstabelecimento.class) != null) {
                req.setAttribute("sucesso", "Essa categoria de estabelecimento já foi cadastrada.");
                req.setAttribute("titulo", "Ops!");
                catDAO.closeSession();
                return "WEB-INF/sucesso.jsp";
            }
            CategoriaEstabelecimento cat = new CategoriaEstabelecimento();
            cat.setCatEstNome(req.getParameter("nome"));
            cat.setCatEstDescricao(req.getParameter("descricao"));
            catDAO.save(cat);
            catDAO.commitTransaction();
            catDAO.closeSession();
            return "Administracao/catEstab/todos.jsp";
        }else{
            req.setAttribute("sucesso", "Você não tem permissão para essa página.");
            req.setAttribute("titulo", "Ops!");
            return "WEB-INF/sucesso.jsp";            
        }
    }
}