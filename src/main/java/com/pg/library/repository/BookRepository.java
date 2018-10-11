package com.pg.library.repository;

import com.pg.library.model.Book;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class BookRepository {

    @PersistenceContext
    EntityManager bookEntityManager;

    public List<Book> list(){
        TypedQuery<Book> query = bookEntityManager.createQuery("SELECT b FROM Book b", Book.class);
        return query.getResultList();
    }

    public List<Book> borrowedList(){
        TypedQuery<Book> query = bookEntityManager.createQuery("SELECT b FROM Book b WHERE b.user IS NOT NULL", Book.class);
        return query.getResultList();
    }

    public List<Book> availableList(){
        TypedQuery<Book> query = bookEntityManager.createQuery("SELECT b FROM Book b WHERE b.user IS NULL", Book.class);
        return query.getResultList();
    }

    public List<Book> searchByWord(String word){
        TypedQuery<Book> query = bookEntityManager.createQuery("SELECT b FROM Book b WHERE b.title LIKE :word OR b.author LIKE :word", Book.class);
        query.setParameter("word", "%"+word+"%");
        return query.getResultList();
    }

    public List<Book> searchAvailableByWord(String word){
        TypedQuery<Book> query = bookEntityManager.createQuery("SELECT b FROM Book b WHERE (b.title LIKE :word OR b.author LIKE :word) AND b.user IS NULL", Book.class);
        query.setParameter("word", "%"+word+"%");
        return query.getResultList();
    }

    public List<Book> searchBorrowedByWord(String word){
        TypedQuery<Book> query = bookEntityManager.createQuery("SELECT b FROM Book b WHERE (b.title LIKE :word OR b.author LIKE :word) AND b.user IS NOT NULL", Book.class);
        query.setParameter("word", "%"+word+"%");
        return query.getResultList();
    }

    public void add(Book book){
        bookEntityManager.persist(book);
    }

    public void update(Book book) {
        bookEntityManager.merge(book);
    }

    public Book searchById(Integer id) {
        return bookEntityManager.find(Book.class, id);
    }
}
