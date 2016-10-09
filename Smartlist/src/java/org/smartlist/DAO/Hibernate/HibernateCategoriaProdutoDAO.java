/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.smartlist.DAO.Hibernate;

import org.smartlist.DAO.InterfaceDAO.CategoriaProdutoDAO;
import org.smartlist.Model.CategoriaProduto;

/**
 *
 * @author GILIARD
 */
public class HibernateCategoriaProdutoDAO extends HibernateDAO< CategoriaProduto, Integer> 
    implements CategoriaProdutoDAO{

    public HibernateCategoriaProdutoDAO() {
        super(CategoriaProduto.class);
    }
}
