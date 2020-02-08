package ru.ryabtsev.interview;

import org.hibernate.Session;

import java.util.Collection;

/**
 * Provides interface for data access objects.
 */
public interface DaoRepository<T, U> {

    void close();

    void closeWithTransaction();

    void delete(T entity);

    Collection<T> findAll();

    T findById(U id);

    void persist(T entity);

    Session open();

    Session openWithTransaction();

    void update(T entity);
}