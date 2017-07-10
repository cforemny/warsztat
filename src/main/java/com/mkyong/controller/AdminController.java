package com.mkyong.controller;

import com.mkyong.SQLBase.TableSelector;
import com.mkyong.payment.paymentSummary.MonthSummary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Cyprian on 2017-07-09.
 */

@Controller

public class AdminController {

    @Autowired
    private TableSelector tableSelector;
    @Autowired
    MonthSummary monthSummary;

    @GetMapping("/admin")
    public String admin(Model model) {

        model.addAttribute("dataMap",monthSummary.prepareButtons());
        return "admin";
    }

    @GetMapping("/{month}")
    public String getMonth(@PathVariable("month") String napis, Model model) {

        model.addAttribute("dataMap",monthSummary.prepareButtons());
        String nowyNapis = napis +" przrobiony na maksa";
        model.addAttribute("napis",nowyNapis);

        return "/admin";
    }

}
