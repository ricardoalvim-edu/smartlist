package org.smartlist.Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.smartlist.DAO.Hibernate.DAOFactory;
import org.smartlist.DAO.InterfaceDAO.ListaConsumidorDAO;
import org.smartlist.DAO.InterfaceDAO.ProdutoDAO;
import org.smartlist.Model.ListaConsumidor;
import org.smartlist.Model.Produto;

public class AddToList extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getParameter("produtoId") != null || request.getParameter("listaId") != null) {
            String produto = request.getParameter("produtoId"); //pega parametro
            String lista = request.getParameter("listaId"); //pega parametro
            try { 
                DAOFactory daoFactoryState = DAOFactory.getFactory();
                ProdutoDAO pDAO = daoFactoryState.getProdutoDAO();
                pDAO.beginTransaction();
                Produto p = pDAO.getById(Integer.parseInt(produto));    
                
                ListaConsumidorDAO lcDAO = daoFactoryState.getListaConsumidorDAO();
                ListaConsumidor lc = lcDAO.getById(Integer.parseInt(lista));
                p.getListaconsumidorList().add(lc);
                lcDAO.save(lc);
                lcDAO.commitTransaction();
                lcDAO.closeSession();
                pDAO.closeSession();
                
                response.sendRedirect("Administracao/Produto/lista.jsp?listaId=" + lista);
            } catch (Exception ex) {
                System.out.println("exception: "+ ex.getMessage());
            }
        } else {
            response.sendError(404);
        }
    }
}
