package com.mkyong.controller;

import com.mkyong.payment.paymentSummary.NumberOfMonths;
import com.mkyong.sqlBase.JobSummaryCreator;
import com.mkyong.utils.Instructor;
import com.mkyong.utils.WorkSummary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Cyprian on 2017-09-25.
 */

@Controller
@RequestMapping("/manager")
public class ManagerController {

    @Autowired
    private JobSummaryCreator jobSummaryCreator;
    @Autowired
    private NumberOfMonths numberOfMonths;

    @GetMapping("")
    public String getInstrucorWorkList(Model model) {
        model.addAttribute("dataMap", numberOfMonths.prepareButtons());
        model.addAttribute("instructorMap", jobSummaryCreator.getInstrucorsList(getCurrentDate()));
        model.addAttribute("instructor", new Instructor());

        return "manager";
    }

    @PostMapping("")
    public String insertWorkingHours(@ModelAttribute Instructor instructor, @ModelAttribute WorkSummary workSummaryDate, Model model) {


        jobSummaryCreator.insertWorkingHour(instructor);
        model.addAttribute("instructorMap", jobSummaryCreator.getInstrucorsList(getCurrentDate()));
        model.addAttribute("instructor", new Instructor());
        return "manager";
    }

    private String getCurrentDate() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        return dateFormat.format(date);
    }
}