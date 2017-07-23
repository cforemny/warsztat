package com.mkyong.controller;

import com.mkyong.date.CourseDate;
import com.mkyong.payment.expenseSummary.MonthExpense;
import com.mkyong.payment.paymentSummary.EventSummary;
import com.mkyong.payment.paymentSummary.MonthIncome;
import com.mkyong.payment.paymentSummary.NumberOfMonths;
import com.mkyong.payment.paymentSummary.NurserySchoolSummary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Cyprian on 2017-07-09.
 */

@Controller
@RequestMapping("/admin")
public class AdminController {


    @Autowired
    private NumberOfMonths numberOfMonths;
    @Autowired
    private MonthIncome monthIncome;
    @Autowired
    private MonthExpense monthExpense;
    @Autowired
    private NurserySchoolSummary nurserySchoolSummary;
    @Autowired
    private EventSummary eventSummary;

    @GetMapping("")
    public String admin(Model model) {
        model.addAttribute("dataMap", numberOfMonths.prepareButtons());
        return "admin";
    }

    @GetMapping("/{month}")
    public String getMonth(@PathVariable("month") String data,  Model model) {

        model.addAttribute("instructorExpense", monthExpense.getInstructorExpenseForMonth(data));
        model.addAttribute("dataMap", numberOfMonths.prepareButtons());
        model.addAttribute("remittancePayment", monthIncome.getPaymentFromLocations(data, "N"));
        model.addAttribute("cashPayment", monthIncome.getPaymentFromLocations(data, "T"));
        model.addAttribute("instructorsCashMap", monthIncome.getCashPerInstrutor(data));
        model.addAttribute("nurserySchoolIncome", nurserySchoolSummary.getPaymentFromNurserySchools(data));
        model.addAttribute("eventsIncome", eventSummary.getIncomFromEvent(data));
        model.addAttribute("miesiac", new CourseDate());

        return "admin";
    }

    @GetMapping("/dodajKosztStaly")
    public String addPermanentExpense( Model model) {

        return "admin";
    }
    @GetMapping("/podgladSzczegolowy")
    public String showDetails( @RequestParam("data") String data, Model model) {
        model.addAttribute("expenseDetailsList", monthExpense.getExpenseListByDate(data));
        model.addAttribute("cashDetailList", monthIncome.getCashPerInstrutor(data));
        return "admin/podgladSzczegolowy";
    }


}
