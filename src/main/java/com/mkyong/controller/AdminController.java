package com.mkyong.controller;

import com.mkyong.payment.expenseSummary.MonthExpense;
import com.mkyong.payment.paymentSummary.MonthIncome;
import com.mkyong.payment.paymentSummary.NumberOfMonths;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Created by Cyprian on 2017-07-09.
 */

@Controller

public class AdminController {


    @Autowired
    NumberOfMonths numberOfMonths;
    @Autowired
    MonthIncome monthIncome;
    @Autowired
    MonthExpense monthExpense;

    @GetMapping("/admin")
    public String admin(Model model) {
        model.addAttribute("dataMap", numberOfMonths.prepareButtons());
        return "admin";
    }

    @GetMapping("/admin/{month}")
    public String getMonth(@PathVariable("month") String data, Model model) {


        model.addAttribute("instructorExpense",monthExpense.getInstructorExpenseForMonth(data));
        model.addAttribute("dataMap", numberOfMonths.prepareButtons());
        model.addAttribute("remittancePayment", monthIncome.getRemittamceFromLocations(data));
        model.addAttribute("cashPayment", monthIncome.getCashFromLocations(data));

        return "/admin";
    }



}
