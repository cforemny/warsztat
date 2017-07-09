package com.mkyong.controller;

import com.mkyong.SQLBase.TableSelector;
import com.mkyong.payment.paymentSummary.MonthSummary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Cyprian on 2017-07-09.
 */

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private TableSelector tableSelector;
    @Autowired
    MonthSummary monthSummary;

    @GetMapping()
    public String admin(Model model) {

        model.addAttribute("dataSet",monthSummary.prepareMonthButtonListForSite());
        return "/admin";
    }


}
