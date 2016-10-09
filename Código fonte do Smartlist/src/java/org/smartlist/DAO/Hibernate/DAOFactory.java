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

public abstract class DAOFactory {
    private static final Class FACTORY_CLASS = HibernateFactoryDAO.class;

    public static DAOFactory getFactory(){
        try {
            return (DAOFactory) FACTORY_CLASS.newInstance();
        } catch (InstantiationException e) {
            throw new RuntimeException();
        } catch (IllegalAccessException e) {
            throw new RuntimeException();
        }
    }

    public abstract CidadeDAO getCidadeDAO();
    
    public abstract EstadoDAO getEstadoDAO();
    
    public abstract ProdutoDAO getProdutoDAO();
    
    public abstract AdministradorEstabelecimentoDAO getAdministradorEstabelecimentoDAO();
    
    public abstract AdministradorSistemaDAO getAdministradorSistemaDAO();
    
    public abstract CategoriaEstabelecimentoDAO getCategoriaEstabelecimentoDAO();
    
    public abstract CategoriaProdutoDAO getCategoriaProdutoDAO();
    
    public abstract ConsumidorDAO getConsumidorDAO();
    
    public abstract EstabelecimentoDAO getEstabelecimentoDAO();
    
    public abstract ListaConsumidorDAO getListaConsumidorDAO();
}