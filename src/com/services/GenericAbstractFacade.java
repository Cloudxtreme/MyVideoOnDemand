package com.services;

import com.entities.EntitiesUtilities;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public abstract class GenericAbstractFacade<T> {
    Class<T> clazz;

    GenericAbstractFacade(Class<T> clazz) {
        this.clazz = clazz;
    }

    protected EntityManager getEntityManager() {
        return Persistence.createEntityManagerFactory(EntitiesUtilities.PERSISTENCE_UNIT_NAME)
                .createEntityManager();
    }

    public T create(T entity) {
        EntityManager em = getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        entity = em.merge(entity);
        transaction.commit();
        em.close();

        return entity;
    }

    public void edit(T entity) {
        EntityManager em = getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.merge(entity);
        transaction.commit();
        em.close();
    }

    public void remove(T entity) {
        EntityManager em = getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        entity = em.merge(entity);
        em.remove(entity);
        transaction.commit();
        em.close();
    }

    public T findById(Object objectId) {
        EntityManager entityManager = Persistence.createEntityManagerFactory(EntitiesUtilities.PERSISTENCE_UNIT_NAME)
                .createEntityManager();
        return entityManager.find(clazz, objectId);
    }

    public List<T> findAll() {
        EntityManager entityManager = getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        CriteriaQuery<T> c = entityManager.getCriteriaBuilder().createQuery(clazz);
        Root<T> from = c.from(clazz);
        c.select(from);
        List<T> resultList = entityManager.createQuery(c).getResultList();

        transaction.commit();
        entityManager.close();
        return resultList;
    }
}
