package org.smartlist.DAO.InterfaceDAO;

import java.io.Serializable;
import java.util.List;
import org.smartlist.Model.ListaConsumidor;
import org.smartlist.Model.Produto;

public interface GenericDAO<T,Type extends Serializable> {

    void beginTransaction();

    void commitTransaction();
    
    void closeSession();

    void save(T entity);

    void delete (T entity);

    public List<T> listAll();

    public T getById(Integer id);
    
    public Object getByUsuario(String usuario, Class classe);
    
    public Object getByNome(String nome, Class classe);
    
    public Object getByEmail(String nome, Class classe);
    
    public Object getByRazaoSocial(String nome, Class classe);
    
    public Object getByCNPJ(String nome, Class classe);
    
    public List<Produto> getByEstabelecimento(int est, Class classe);
    
    public List<ListaConsumidor> getByUsuario(String usr);
}