package com.pg.Lesson007.repository;

import com.pg.Lesson007.model.Book;
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

    public List<Book> getAllBooks(){
        TypedQuery<Book> query = bookEntityManager.createQuery("SELECT b FROM Book b", Book.class);
        return query.getResultList();
    }

    public List<Book> bookByTitle(String title){
        TypedQuery<Book> query = bookEntityManager.createQuery("SELECT b FROM Book b WHERE b.title = :title", Book.class);
        query.setParameter("title", title);
        return query.getResultList();
    }

    public List<Book> getAvailableBooksByTitle(String title){
        TypedQuery<Book> query = bookEntityManager.createQuery("SELECT b FROM Book b WHERE b.title = :title AND b.student IS NULL", Book.class);
        query.setParameter("title", title);
        return query.getResultList();
    }

    public List<Book> getBorrowedBooksByTitle(String title){
        TypedQuery<Book> query = bookEntityManager.createQuery("SELECT b FROM Book b WHERE b.title = :title AND b.student IS NOT NULL", Book.class);
        query.setParameter("title", title);
        return query.getResultList();
    }

    public void addBook(Book book){
        bookEntityManager.persist(book);
    }

    public void updateBook(Book book) {
        bookEntityManager.merge(book);
    }

    public Book bookById(Integer id) {
        return bookEntityManager.find(Book.class, id);
    }
}
