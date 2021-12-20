package com.geekcap.javaworld.jpa.repository;

import com.geekcap.javaworld.jpa.model.Book;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class BookRepository {
    private EntityManager entityManager;

    public BookRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Optional<Book> save(Book book) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(book);
            entityManager.getTransaction().commit();
            return Optional.of(book);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public Optional<Book> findById(Integer id) {
        Book book = entityManager.find(Book.class, id);
        return book != null ? Optional.of(book) : Optional.empty();
    }

    public List<Book> findAll() {
        return entityManager.createQuery("from Book").getResultList();
    }

    public Optional<Book> findByName(String name) {
        Book book = entityManager.createQuery("SELECT b FROM Book b WHERE b.name = :name", Book.class)
                .setParameter("name", name)
                .getSingleResult();
        return book != null ? Optional.of(book) : Optional.empty();
    }

    public Optional<Book> findByNameNamedQuery(String name) {
        Book book = entityManager.createNamedQuery("Book.findByName", Book.class)
                .setParameter("name", name)
                .getSingleResult();
        return book != null ? Optional.of(book) : Optional.empty();
    }
}
