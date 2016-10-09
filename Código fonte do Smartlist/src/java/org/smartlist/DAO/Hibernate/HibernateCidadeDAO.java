package org.smartlist.DAO.Hibernate;

import org.smartlist.DAO.InterfaceDAO.CidadeDAO;
import org.smartlist.Model.Cidade;

public class HibernateCidadeDAO extends HibernateDAO<Cidade, Integer> implements CidadeDAO {

    public HibernateCidadeDAO(){
        super(Cidade.class);
    }
}