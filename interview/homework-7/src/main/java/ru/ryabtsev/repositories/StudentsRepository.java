package ru.ryabtsev.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.ryabtsev.entities.Student;

import java.util.Collection;

@Repository
public interface StudentsRepository extends CrudRepository<Student, Long> {
    public Student findOneById(Long id);

    public Collection<Student> findAll();

}
