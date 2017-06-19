package com.mkyong.controller;

import com.mkyong.StudentsList.Student;
import com.mkyong.StudentsList.StudentListCreator;
import com.mkyong.date.CourseDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Cyprian on 2017-06-04.
 */
@Controller
@RequestMapping("/user")
public class InstructorController {

    @Autowired
    private StudentListCreator studentListCreator;


    @GetMapping("/SolneMiasto")
    public String prepareTable(Model model) {
        model.addAttribute("studentList", studentListCreator.getStudentListFromTable("solnemiasto"));
        model.addAttribute("dateList", studentListCreator.getDateTable("daty"));
        model.addAttribute("paymentList", studentListCreator.getPaymentList("obecnosci"));
        model.addAttribute("courseDate", new CourseDate());
        model.addAttribute("student", new Student());
        return "/SolneMiasto";
    }

    @PostMapping("/SolneMiasto")
    public String submitNewStudent(@ModelAttribute Student student, CourseDate courseDate, Model model) {
        studentListCreator.addStudentToList(student, "solnemiasto");
        model.addAttribute("studentList", studentListCreator.getStudentListFromTable("solnemiasto"));
        model.addAttribute("dateList", studentListCreator.getDateTable("daty"));
        model.addAttribute("paymentList", studentListCreator.getPaymentList("obecnosci"));

        model.addAttribute("courseDate", new CourseDate());
        model.addAttribute("student", new Student());

        return "/SolneMiasto";
    }

    @PostMapping("SolneMiasto/usun")
    public String deleteStudentFromRecord(@ModelAttribute Student student, Model model) {
        studentListCreator.deleteStudent(student.getId(), "solnemiasto");
        model.addAttribute("studentList", studentListCreator.getStudentListFromTable("solnemiasto"));
        model.addAttribute("dateList", studentListCreator.getDateTable("daty"));
        model.addAttribute("paymentList", studentListCreator.getPaymentList("obecnosci"));

        model.addAttribute("courseDate", new CourseDate());
        model.addAttribute("student", new Student());
        return "/SolneMiasto";
    }

    @PostMapping("SolneMiasto/dodajDate")
    public String addNewColumn(@ModelAttribute CourseDate courseDate, Model model) {

        studentListCreator.addNewDate("daty", courseDate.getCurrentDate());
        model.addAttribute("studentList", studentListCreator.getStudentListFromTable("solnemiasto"));
        model.addAttribute("dateList", studentListCreator.getDateTable("daty"));
        model.addAttribute("courseDate", new CourseDate());
        model.addAttribute("paymentList", studentListCreator.getPaymentList("obecnosci"));

        model.addAttribute("courseDate", new CourseDate());
        model.addAttribute("student", new Student());
        return "/SolneMiasto";
    }


}
