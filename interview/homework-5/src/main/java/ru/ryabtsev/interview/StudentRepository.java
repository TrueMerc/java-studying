package ru.ryabtsev.interview;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.Collection;

public class StudentRepository implements DaoRepository<Student, Long> {

        private Session currentSession;

        private Transaction currentTransaction;

        public StudentRepository() {}

        @Override
        public Session open() {
            currentSession = getSessionFactory().openSession();
            return currentSession;
        }

        @Override
        public Session openWithTransaction() {
            currentSession = getSessionFactory().openSession();
            currentTransaction = currentSession.beginTransaction();
            return currentSession;
        }

        @Override
        public void close() {
            currentSession.close();
        }

        @Override
        public void closeWithTransaction() {
            currentTransaction.commit();
            currentSession.close();
        }

        private static SessionFactory getSessionFactory() {
            StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                    .configure("hibernate.cfg.xml").build();
            Metadata metadata = new MetadataSources(registry)
                    .getMetadataBuilder().build();
            SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();;
            return sessionFactory;
        }

        public Session getCurrentSession() {
            return currentSession;
        }

        @Override
        public void persist(Student entity) {
            getCurrentSession().save(entity);
        }

        @Override
        public void update(Student entity) {
            getCurrentSession().update(entity);
        }

        @Override
        public Student findById(Long id) {
            Student book = (Student) getCurrentSession().get(Student.class, id);
            return book;
        }

        @Override
        public void delete(Student entity) {
            getCurrentSession().delete(entity);
        }

        @SuppressWarnings("unchecked")
        public Collection<Student> findAll() {
            Collection<Student> students = (Collection<Student>) getCurrentSession().createQuery("from Student").list();
            return students;
        }

    }
