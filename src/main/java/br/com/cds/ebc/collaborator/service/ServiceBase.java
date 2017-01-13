package br.com.cds.ebc.collaborator.service;

import br.com.cds.ebc.collaborator.dao.GenericDAO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

/**
 * Created by eduardo.costa on 11/01/2017.
 */
@Transactional
@Service
public abstract class ServiceBase<T, I extends Serializable> {

    protected abstract GenericDAO<T, I> getDAO();

    public T save(T entity) {
        return getDAO().save(entity);
    }

    public T update(T entity) {
       return getDAO().update(entity);
    }


    public void delete(T entity) {
       getDAO().delete(entity);
    }


    public List<T> findAll() {
       return getDAO().findAll();
    }


    public T findById(I id) {
        return getDAO().findById(id);
    }

}
