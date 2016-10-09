package org.smartlist.DAO.Hibernate;

import org.smartlist.DAO.InterfaceDAO.EstadoDAO;
import org.smartlist.Model.Estado;

public class HibernateEstadoDAO extends HibernateDAO<Estado, Integer> 
        implements EstadoDAO {

    public HibernateEstadoDAO() {
        super(Estado.class);
    }
        
}