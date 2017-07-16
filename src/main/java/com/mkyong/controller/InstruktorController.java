package com.mkyong.controller;

import com.mkyong.SQLBase.ExpenseCreator;
import com.mkyong.payment.Expense;
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

    @GetMapping("")
    public String defaultInstruktor(@ModelAttribute String skosna, Model model) {
        String zawartosc = "jakas zawartosc z kontrolera";
        model.addAttribute("zawartosc", zawartosc);
        model.addAttribute("expense", new Expense());
        return "/instruktor";
    }

    @PostMapping("/dodajWydatek")
    public String addExpense(@ModelAttribute Expense expense, Model model){
        expenseCreator.insertExpenseToTable(expense);
        return "/instruktor";
    }

}
