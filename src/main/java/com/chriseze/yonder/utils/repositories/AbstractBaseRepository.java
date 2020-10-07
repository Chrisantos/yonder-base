package com.chriseze.yonder.utils.repositories;

import javax.persistence.EntityManager;

public abstract class AbstractBaseRepository {

    public abstract EntityManager getEntityManager();

    public void create(Object entity) {
        getEntityManager().persist(entity);
    }

    public void delete(Object entity) {
        getEntityManager().remove(entity);
    }

    public <T> T update(T entity) {
        return getEntityManager().merge(entity);
    }

    public <T> T findById(Class<T> entity, Object primaryKey) {
        return getEntityManager().find(entity, primaryKey);
    }
}
