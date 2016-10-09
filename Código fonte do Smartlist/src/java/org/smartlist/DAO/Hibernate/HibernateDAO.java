package org.smartlist.DAO.Hibernate;

import org.smartlist.DAO.InterfaceDAO.GenericDAO;
import java.io.Serializable;
import java.util.List;
import org.hibernate.Criteria;
import org.smartlist.Util.HibernateUtil;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.smartlist.Model.ListaConsumidor;
import org.smartlist.Model.Produto;

public abstract class HibernateDAO<T, Type extends Serializable> implements GenericDAO<T, Type>{

    private final Class<T> persistentClass;
    
    public HibernateDAO(Class persistentClass) {
        super();
        this.persistentClass = persistentClass;
    }

    @Override
    public void beginTransaction() {
        HibernateUtil.beginTransaction();
    }
    
    @Override
    public void closeSession() {
        HibernateUtil.closeSession();
    }
    
    @Override
    public T getById(Integer id) {
        T result = (T) HibernateUtil.getSession().load(persistentClass, id);
        if(result != null){
            Hibernate.initialize(result);
            return result;
        }else{ 
            return null;
        }
    }
    
    @Override
    public Object getByUsuario(String usuario, Class classe) {
        return HibernateUtil.getSession().getNamedQuery(classe.getSimpleName() + ".findByUsuario").setParameter("usuario", usuario).uniqueResult();
    }
    
    @Override
    public Object getByNome(String nome, Class classe) {
        return HibernateUtil.getSession().getNamedQuery(classe.getSimpleName() + ".findByNome").setParameter("nome", nome).uniqueResult();    
    }
    
    @Override
    public Object getByEmail(String nome, Class classe) {
        return HibernateUtil.getSession().getNamedQuery(classe.getSimpleName() + ".findByEmail").setParameter("email", nome).uniqueResult();    
    }
    
    @Override
    public Object getByCNPJ(String nome, Class classe) {
        return HibernateUtil.getSession().getNamedQuery(classe.getSimpleName() + ".findByCNPJ").setParameter("CNPJ", nome).uniqueResult();    
    }
    
    @Override
    public Object getByRazaoSocial(String nome, Class classe) {
        return HibernateUtil.getSession().getNamedQuery(classe.getSimpleName() + ".findByRazaoSocial").setParameter("razaosocial", nome).uniqueResult();    
    }
    
    @Override
    public List<Produto> getByEstabelecimento(int est, Class classe) {
        try {
            HibernateUtil.beginTransaction();
            Query query = HibernateUtil.getSession().getNamedQuery(classe.getSimpleName() + ".findByEstabelecimento").setParameter("estId", est);    
            HibernateUtil.closeSession();
            return query.list();
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
    
    @Override
    public List<ListaConsumidor> getByUsuario(String usr) {
        HibernateUtil.beginTransaction();
        System.out.println(usr);
        try {
            Query query = HibernateUtil.getSession().getNamedQuery(ListaConsumidor.class.getSimpleName() + ".findByUsuario").setParameter("id", Integer.parseInt(usr));    
            return query.list();
        } catch (Exception e) {
            System.out.println(e);
        }
        HibernateUtil.closeSession();
        return null;
    }
    
    @Override
    public void commitTransaction() {
        HibernateUtil.commitTransaction();
    }

    @Override
    public void save(T entity) {
        HibernateUtil.getSession().saveOrUpdate(entity);
        //HibernateUtil.getSession().close();
    }

    @Override
    public void delete(T entity) {
        HibernateUtil.getSession().delete(entity);
    }

    @Override
    public List<T> listAll() {
        HibernateUtil.beginTransaction();
        Criteria criteria = HibernateUtil.getSession().createCriteria(persistentClass);
        List<T> lista = criteria.list();
        HibernateUtil.closeSession();
        return lista;
    }
}