package com.pg.library.repository;

import com.pg.library.model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class UserRepository {

    @PersistenceContext
    EntityManager userEntityManager;

    public List<User> list() {
        TypedQuery<User> query = userEntityManager.createQuery("SELECT u FROM User u", User.class);
        return query.getResultList();
    }

    public List<User> searchByWord(String word) {
        TypedQuery<User> query = userEntityManager.createQuery("SELECT u FROM User u WHERE u.name LIKE :word OR u.surname LIKE :word OR u.course LIKE :word", User.class);
        query.setParameter("word", "%"+word+"%");
        return query.getResultList();
    }

    public void add(User user) {
        userEntityManager.persist(user);
    }

    public void update(User user) {
        userEntityManager.merge(user);
    }

    public User searchById(Integer id) {
        return userEntityManager.find(User.class, id);
    }

    public void delete(User user) {
        userEntityManager.remove(user);
    }
}
