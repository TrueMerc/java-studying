package ru.ryabtsev.controllers;

import ru.ryabtsev.entities.Student;
import ru.ryabtsev.services.StudentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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

//    @RequestMapping("/showForm")
//    public String showSimpleForm(Model model) {
//        Student student = new Student();
//        model.addAttribute("student", student);
//        return "student-form";
//    }
//
//    @RequestMapping("/processForm")
//    public String processForm(@ModelAttribute("student") Student student) {
//        System.out.println(student.getName() + " " + student.getMark());
//        return "student-form-result";
//    }
    
    // http://localhost:8189/students/showStudentById?id=5
    @RequestMapping(path="/showStudentById", method=RequestMethod.GET)
    public String showStudentById(Model model, @RequestParam int id) {
        Student student = studentsService.getStudentById(new Long(id));
        model.addAttribute("student", student);
        return "student-form-result";
    }
    
    @RequestMapping(path="/getStudentById", method=RequestMethod.GET)
    @ResponseBody
    public Student getStudentById(@RequestParam int id) {
        Student student = studentsService.getStudentById(new Long(id));
        return student;
    }
    
    @RequestMapping(path="/getStudentById/{sid}", method=RequestMethod.GET)
    @ResponseBody
    public Student getStudentByIdFromPath(@PathVariable("sid") int id) {
        Student student = studentsService.getStudentById(new Long(id));
        return student;
    }

    @RequestMapping(path="/deleteStudentById", method = RequestMethod.GET)
    public String deleteStudentById(@RequestParam Long id) {
        studentsService.deleteById(id);
        return "redirect:/showAll";
    }
}
