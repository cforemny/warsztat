package com.mkyong.controller;

import com.mkyong.SQLBase.CashCollectionCreator;
import com.mkyong.SQLBase.ExpenseCreator;
import com.mkyong.SQLBase.PreSchoolCreator;
import com.mkyong.StudentsList.NurserySchool;
import com.mkyong.payment.expenseSummary.Expense;
import com.mkyong.payment.paymentSummary.CashCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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

    @GetMapping("")
    public String defaultInstruktor(@ModelAttribute String skosna, Model model) {
        String zawartosc = "jakas zawartosc z kontrolera";
        model.addAttribute("zawartosc", zawartosc);
        model.addAttribute("expense", new Expense());
        model.addAttribute("cashCollection", new CashCollection());
        model.addAttribute("nurserySchool", new NurserySchool());
        return "instruktor";
    }

    @PostMapping("/dodajWydatek")
    public String addExpense(@ModelAttribute NurserySchool nurserySchool, @ModelAttribute CashCollection cashCollection,
                             @ModelAttribute Expense expense, Model model) {
        expenseCreator.insertExpenseToTable(expense);
        return "instruktor";
    }

    @PostMapping("/odbioryGotowki")
    public String getInstructorPayment(@ModelAttribute NurserySchool nurserySchool, @ModelAttribute CashCollection cashCollection,
                                       @ModelAttribute Expense expense, Model model) {
        cashCollectionCreator.insertCashCollectionIntoTable(cashCollection);
        return "instruktor";
    }

    @PostMapping("/obecnoscWPrzedszkolu")
    public String countKidsInPreSchool(@ModelAttribute NurserySchool nurserySchool, @ModelAttribute CashCollection cashCollection,
                                       @ModelAttribute Expense expense, Model model) {
        preSchoolCreator.insertPreschoolIntoTable(nurserySchool);
        return "instruktor";
    }

}
