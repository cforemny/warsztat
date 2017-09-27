package com.mkyong.controller;

import com.mkyong.date.CourseDate;
import com.mkyong.payment.paymentSummary.Payment;
import com.mkyong.sqlBase.CurrentUrlCutter;
import com.mkyong.sqlBase.StudentListCreator;
import com.mkyong.sqlBase.TableSelector;
import com.mkyong.utils.Student;
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
        prepareSiteObjects(model);
        return "lokalizacje/SolneMiasto";
    }

    @PostMapping("/SolneMiasto")
    public String submitNewStudent(@ModelAttribute Student student, CourseDate courseDate, Model model) {
        studentListCreator.addStudentToList(student, currentTable(adress));
        prepareSiteObjects(model);
        return "lokalizacje/SolneMiasto";
    }

    @PostMapping("SolneMiasto/usun")
    public String deleteStudentFromRecord(@ModelAttribute Student student, Model model) {
        studentListCreator.deleteStudent(student.getId(), currentTable(adress));
        prepareSiteObjects(model);
        return "lokalizacje/SolneMiasto";
    }

    @PostMapping("SolneMiasto/dodajDate")
    public String addNewColumn(@ModelAttribute CourseDate courseDate, Model model) {
        studentListCreator.addNewDate("datysolnemiasto", courseDate.getCurrentDate());
        prepareSiteObjects(model);
        return "lokalizacje/SolneMiasto";
    }

    @PostMapping("SolneMiasto/platnosci")
    public String addPayment(@ModelAttribute Payment payment, Model model) {
        studentListCreator.addNewPayment("platnoscisolnemiasto", payment.getPaymentValue(), payment.getStudentId(), payment.getPaymentDate(), payment.getPaymentType());
        prepareSiteObjects(model);
        return "lokalizacje/SolneMiasto";
    }

    @PostMapping("SolneMiasto/usunPlatnosc")
    public String removePayment(@ModelAttribute Payment payment, Model model) {
        studentListCreator.removePayment("platnoscisolnemiasto", payment.getStudentId(), payment.getPaymentDate());
        prepareSiteObjects(model);
        return "lokalizacje/SolneMiasto";
    }

    @GetMapping("naszeLokalizacje")
    public String showAllOurLocations() {
        return "lokalizacje/naszeLokalizacje";
    }

    private String currentTable(String adress) {
        return currentUrlCutter.getTableNameFromUrl(adress);
    }

    private void prepareSiteObjects(Model model) {
        model.addAttribute("studentList", tableSelector.getStudentListFromTable(currentTable(adress)));
        model.addAttribute("dateList", tableSelector.getDateTable("datysolnemiasto"));
        model.addAttribute("paymentList", tableSelector.getPaymentList("platnoscisolnemiasto"));
        model.addAttribute("courseDate", new CourseDate());
        model.addAttribute("student", new Student());
        model.addAttribute("payment", new Payment());
    }
}
