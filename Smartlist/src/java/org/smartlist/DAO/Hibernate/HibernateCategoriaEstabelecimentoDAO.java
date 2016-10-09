package org.smartlist.DAO.Hibernate;

import org.smartlist.DAO.InterfaceDAO.CategoriaEstabelecimentoDAO;
import org.smartlist.Model.CategoriaEstabelecimento;

public class HibernateCategoriaEstabelecimentoDAO extends HibernateDAO< CategoriaEstabelecimento, Integer> 
    implements CategoriaEstabelecimentoDAO{

    public HibernateCategoriaEstabelecimentoDAO() {
        super(CategoriaEstabelecimento.class);
    }   
}