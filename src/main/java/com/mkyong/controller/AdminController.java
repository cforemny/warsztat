package com.mkyong.controller;

import com.mkyong.SQLBase.TableSelector;
import com.mkyong.payment.paymentSummary.MonthIncome;
import com.mkyong.payment.paymentSummary.NumberOfMonths;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.security.acl.Owner;

/**
 * Created by Cyprian on 2017-07-09.
 */

@Controller

public class AdminController {


    @Autowired
    NumberOfMonths numerofmonths;
    @Autowired
    MonthIncome monthIncome;


    @GetMapping("/admin")
    public String admin(Model model) {
        model.addAttribute("dataMap", numerofmonths.prepareButtons());
        return "admin";
    }

    @GetMapping("/admin/{month}")
    public String getMonth(@PathVariable("month") String data, Model model) {

        monthIncome.getCashFromLocations(data);
        model.addAttribute("dataMap", numerofmonths.prepareButtons());
        model.addAttribute("remittancePayment", monthIncome.getRemittamceFromLocations(data));
        model.addAttribute("cashPayment", monthIncome.getCashFromLocations(data));

        return "/admin";
    }



}
