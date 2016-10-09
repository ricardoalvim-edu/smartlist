package org.smartlist.Logica;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.smartlist.DAO.Hibernate.DAOFactory;
import org.smartlist.DAO.InterfaceDAO.AdministradorEstabelecimentoDAO;
import org.smartlist.DAO.InterfaceDAO.CategoriaProdutoDAO;
import org.smartlist.DAO.InterfaceDAO.EstabelecimentoDAO;
import org.smartlist.DAO.InterfaceDAO.ProdutoDAO;
import org.smartlist.Model.AdministradorEstabelecimento;
import org.smartlist.Model.CategoriaProduto;
import org.smartlist.Model.Estabelecimento;
import org.smartlist.Model.Produto;

public class CadastroProduto implements Logica {

    @Override
    public String executa(HttpServletRequest req, HttpServletResponse res) throws Exception {
        HttpSession session = req.getSession();
        if (session.getAttribute("logado") != null && session.getAttribute("logado").equals("1")
                && session.getAttribute("tipo") != null
                && session.getAttribute("tipo").equals("AdministradorEstabelecimento")){
            if (req.getParameter("nome").isEmpty()) {
                   req.setAttribute("sucesso", "Existem campos vazios! Preencha todos ao fazer o cadastro.");
                   req.setAttribute("titulo", "Ops!");
                   return "WEB-INF/sucesso.jsp";
            }
            DAOFactory daoFactoryState = DAOFactory.getFactory();
            daoFactoryState.getProdutoDAO().beginTransaction();
            ProdutoDAO pDAO = daoFactoryState.getProdutoDAO();
            if (pDAO.getByNome(req.getParameter("nome"), Produto.class) != null) {
                req.setAttribute("sucesso", "Esse produto já existe.");
                req.setAttribute("titulo", "Ops!");
                pDAO.closeSession();
                return "WEB-INF/sucesso.jsp";
            }
            CategoriaProdutoDAO cpDAO = daoFactoryState.getCategoriaProdutoDAO();
            EstabelecimentoDAO eDAO = daoFactoryState.getEstabelecimentoDAO();
            AdministradorEstabelecimentoDAO aeDAO = daoFactoryState.getAdministradorEstabelecimentoDAO();
            AdministradorEstabelecimento ae = (AdministradorEstabelecimento) aeDAO.getByUsuario(session.getAttribute("usuario").toString(), AdministradorEstabelecimento.class);
            Estabelecimento e = ae.getEstIdFk();
            Produto p = new Produto();
            CategoriaProduto cp = cpDAO.getById(Integer.parseInt(req.getParameter("categoria")));
            p.setProNome(req.getParameter("nome"));
            p.setProPreco(Float.parseFloat(req.getParameter("preco")));
            p.setProQuantidade(Integer.parseInt(req.getParameter("quantidade")));
            p.setProPeso(Float.parseFloat(req.getParameter("peso")));
            p.setProUnidade(req.getParameter("unidade"));
            p.setCategprodIdFk(cp);
            p.setEstIdFk(e);
            e.getProdutoList().add(p);
            cp.getProdutoList().add(p);
            cpDAO.save(cp);
            eDAO.save(e);
            pDAO.save(p);
            pDAO.commitTransaction();
            pDAO.closeSession();
            session.setAttribute("obj_up", p);
            session.setAttribute("obj_tipo", Estabelecimento.class.getSimpleName());
            return "Administracao/Produto/todos.jsp";
        } else {
            req.setAttribute("sucesso", "Você não tem permissão para essa página.");
            req.setAttribute("titulo", "Ops!");
            return "WEB-INF/sucesso.jsp";
        }
    }
}