package com.mkyong.controller;

import com.mkyong.payment.paymentSummary.NumberOfMonths;
import com.mkyong.sqlBase.JobSummaryCreator;
import com.mkyong.utils.Event;
import com.mkyong.utils.WorkSummary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
    public String getInstrucorWorkList(Model model){
        model.addAttribute("dataMap", numberOfMonths.prepareButtons());
        model.addAttribute("instructorMap", jobSummaryCreator.getInstrucorsList());
        model.addAttribute("workSummary", new WorkSummary());

        return "manager";
    }

    @PostMapping("")
    public String insertWorkingHours(@ModelAttribute WorkSummary workSummary, Model model){


        model.addAttribute("dataMap", numberOfMonths.prepareButtons());
        model.addAttribute("instructorMap", jobSummaryCreator.getInstrucorsList());
        return "manager";
    }
}
