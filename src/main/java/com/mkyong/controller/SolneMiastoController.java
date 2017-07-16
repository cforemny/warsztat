package com.mkyong.controller;

import com.mkyong.SQLBase.CurrentUrlCutter;
import com.mkyong.SQLBase.TableSelector;
import com.mkyong.StudentsList.Student;
import com.mkyong.SQLBase.StudentListCreator;
import com.mkyong.date.CourseDate;
import com.mkyong.payment.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


/**
 * Created by Cyprian on 2017-06-04.
 */
@Controller
@RequestMapping("/instruktor")
public class SolneMiastoController {

    @Autowired
    private StudentListCreator studentListCreator;
    @Autowired
    private TableSelector tableSelector;
    @Autowired
    private CurrentUrlCutter currentUrlCutter;
    private String adress;

    @GetMapping("/SolneMiasto")
    public String showLocation(Model model) {
        this.adress = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest().getRequestURI().toString();
        preapreSiteObjects(model);
        return "lokalizacje/SolneMiasto";
    }

    @PostMapping("/SolneMiasto")
    public String submitNewStudent(@ModelAttribute Student student, CourseDate courseDate, Model model) {
        studentListCreator.addStudentToList(student, currentTable(adress));
        preapreSiteObjects(model);
        return "/lokalizacje/SolneMiasto";
    }

    @PostMapping("SolneMiasto/usun")
    public String deleteStudentFromRecord(@ModelAttribute Student student, Model model) {
        studentListCreator.deleteStudent(student.getId(), currentTable(adress));
        preapreSiteObjects(model);
        return "/lokalizacje/SolneMiasto";
    }

    @PostMapping("SolneMiasto/dodajDate")
    public String addNewColumn(@ModelAttribute CourseDate courseDate, Model model) {
        studentListCreator.addNewDate("datysolnemiasto", courseDate.getCurrentDate());
        preapreSiteObjects(model);
        return "/lokalizacje/SolneMiasto";
    }

    @PostMapping("SolneMiasto/platnosci")
    public String addPayment(@ModelAttribute Payment payment, Model model) {
        studentListCreator.addNewPayment("platnosciSolneMiasto", payment.getPaymentValue(), payment.getStudentId(), payment.getPaymentDate(), payment.getPaymentType());
        preapreSiteObjects(model);
        return "/lokalizacje/SolneMiasto";
    }

    @GetMapping("naszeLokalizacje")
    public String showAllOurLocations() {
        return "lokalizacje/naszeLokalizacje";
    }

    private String currentTable(String adress){
        return currentUrlCutter.getTableNameFromUrl(adress);
    }
    private void preapreSiteObjects(Model model) {
        model.addAttribute("studentList", tableSelector.getStudentListFromTable(currentTable(adress)));
        model.addAttribute("dateList", tableSelector.getDateTable("datysolnemiasto"));
        model.addAttribute("paymentList", tableSelector.getPaymentList("platnosciSolneMiasto"));
        model.addAttribute("courseDate", new CourseDate());
        model.addAttribute("student", new Student());
        model.addAttribute("payment", new Payment());
    }
}
