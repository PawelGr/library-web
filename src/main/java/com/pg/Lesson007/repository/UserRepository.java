package com.pg.Lesson007.repository;

import com.pg.Lesson007.model.User;
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
    EntityManager studentEntityManager;

    public List<User> list() {
        TypedQuery<User> query = studentEntityManager.createQuery("SELECT u FROM User u", User.class);
        return query.getResultList();
    }

    public List<User> searchByWord(String word) {
        TypedQuery<User> query = studentEntityManager.createQuery("SELECT u FROM User u WHERE u.name LIKE :word OR u.surname LIKE :word OR u.course LIKE :word", User.class);
        query.setParameter("word", "%"+word+"%");
        return query.getResultList();
    }

    public void add(User user) {
        studentEntityManager.persist(user);
    }

    public void update(User user) {
        studentEntityManager.merge(user);
    }

    public User searchById(Integer id) {
        return studentEntityManager.find(User.class, id);
    }

    public void delete(User user) {
        studentEntityManager.remove(user);
    }
}
