package br.com.cds.ebc.collaborator.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.validation.Valid;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * Created by Eduardo on 07/01/2017.
 */
public abstract class GenericDAO<T, I extends Serializable> {


    @PersistenceContext
    protected EntityManager entityManager;

    private Class<T> persistedClass;

    public GenericDAO() {
        this.persistedClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    public GenericDAO(Class<T> persistedClass) {
        this.persistedClass = persistedClass;
    }


    public T save(@Valid T entity) {
        entityManager.persist(entity);
        return entity;
    }


    public T update(@Valid T entity) {
        entityManager.merge(entity);
       return entity;
    }


    public void delete(T entity) {
        T mergedEntity = entityManager.merge(entity);
        entityManager.remove(mergedEntity);
    }


    public List<T> findAll() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> query = builder.createQuery(persistedClass);
        query.from(persistedClass);
        return entityManager.createQuery(query).getResultList();
    }


    public T findById(I id) {
        return entityManager.find(persistedClass, id);
    }

}
