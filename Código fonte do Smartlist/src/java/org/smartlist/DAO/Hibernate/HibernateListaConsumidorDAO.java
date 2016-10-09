package org.smartlist.DAO.Hibernate;

import java.util.List;
import org.smartlist.DAO.InterfaceDAO.ListaConsumidorDAO;
import org.smartlist.Model.ListaConsumidor;
import org.smartlist.Util.HibernateUtil;

public class HibernateListaConsumidorDAO extends HibernateDAO< ListaConsumidor, Integer>
        implements ListaConsumidorDAO {

    public HibernateListaConsumidorDAO() {
        super(ListaConsumidor.class);
    }
    
    public List<ListaConsumidor> ListListasByUsuario(String id) {
        HibernateUtil.beginTransaction();
        List<ListaConsumidor> l = HibernateUtil.getSession().getNamedQuery(ListaConsumidor.class.getSimpleName() + ".findByUsuario").setParameter("id", Integer.parseInt(id)).list();
        HibernateUtil.closeSession();
        return l;
    }
}