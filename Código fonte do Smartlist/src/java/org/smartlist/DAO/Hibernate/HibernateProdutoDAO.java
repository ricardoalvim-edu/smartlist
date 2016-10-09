package org.smartlist.DAO.Hibernate;

import java.util.List;
import org.smartlist.DAO.InterfaceDAO.ProdutoDAO;
import org.smartlist.Model.Produto;
import org.smartlist.Util.HibernateUtil;

public class HibernateProdutoDAO extends HibernateDAO<Produto, Integer> implements ProdutoDAO {

    public HibernateProdutoDAO() {
        super(Produto.class);
    }    
    
    public List<Produto> ListByEstabelecimento(String id) {
        Integer idCidade = Integer.parseInt(id); 
        HibernateUtil.beginTransaction();
        List<Produto> p = HibernateUtil.getSession().getNamedQuery(Produto.class.getSimpleName() + ".findByEstabelecimento").setParameter("estId", idCidade).list();
        HibernateUtil.commitTransaction();
        HibernateUtil.getSession().close();
        return p;
    }
        
}