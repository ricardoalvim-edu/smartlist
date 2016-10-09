/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.smartlist.DAO.Hibernate;

import java.util.List;
import org.smartlist.DAO.InterfaceDAO.EstabelecimentoDAO;
import org.smartlist.Model.Estabelecimento;
import org.smartlist.Util.HibernateUtil;

/**
 *
 * @author GILIARD
 */
public class HibernateEstabelecimentoDAO extends HibernateDAO <Estabelecimento, Integer>
        implements EstabelecimentoDAO{

    public HibernateEstabelecimentoDAO() {
        super(Estabelecimento.class);
    }
    
     public List<Estabelecimento> ListByCidade(String id) {
        Integer idEstabelecimento = Integer.parseInt(id); 
        HibernateUtil.beginTransaction();
        List<Estabelecimento> e = HibernateUtil.getSession().getNamedQuery(Estabelecimento.class.getSimpleName() + ".findByCidade").setParameter("idCidade", idEstabelecimento).list();
        HibernateUtil.commitTransaction();
        HibernateUtil.closeSession();
        return e;
    }
    
}
