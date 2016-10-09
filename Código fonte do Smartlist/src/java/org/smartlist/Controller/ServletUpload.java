package org.smartlist.Controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import org.smartlist.DAO.Hibernate.DAOFactory;
import org.smartlist.DAO.InterfaceDAO.EstabelecimentoDAO;
import java.util.concurrent.ThreadLocalRandom;
import org.smartlist.DAO.InterfaceDAO.CategoriaProdutoDAO;
import org.smartlist.DAO.InterfaceDAO.CidadeDAO;
import org.smartlist.DAO.InterfaceDAO.ProdutoDAO;
import org.smartlist.Model.CategoriaProduto;
import org.smartlist.Model.Cidade;
import org.smartlist.Model.Estabelecimento;
import org.smartlist.Model.Produto;

@WebServlet(name = "ServletUpload", urlPatterns = {"/ServletUpload"})
@MultipartConfig
public class ServletUpload extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Part filePart = request.getPart("file");
        String fileName = filePart.getSubmittedFileName();
        String contentType = filePart.getContentType();
        DAOFactory daoFactoryState = DAOFactory.getFactory();
        if (session.getAttribute("obj_up") != null) {
            Object obj = session.getAttribute("obj_up");
            if (contentType.equals("image/png") || contentType.equals("image/jpeg")) {
                File uploads = new File("C:\\Users\\ricar\\Documents\\Git\\smartlist\\smartlist-utfpr-code\\Smartlist\\web\\imagens");
                String arquivo = Integer.toString(ThreadLocalRandom.current().nextInt(1, 100000 + 1)) + "-" + fileName;
                File file = new File(uploads, arquivo);
                
                try (InputStream input = filePart.getInputStream()) {
                    Files.copy(input, file.toPath());
                } catch (Exception e) {
                    request.setAttribute("titulo", "Ops!");
                    request.setAttribute("sucesso", "Algo de errado aconteceu! Mensagem pro administrador: \n" + e);
                    System.out.println(e);
                    request.getRequestDispatcher("WEB-INF/sucesso.jsp").forward(request, response);
                }
                String tipo = session.getAttribute("obj_tipo").toString();
                System.out.println(tipo);
                switch (tipo) {
                    case "Estabelecimento":
                        Estabelecimento est = (Estabelecimento) obj;
                        est.setUrl_imagem(arquivo);
                        EstabelecimentoDAO eDAO = daoFactoryState.getEstabelecimentoDAO();          
                        eDAO.beginTransaction();
                        eDAO.save(est);
                        eDAO.commitTransaction();
                        eDAO.closeSession();
                        response.sendRedirect("Administracao/Estabelecimento/todos.jsp");
                        break;
                    case "Produto":
                        Produto prod = (Produto) obj;
                        prod.setProImgurl(arquivo);
                        ProdutoDAO pDAO = daoFactoryState.getProdutoDAO();
                        pDAO.beginTransaction();
                        pDAO.save(prod);
                        pDAO.commitTransaction();
                        pDAO.closeSession();
                        response.sendRedirect("Administracao/Produto/todos.jsp");
                        break;
                    case "Cidade":
                        Cidade cid = (Cidade) obj;
                        cid.setCidadeImgurl(arquivo);
                        CidadeDAO cDAO = daoFactoryState.getCidadeDAO();
                        cDAO.beginTransaction();
                        cDAO.save(cid);
                        cDAO.commitTransaction();
                        cDAO.closeSession();
                        response.sendRedirect("Administracao/Cidade/todos.jsp");
                        break;
                    case "CategoriaProduto":
                        CategoriaProduto cp = (CategoriaProduto) obj;
                        cp.setCategprodurlImg(arquivo);
                        CategoriaProdutoDAO cpDAO = daoFactoryState.getCategoriaProdutoDAO();
                        cpDAO.beginTransaction();
                        cpDAO.save(cp);
                        cpDAO.commitTransaction();
                        cpDAO.closeSession();
                        response.sendRedirect("Administracao/catProd/todos.jsp");
                        break;
                }
            } else {
                request.setAttribute("titulo", "Ops!");
                request.setAttribute("sucesso", "Apenas PNG e JPG são suportados!");
                request.getRequestDispatcher("WEB-INF/sucesso.jsp").forward(request, response);
            }
        } else {
            request.setAttribute("titulo", "Ops!");
            request.setAttribute("sucesso", "Algo de errado aconteceu!");
            request.getRequestDispatcher("WEB-INF/sucesso.jsp").forward(request, response);
        }
    }
}