package org.smartlist.DAO.Hibernate;

import org.smartlist.DAO.InterfaceDAO.AdministradorEstabelecimentoDAO;
import org.smartlist.Model.AdministradorEstabelecimento;

public class HibernateAdministradorEstabelecimentoDAO extends HibernateDAO<AdministradorEstabelecimento, Integer>
            implements AdministradorEstabelecimentoDAO{
    
    public HibernateAdministradorEstabelecimentoDAO(){
        super(AdministradorEstabelecimento.class);
    }    
}