package org.smartlist.Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.smartlist.DAO.Hibernate.DAOFactory;
import org.smartlist.DAO.InterfaceDAO.AdministradorSistemaDAO;

public class IndexServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            DAOFactory daoFactoryState = DAOFactory.getFactory();
            AdministradorSistemaDAO asDAO = daoFactoryState.getAdministradorSistemaDAO();
            HttpSession session = request.getSession();
            if (asDAO.listAll().isEmpty()) {
                session.setAttribute("exec", "primeira");
                response.sendRedirect("primeiraExecucao.jsp");
            } else {
                if (session.getAttribute("tipo") == null 
                        || session.getAttribute("tipo").toString().equals("Consumidor")) {
                    response.sendRedirect("index.jsp");
                } else if (session.getAttribute("tipo").toString().equals("AdministradorSistema")) {
                    response.sendRedirect("Cadastro/AdmSys/painel.jsp");
                } else if (session.getAttribute("tipo").toString().equals("AdministradorEstabelecimento")) {
                    response.sendRedirect("Cadastro/AdmEst/painel.jsp");
                } else {
                    response.sendRedirect("index.jsp");
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }     

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}