package org.smartlist.DAO.Hibernate;

import org.smartlist.DAO.InterfaceDAO.AdministradorSistemaDAO;
import org.smartlist.Model.AdministradorSistema;

public class HibernateAdministradorSistemaDAO extends HibernateDAO<AdministradorSistema, Integer> 
    implements AdministradorSistemaDAO {

    public HibernateAdministradorSistemaDAO() {
        super(AdministradorSistema.class);
    }
}