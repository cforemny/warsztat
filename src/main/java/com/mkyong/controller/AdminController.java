package com.mkyong.controller;

import com.mkyong.payment.expenseSummary.MonthExpense;
import com.mkyong.payment.expenseSummary.PermanentExpense;
import com.mkyong.payment.paymentSummary.*;
import com.mkyong.utils.Debt;
import com.mkyong.utils.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

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
    @Autowired
    private MonthIncomeForLocations monthIncomeForLocations;
    @Autowired
    private PermanentExpense permanentExpense;
    @Autowired
    private Debt debt;


    @GetMapping("")
    public String admin(Model model) throws SQLException, ClassNotFoundException {
        model.addAttribute("dataMap", numberOfMonths.prepareButtons());
        model.addAttribute("event", new Event());
        return "admin";
    }
    @GetMapping("/wlasciciele")
    public String owners(Model model   ){
        model.addAttribute("debts",debt.getDebtList());
        model.addAttribute("payedValue",debt.payedValue());
        return "admin/wlasciciele";
    }

    @GetMapping("/{month}")
    public String getMonth(@PathVariable("month") String data, Model model) {

        prepareDataForDetails(data, model);
        model.addAttribute("event", new Event());
        return "admin";
    }

    @GetMapping("/kosztyStale")
    public String showPermanentExpense(@RequestParam("data") String data, Model model) throws SQLException, ClassNotFoundException {
        model.addAttribute("permanentExpenses", permanentExpense.getPermanentExpense(data));
        model.addAttribute("permanentExpense", new PermanentExpense());
        return "admin/kosztyStale";
    }

    @GetMapping("/zyski")
    public String getIncome(@RequestParam("data") String data, Model model) {
        model.addAttribute("incomeSummary", addAllIncome(data));
        model.addAttribute("paybackExpenses", monthExpense.getExpensesToPayback(data));
        model.addAttribute("noneBackExpenses", monthExpense.getNoneBackExpenses(data));
        model.addAttribute("incomeForTaxes", addAllIncomeForTaxes(data));
        model.addAttribute("vat", countVat(data));
        model.addAttribute("tax", incomeTax(data));
        model.addAttribute("zyskNowySacz", monthIncome.incomeFromNowySacz(data));
        return "admin/zyski";
    }

    @PostMapping("/kosztyStale")
    public String addPermanentExpense(@RequestParam("data") String data, Model model, @ModelAttribute PermanentExpense expense) {
        permanentExpense.insertPermanentExpense(expense);
        model.addAttribute("permanentExpenses", permanentExpense.getPermanentExpense(data));
        return "admin/kosztyStale";
    }


    @GetMapping("/podgladSzczegolowy")
    public String showDetails(@RequestParam("data") String data, Model model) {

        model.addAttribute("expenseDetailsList", monthExpense.getExpenseListByDate(data));
        model.addAttribute("cashDetailList", monthIncome.getCashPerInstructor(data));
        model.addAttribute("locationsIncomeCash", monthIncomeForLocations.getLocationSummary(data, "T"));
        model.addAttribute("locationsIncomeRemittance", monthIncomeForLocations.getLocationSummary(data, "N"));
        model.addAttribute("nurserySchoolDetails", nurserySchoolSummary.getListOfNurserySchoolByMonth(data));
        model.addAttribute("eventListDetails", eventSummary.getListOfEventsByMonth(data));
        return "admin/podgladSzczegolowy";
    }

    private double addAllIncome(String data) {

        double monthIncomeSummary = monthIncome.getPaymentFromLocations(data, "N") +
                monthIncome.getPaymentFromLocations(data, "T") +
                eventSummary.getIncomeFromEvent(data, "T") + eventSummary.getIncomeFromEvent(data, "N") +
                nurserySchoolSummary.getPaymentFromNurserySchools(data);

        return monthIncomeSummary;

    }

    private double addAllIncomeForTaxes(String data) {

        double incomeForTaxes = monthIncome.getPaymentFromLocations(data, "N") + eventSummary.getIncomeFromEvent(data, "T")
                + nurserySchoolSummary.getPaymentFromNurserySchools(data);

        return incomeForTaxes;
    }

    private double countVat(String data) {

        double vat;
        double expenses = monthExpense.getExpensesToPayback(data);
        double income = addAllIncomeForTaxes(data);
        vat = (income - income / 1.23) - (expenses - expenses / 1.23);
        return (int) vat;
    }

    private double incomeTax(String data) {

        double tax;
        double expenses = monthExpense.getExpensesToPayback(data);
        tax = ((addAllIncomeForTaxes(data) / 1.23) - (expenses / 1.23)) * 0.19;
        return (int) tax;
    }

    private void prepareDataForDetails(String data, Model model) {

        model.addAttribute("instructorExpense", monthExpense.getInstructorExpenseForMonth(data));
        model.addAttribute("dataMap", numberOfMonths.prepareButtons());
        model.addAttribute("remittancePayment", monthIncome.getPaymentFromLocations(data, "N"));
        model.addAttribute("cashPayment", monthIncome.getPaymentFromLocations(data, "T"));

        model.addAttribute("monthCash", monthIncome.getCashByDate(data) - monthExpense.getInstructorExpenseForMonth(data));
        model.addAttribute("nurserySchoolIncome", nurserySchoolSummary.getPaymentFromNurserySchools(data));
        model.addAttribute("eventsIncome", eventSummary.getIncomeFromEvent(data, "T") + eventSummary.getIncomeFromEvent(data, "N"));
        model.addAttribute("adminExpensesSummary", permanentExpense.getPermanenetExpenseSummary(data));
        model.addAttribute("event", new Event());

    }
}
