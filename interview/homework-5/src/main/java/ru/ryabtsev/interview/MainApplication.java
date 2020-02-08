package ru.ryabtsev.interview;

import java.util.Collection;

/**
 * Provides main application for the fifth homework of 'Coding Interview' course.
 */
public class MainApplication
{
    private static final int STUDENTS_COUNT = 1000;

    public static void main( String[] args )
    {
        StudentRepository studentRepository = new StudentRepository();
        studentRepository.openWithTransaction();

        for(int i = 0; i < 1000; ++i) {
           Student student = new Student("Student " + i, i % 5 + 1);
           studentRepository.persist(student);
        }

        Collection<Student> students = studentRepository.findAll();
        if(students.size() != STUDENTS_COUNT) {
           System.out.println("findAll() method fails.");
        }

        Student student = studentRepository.findById(1L);
        student.setName("Zero student");
        student.setMark(5);

        studentRepository.update(student);

        student = studentRepository.findById(1L);
        System.out.println("Name: " + student.getName() + ", mark: " + student.getMark());

        studentRepository.delete(student);
        students = studentRepository.findAll();
        if(students.size() != STUDENTS_COUNT - 1) {
            System.out.println("delete() method fails.");
        }

        studentRepository.closeWithTransaction();
    }
}
