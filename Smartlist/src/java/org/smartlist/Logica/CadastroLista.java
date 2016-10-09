package org.smartlist.Logica;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.smartlist.DAO.Hibernate.DAOFactory;
import org.smartlist.DAO.InterfaceDAO.ConsumidorDAO;
import org.smartlist.DAO.InterfaceDAO.ListaConsumidorDAO;
import org.smartlist.Model.Consumidor;
import org.smartlist.Model.ListaConsumidor;

public class CadastroLista implements Logica {

    @Override
    public String executa(HttpServletRequest req, HttpServletResponse res) throws Exception {
        HttpSession session = req.getSession();
        if (session.getAttribute("logado") != null && session.getAttribute("logado").equals("1")
                && session.getAttribute("tipo") != null
                && session.getAttribute("tipo").equals("Consumidor")){
            if (req.getParameter("nome").isEmpty()) {
                   req.setAttribute("sucesso", "Existem campos vazios! Preencha todos ao fazer o cadastro.");
                   req.setAttribute("titulo", "Ops!");
                   return "WEB-INF/sucesso.jsp";
            }
            DAOFactory daoFactoryState = DAOFactory.getFactory();
            daoFactoryState.getListaConsumidorDAO().beginTransaction();
            ListaConsumidorDAO lDAO = daoFactoryState.getListaConsumidorDAO();
            ConsumidorDAO cDAO = daoFactoryState.getConsumidorDAO();
            if (lDAO.getByNome(req.getParameter("nome"), ListaConsumidor.class) != null) {
                req.setAttribute("sucesso", "Essa lista já existe.");
                req.setAttribute("titulo", "Ops!");
                lDAO.closeSession();
                return "WEB-INF/sucesso.jsp";
            }
            ListaConsumidor l = new ListaConsumidor();
            l.setLstNome(req.getParameter("nome"));
            Consumidor c = (Consumidor) cDAO.getByUsuario(session.getAttribute("usuario").toString(), Consumidor.class);
            l.setConsIdFk(c);
            c.getListaconsumidorList().add(l);
            cDAO.save(c);
            cDAO.closeSession();
            lDAO.beginTransaction();
            lDAO.save(l);
            lDAO.commitTransaction();
            lDAO.closeSession();
            return "/index";
        }
        req.setAttribute("sucesso", "Faça login para acessar essa página.");
        req.setAttribute("titulo", "Ops!");
        return "WEB-INF/sucesso.jsp";
    } 
}