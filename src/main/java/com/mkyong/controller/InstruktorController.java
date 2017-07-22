package com.mkyong.controller;

import com.mkyong.SQLBase.CashCollectionCreator;
import com.mkyong.SQLBase.EventCreator;
import com.mkyong.SQLBase.ExpenseCreator;
import com.mkyong.SQLBase.PreSchoolCreator;
import com.mkyong.payment.expenseSummary.MonthExpense;
import com.mkyong.payment.paymentSummary.CashSummary;
import com.mkyong.payment.paymentSummary.EventSummary;
import com.mkyong.payment.paymentSummary.NurserySchoolSummary;
import com.mkyong.utils.Event;
import com.mkyong.utils.NurserySchool;
import com.mkyong.utils.Expense;
import com.mkyong.utils.CashCollection;
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
 * Created by Cyprian on 2017-07-15.
 */

@Controller
@RequestMapping("/instruktor")
public class InstruktorController {
    @Autowired
    private ExpenseCreator expenseCreator;
    @Autowired
    private CashCollectionCreator cashCollectionCreator;
    @Autowired
    private PreSchoolCreator preSchoolCreator;
    @Autowired
    private EventCreator eventCreator;
    @Autowired
    private NurserySchoolSummary nurserySchoolSummary;
    @Autowired
    private EventSummary eventSummary;
    @Autowired
    private MonthExpense monthExpense;
    @Autowired
    private CashSummary cashSummary;

    @GetMapping("")
    public String defaultInstruktor(@ModelAttribute String skosna, Model model) {
        String zawartosc = "jakas zawartosc z kontrolera";
        model.addAttribute("zawartosc", zawartosc);
        model.addAttribute("expense", new Expense());
        model.addAttribute("cashCollection", new CashCollection());
        model.addAttribute("nurserySchool", new NurserySchool());
        model.addAttribute("event", new Event());
        model.addAttribute("nurserySchoolList", nurserySchoolSummary.getListOfNurserbySchoolByMonth(getCurrentDate()));
        model.addAttribute("eventList", eventSummary.getListOfEventsByMonth(getCurrentDate()));
        model.addAttribute("expenseList", monthExpense.getExpenseListByMonth(getCurrentDate()));
        model.addAttribute("cashCollectionList", cashSummary.getListOfCashCollectionByMonth(getCurrentDate()));
        return "instruktor";
    }

    @PostMapping("/dodajWydatek")
    public String addExpense(@ModelAttribute NurserySchool nurserySchool, @ModelAttribute CashCollection cashCollection,
                             @ModelAttribute Expense expense,@ModelAttribute Event event, Model model) {
        expenseCreator.insertExpenseToTable(expense);
        return "instruktor";
    }

    @PostMapping("/odbioryGotowki")
    public String getInstructorPayment(@ModelAttribute NurserySchool nurserySchool, @ModelAttribute CashCollection cashCollection,
                                       @ModelAttribute Expense expense,@ModelAttribute Event event, Model model) {
        cashCollectionCreator.insertCashCollectionIntoTable(cashCollection);
        return "instruktor";
    }

    @PostMapping("/obecnoscWPrzedszkolu")
    public String countKidsInPreSchool(@ModelAttribute NurserySchool nurserySchool, @ModelAttribute CashCollection cashCollection,
                                       @ModelAttribute Expense expense,@ModelAttribute Event event, Model model) {
        preSchoolCreator.insertPreschoolIntoTable(nurserySchool);
        return "instruktor";
    }

    @PostMapping("/dodajEvent")
    public String addNewEvent(@ModelAttribute NurserySchool nurserySchool, @ModelAttribute CashCollection cashCollection,
                                       @ModelAttribute Expense expense,@ModelAttribute Event event, Model model) {
        eventCreator.insertNewEventToTable(event);
        return "instruktor";
    }

    public String getCurrentDate(){
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        return dateFormat.format(date);
    }
}
