package com.mkyong.controllers;

import com.mkyong.date.CourseDate;
import com.mkyong.payment.paymentSummary.Payment;
import com.mkyong.sqlBase.CurrentUrlCutter;
import com.mkyong.sqlBase.StudentListCreator;
import com.mkyong.sqlBase.TableSelector;
import com.mkyong.utils.Checkbox;
import com.mkyong.utils.Date;
import com.mkyong.utils.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.List;


/**
 * Created by Cyprian on 2017-06-04.
 */
@Controller
@RequestMapping("/instruktor")
public class LocationsController {

    @Autowired
    private StudentListCreator studentListCreator;
    @Autowired
    private TableSelector tableSelector;
    @Autowired
    private CurrentUrlCutter currentUrlCutter;
    private String adress;
    private String location;


    @GetMapping("/{location}")
    public String getLocation(@PathVariable("location") String location, Model model) {
        this.adress = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest().getRequestURI().toString();
        this.location = location;
        tableSelector.removeDuplicatesFromPayments("platnosci"+location.toLowerCase());
        prepareSiteObjects(model);
        model.addAttribute("location", location);
        return "lokalizacje/Lokalizacja";
    }

    @PostMapping("/{location}/dodajStudenta")
    public String submitNewStudent(@ModelAttribute Student student,@PathVariable("location") String location, CourseDate courseDate, Model model) {
        this.location = location;
        List studentListFromTable = tableSelector.getStudentListFromTable(currentTable(adress), false);
        int studentListFromTableSize = studentListFromTable.size();
        studentListCreator.addStudentToList(student, currentTable(adress), studentListFromTableSize+1);
        prepareSiteObjects(model);
        return "lokalizacje/Lokalizacja";
    }

    @PostMapping("{location}/usun")
    public String deleteStudentFromRecord(@ModelAttribute Student student, @PathVariable("location") String location, Model model) {
        this.location = location;
        studentListCreator.deleteStudent(student.getId(), currentTable(adress));
        prepareSiteObjects(model);
        return "lokalizacje/Lokalizacja";
    }

    @PostMapping("{location}/dodajDate")
    public String addNewColumn(@ModelAttribute CourseDate courseDate,@PathVariable("location") String location, Model model) {
        this.location = location;
        studentListCreator.addNewDate("daty" + location.toLowerCase(), courseDate.getCurrentDate());
        prepareSiteObjects(model);
        return "lokalizacje/Lokalizacja";
    }

    @PostMapping("{location}/platnosci")
    public String addPayment(@ModelAttribute Payment payment, @PathVariable("location") String location, Model model) {
        this.location = location;
        studentListCreator.addNewPayment("platnosci" + location.toLowerCase(), payment, payment.getStudentId(), payment.getPaymentDate(), payment.getPaymentType());
        prepareSiteObjects(model);
        return "lokalizacje/Lokalizacja";
    }

    @PostMapping("{location}/grupowaPlatnosc")
    public String addGroupPayment(@ModelAttribute Payment payment, @PathVariable("location") String location, Model model) {
        this.location = location;
        studentListCreator.addNewPayment("platnosci" + location.toLowerCase(), payment, payment.getStudentId(), payment.getPaymentDate(), payment.getPaymentType());
        prepareSiteObjects(model);
        return "lokalizacje/Lokalizacja";
    }

    @PostMapping("{location}/usunPlatnosc")
    public String removePayment(@ModelAttribute Payment payment, @PathVariable("location") String location,Model model) {
        this.location = location;
        studentListCreator.removePayment("platnosci" + location.toLowerCase(), payment.getStudentId(), payment.getPaymentDate());
        prepareSiteObjects(model);
        return "lokalizacje/Lokalizacja";
    }
    @PostMapping("{location}/usunDate")
    public String removeDate(@ModelAttribute Date data, @PathVariable("location") String location, Model model) {
        this.location = location;
        studentListCreator.removePayment("daty" + location.toLowerCase(), data.getDate());
        prepareSiteObjects(model);
        return "lokalizacje/Lokalizacja";
    }

    @PostMapping("{location}/wyswietlWszystko")
    public String showAll(@ModelAttribute Payment payment, Model model, @PathVariable("location") String location, @ModelAttribute Checkbox checkbox) {
        this.location = location;
        prepareSiteObjects(model);
        model.addAttribute("dateList", tableSelector.getDateTable("daty" + location.toLowerCase(),checkbox.isCheckbox()));
        model.addAttribute("studentList", tableSelector.getStudentListFromTable(currentTable(adress),checkbox.isCheckbox()));
        return "lokalizacje/Lokalizacja";
    }

    private String currentTable(String adress) {
        return currentUrlCutter.getTableNameFromUrl(adress);
    }

    private void prepareSiteObjects(Model model) {

        model.addAttribute("studentList", tableSelector.getStudentListFromTable(currentTable(adress),true));
        model.addAttribute("studentListForCount", tableSelector.getStudentListFromTable(currentTable(adress),false));
        model.addAttribute("dateList", tableSelector.getDateTable("daty" + location.toLowerCase(),true));
        model.addAttribute("paymentList", tableSelector.getPaymentList("platnosci" + location.toLowerCase()));
        model.addAttribute("courseDate", new CourseDate());
        model.addAttribute("student", new Student());
        model.addAttribute("payment", new Payment(1));
        model.addAttribute("data", new Date());
        model.addAttribute("checkbox", new Checkbox(true));
    }




}
