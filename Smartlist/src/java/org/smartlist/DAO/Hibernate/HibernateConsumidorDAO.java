package org.smartlist.DAO.Hibernate;

import org.smartlist.DAO.InterfaceDAO.ConsumidorDAO;
import org.smartlist.Model.Consumidor;

public class HibernateConsumidorDAO extends HibernateDAO<Consumidor, Integer> implements ConsumidorDAO {

    public HibernateConsumidorDAO() {
       super(Consumidor.class);
    }
    
}
