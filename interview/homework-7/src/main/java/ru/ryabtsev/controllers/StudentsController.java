package ru.ryabtsev.controllers;

import ru.ryabtsev.entities.Student;
import ru.ryabtsev.services.StudentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;

@Controller
@RequestMapping("/students")
public class StudentsController {
    private StudentsService studentsService;

    @Autowired
    public void setStudentsService(StudentsService studentsService) {
        this.studentsService = studentsService;
    }    
    
    public StudentsController() {}

    @RequestMapping("/showAll")
    public String showAll(Model model) {
        Collection<Student> students = studentsService.getAll();
        model.addAttribute("students", students );
        return "all-students-list";
    }

    @RequestMapping(path="/showStudent", method=RequestMethod.GET)
    public String showStudentById(Model model, @RequestParam int id) {
        Student student = studentsService.getStudentById(new Long(id));
        model.addAttribute("student", student);
        return "student-form";
    }

    @RequestMapping(path="/saveStudent", method=RequestMethod.GET)
    public String saveStudent(@RequestParam Long id, @RequestParam String name, @RequestParam int mark) {
        Student student = new Student();
        student.setId(id);
        student.setName(name);
        student.setMark(mark);
        studentsService.save(student);
        return "redirect:showAll";
    }


    @RequestMapping(path="/deleteStudentById", method = RequestMethod.GET)
    public String deleteStudentById(@RequestParam Long id) {
        studentsService.deleteById(id);
        return "redirect:showAll";
    }
}
