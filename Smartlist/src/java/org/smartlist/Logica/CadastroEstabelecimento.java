package org.smartlist.Logica;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.smartlist.DAO.Hibernate.DAOFactory;
import org.smartlist.DAO.InterfaceDAO.CategoriaEstabelecimentoDAO;
import org.smartlist.DAO.InterfaceDAO.CidadeDAO;
import org.smartlist.DAO.InterfaceDAO.EstabelecimentoDAO;
import org.smartlist.Model.CategoriaEstabelecimento;
import org.smartlist.Model.Cidade;
import org.smartlist.Model.Estabelecimento;

public class CadastroEstabelecimento implements Logica {

    @Override
    public String executa(HttpServletRequest req, HttpServletResponse res) throws Exception {
        HttpSession session = req.getSession();
        if (session.getAttribute("logado") != null && session.getAttribute("logado").equals("1") 
            && session.getAttribute("tipo") != null 
            && session.getAttribute("tipo").equals("AdministradorSistema")) {
            if (req.getParameter("nome").isEmpty()
                || req.getParameter("razaosocial").isEmpty()
                || req.getParameter("CNPJ").isEmpty()
                || req.getParameter("cidade").isEmpty() 
                || req.getParameter("categoria").isEmpty() 
                || req.getParameter("bairro").isEmpty()
                || req.getParameter("CEP").isEmpty()
                || req.getParameter("logradouro").isEmpty()
                || req.getParameter("lat").isEmpty()
                || req.getParameter("lon").isEmpty()
                || req.getParameter("numerorua").isEmpty()) {
                        req.setAttribute("sucesso", "Existem campos vazios! Preencha todos ao fazer o cadastro.");
                        req.setAttribute("titulo", "Ops!");
                        return "WEB-INF/sucesso.jsp";
            }
            DAOFactory daoFactoryState = DAOFactory.getFactory();
            EstabelecimentoDAO eDAO = daoFactoryState.getEstabelecimentoDAO();          
            daoFactoryState.getCategoriaEstabelecimentoDAO().beginTransaction();
            if (eDAO.getByNome(req.getParameter("nome"), Estabelecimento.class) != null) {
                req.setAttribute("sucesso", "Esse estabelecimento já existe.");
                req.setAttribute("titulo", "Ops!");
                eDAO.closeSession();
                return "WEB-INF/sucesso.jsp";
            }
            if (eDAO.getByCNPJ(req.getParameter("CNPJ"), Estabelecimento.class) != null) {
                req.setAttribute("sucesso", "Esse CNPJ já está sendo utilizado.");
                req.setAttribute("titulo", "Ops!");
                eDAO.closeSession();
                return "WEB-INF/sucesso.jsp";
            }
            CidadeDAO cDAO = daoFactoryState.getCidadeDAO();
            Cidade cidade = cDAO.getById(Integer.parseInt(req.getParameter("cidade")));
            CategoriaEstabelecimentoDAO ceDAO = daoFactoryState.getCategoriaEstabelecimentoDAO();
            CategoriaEstabelecimento ce = ceDAO.getById(Integer.parseInt(req.getParameter("categoria")));
            Estabelecimento est = new Estabelecimento();
            est.setCidadeIdFk(cidade);
            est.setEstBairro(req.getParameter("bairro"));
            est.setEstCep(req.getParameter("CEP"));
            est.setEstCnpj(req.getParameter("CNPJ"));
            est.setEstLogradouro(req.getParameter("logradouro"));
            est.setEstNome(req.getParameter("nome"));
            est.setEstNumeroendereco(req.getParameter("numerorua"));
            est.setEstRazaosocial(req.getParameter("razaosocial"));
            est.setEstLat(req.getParameter("lat"));
            est.setEstLong(req.getParameter("lon"));
            est.setCategestIdFk(ce);
            cidade.getEstabelecimentoList().add(est);
            ce.getEstabelecimentoList().add(est);
            ceDAO.save(ce);
            cDAO.save(cidade);
            eDAO.save(est);
            eDAO.commitTransaction();
            session.setAttribute("obj_up", est);
            session.setAttribute("obj_tipo", Estabelecimento.class.getSimpleName());
            eDAO.closeSession();
            return "Administracao/Estabelecimento/upload.jsp";
        } else {
            req.setAttribute("sucesso", "Você não tem permissão para essa página.");
            req.setAttribute("titulo", "Ops!");
            return "WEB-INF/sucesso.jsp";
        }  
    } 
}
