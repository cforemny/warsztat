package com.mkyong.controller;

import com.mkyong.SQLBase.TableSelector;
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
    private TableSelector tableSelector;
    @Autowired
    NumberOfMonths numerofmonths;
    @Autowired
    MonthIncome monthIncome;

    @GetMapping("/admin")
    public String admin(Model model) {
        monthIncome.preapareTableList();

        model.addAttribute("dataMap", numerofmonths.prepareButtons());
        return "admin";
    }

    @GetMapping("/{month}")
    public String getMonth(@PathVariable("month") String napis, Model model) {

        monthIncome.getPaymentFromMonth(napis);
        model.addAttribute("dataMap", numerofmonths.prepareButtons());
        String nowyNapis = napis +" przrobiony na maksa";
        model.addAttribute("napis",nowyNapis);

        return "/admin";
    }

}
