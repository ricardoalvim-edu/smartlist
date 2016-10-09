package org.smartlist.DAO.Hibernate;
    
import org.smartlist.DAO.InterfaceDAO.AdministradorEstabelecimentoDAO;
import org.smartlist.DAO.InterfaceDAO.AdministradorSistemaDAO;
import org.smartlist.DAO.InterfaceDAO.CategoriaEstabelecimentoDAO;
import org.smartlist.DAO.InterfaceDAO.CategoriaProdutoDAO;
import org.smartlist.DAO.InterfaceDAO.EstadoDAO;
import org.smartlist.DAO.InterfaceDAO.CidadeDAO;
import org.smartlist.DAO.InterfaceDAO.ConsumidorDAO;
import org.smartlist.DAO.InterfaceDAO.EstabelecimentoDAO;
import org.smartlist.DAO.InterfaceDAO.ListaConsumidorDAO;
import org.smartlist.DAO.InterfaceDAO.ProdutoDAO;

public class HibernateFactoryDAO extends DAOFactory {

    @Override
    public CidadeDAO getCidadeDAO() {
        return new HibernateCidadeDAO();
    }

    @Override
    public EstadoDAO getEstadoDAO() {
        return new HibernateEstadoDAO();
    }
    
    @Override
    public ProdutoDAO getProdutoDAO(){
        return new  HibernateProdutoDAO();
    }
    
    @Override
    public AdministradorEstabelecimentoDAO getAdministradorEstabelecimentoDAO(){
        return new HibernateAdministradorEstabelecimentoDAO();
    }

    @Override
    public AdministradorSistemaDAO getAdministradorSistemaDAO() {
        return new HibernateAdministradorSistemaDAO();
    }

    @Override
    public CategoriaEstabelecimentoDAO getCategoriaEstabelecimentoDAO() {
        return  new HibernateCategoriaEstabelecimentoDAO();
    }

    @Override
    public CategoriaProdutoDAO getCategoriaProdutoDAO() {
        return new HibernateCategoriaProdutoDAO();
    }

    @Override
    public ConsumidorDAO getConsumidorDAO() {
        return new HibernateConsumidorDAO();
    }

    @Override
    public EstabelecimentoDAO getEstabelecimentoDAO() {
        return new HibernateEstabelecimentoDAO();
    }

    @Override
    public ListaConsumidorDAO getListaConsumidorDAO() {
        return new HibernateListaConsumidorDAO();
    }
}