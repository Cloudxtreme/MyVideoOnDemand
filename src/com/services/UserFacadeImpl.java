package com.services;

import com.entities.EntitiesUtilities;
import com.entities.User;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class UserFacadeImpl extends GenericAbstractFacade<User> {

    public UserFacadeImpl(Class<User> clazz) {
        super(clazz);
    }

    public User login(String username, String password) {
        EntityManager entityManager = Persistence.createEntityManagerFactory(EntitiesUtilities.PERSISTENCE_UNIT_NAME)
                .createEntityManager();
        TypedQuery<User> query = entityManager.createQuery("SELECT u FROM User u WHERE u.username = :username AND u.password = :password", User.class);

        return query.setParameter("username", username).setParameter("password", password).getSingleResult();
    }
}
