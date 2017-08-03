package com.mkyong.controller;

import com.mkyong.payment.expenseSummary.MonthExpense;
import com.mkyong.payment.paymentSummary.CashSummary;
import com.mkyong.payment.paymentSummary.EventSummary;
import com.mkyong.payment.paymentSummary.NurserySchoolSummary;
import com.mkyong.sqlBase.CashCollectionCreator;
import com.mkyong.sqlBase.EventCreator;
import com.mkyong.sqlBase.ExpenseCreator;
import com.mkyong.sqlBase.PreSchoolCreator;
import com.mkyong.utils.CashCollection;
import com.mkyong.utils.Event;
import com.mkyong.utils.Expense;
import com.mkyong.utils.NurserySchool;
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


        model.addAttribute("expense", new Expense());
        model.addAttribute("cashCollection", new CashCollection());
        model.addAttribute("nurserySchool", new NurserySchool());
        model.addAttribute("event", new Event());
        model.addAttribute("nurserySchoolList", nurserySchoolSummary.getListOfNurserySchoolByMonth(getCurrentDate()));
        model.addAttribute("eventList", eventSummary.getListOfEventsByMonth(getCurrentDate()));
        model.addAttribute("expenseList", monthExpense.getExpenseListByDate(getCurrentDate()));
        model.addAttribute("cashCollectionList", cashSummary.getListOfCashCollectionByMonth(getCurrentDate()));
        return "instruktor";
    }

    @PostMapping("")
    public String addExpense(@ModelAttribute NurserySchool nurserySchool, @ModelAttribute CashCollection cashCollection,
                             @ModelAttribute Expense expense, @ModelAttribute Event event, Model model) {
        if(expense.getExpenseValue() != null)
        expenseCreator.insertExpenseToTable(expense);
        if(cashCollection.getValue() != null)
        cashCollectionCreator.insertCashCollectionIntoTable(cashCollection);
        if(nurserySchool.getName() != null)
        preSchoolCreator.insertPreschoolIntoTable(nurserySchool);
        if(event.getEventType() != null)
        eventCreator.insertNewEventToTable(event);

        model.addAttribute("expense", new Expense());
        model.addAttribute("cashCollection", new CashCollection());
        model.addAttribute("nurserySchool", new NurserySchool());
        model.addAttribute("event", new Event());
        model.addAttribute("nurserySchoolList", nurserySchoolSummary.getListOfNurserySchoolByMonth(getCurrentDate()));
        model.addAttribute("eventList", eventSummary.getListOfEventsByMonth(getCurrentDate()));
        model.addAttribute("expenseList", monthExpense.getExpenseListByDate(getCurrentDate()));
        model.addAttribute("cashCollectionList", cashSummary.getListOfCashCollectionByMonth(getCurrentDate()));
        return "instruktor";
    }



    public String getCurrentDate() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        return dateFormat.format(date);
    }
}
