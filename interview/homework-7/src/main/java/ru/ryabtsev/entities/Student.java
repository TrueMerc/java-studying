package ru.ryabtsev.entities;

import lombok.Data;

import javax.persistence.*;


@Data
@Entity
@Table(name = "students.students")
public class Student {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "mark")
    private Integer mark;

    public Student() {
        this.name = "Noname";
        this.mark = 0;
    }

    public Student(String name, int mark) {
        this.name = name;
        this.mark = mark;
    }
}
