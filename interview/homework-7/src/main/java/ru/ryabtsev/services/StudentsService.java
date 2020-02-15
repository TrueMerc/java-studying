package ru.ryabtsev.services;

import ru.ryabtsev.entities.Student;
import ru.ryabtsev.repositories.StudentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class StudentsService {
    private StudentsRepository studentsRepository;

    @Autowired
    public void setStudentsRepository(StudentsRepository studentsRepository) {
        this.studentsRepository = studentsRepository;
    }

    public StudentsService() {}

    public Student getStudentById(Long id) {
        return studentsRepository.findOneById(id);
    }

    public Collection<Student> getAll() {
        return studentsRepository.findAll();
    }

    public void deleteById(Long id) {
        studentsRepository.deleteById(id);
    }
}
