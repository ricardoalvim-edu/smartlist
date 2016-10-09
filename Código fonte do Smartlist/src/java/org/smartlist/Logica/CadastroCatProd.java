package org.smartlist.Logica;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.smartlist.DAO.Hibernate.DAOFactory;
import org.smartlist.DAO.InterfaceDAO.CategoriaProdutoDAO;
import org.smartlist.Model.CategoriaProduto;

public class CadastroCatProd implements Logica {

    @Override
    public String executa(HttpServletRequest req, HttpServletResponse res) throws Exception {
        HttpSession session = req.getSession();
        if(session.getAttribute("logado") != null && session.getAttribute("logado").equals("1")
                && session.getAttribute("tipo") != null
                && session.getAttribute("tipo").equals("AdministradorSistema")){
            if (req.getParameter("nome").isEmpty() || req.getParameter("descricao").isEmpty()){
                   req.setAttribute("sucesso", "Existem campos vazios! Preencha todos ao fazer o cadastro.");
                   req.setAttribute("titulo", "Ops!");
                   return "WEB-INF/sucesso.jsp";
            }
            DAOFactory daoFactoryState = DAOFactory.getFactory();
            daoFactoryState.getCategoriaProdutoDAO().beginTransaction();
            CategoriaProdutoDAO cpDAO = daoFactoryState.getCategoriaProdutoDAO();
            if (cpDAO.getByNome(req.getParameter("nome"), CategoriaProduto.class) != null) {
                req.setAttribute("sucesso", "Essa categoria de produto já existe.");
                req.setAttribute("titulo", "Ops!");
                cpDAO.closeSession();
                return "WEB-INF/sucesso.jsp";
            }
            CategoriaProduto cp = new CategoriaProduto();
            cp.setCategprodDescricao(req.getParameter("descricao"));
            cp.setCategprodNome(req.getParameter("nome"));
            cpDAO.save(cp);
            cpDAO.commitTransaction();
            cpDAO.closeSession();
            return "Administracao/catProd/todos.jsp";
        }else{
            req.setAttribute("sucesso", "Você não tem permissão para essa página.");
            req.setAttribute("titulo", "Ops!");
            return "WEB-INF/sucesso.jsp";            
        }   
    }
}