package com.mkyong.controllers;

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
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

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
    public String defaultInstruktor(Model model, @ModelAttribute com.mkyong.utils.Date wybranaData) {


        createAtributtes(model, wybranaData);
        return "instruktor";
    }

    @PostMapping("")
    public String addExpense(@ModelAttribute NurserySchool nurserySchool,
                             @ModelAttribute CashCollection cashCollection, @ModelAttribute Expense expense,
                             @ModelAttribute Event event, @ModelAttribute com.mkyong.utils.Date wybranaData, Model model) {

        if (expense.getKwota() != null)
            expenseCreator.insertExpenseToTable(expense);
        if (cashCollection.getMiejsce() != null)
            cashCollectionCreator.insertCashCollectionIntoTable(cashCollection);
        if (nurserySchool.getNazwaPrzedszkola() != null)
            preSchoolCreator.insertPreschoolIntoTable(nurserySchool);
        if (event.getRodzajEventu() != null)
            eventCreator.insertNewEventToTable(event);


        createAtributtes(model, wybranaData);
        return "instruktor";
    }

    @PostMapping("/")
    public String confirmEventIncome(@RequestParam("potwierdzenie") String potwierdzenie, @RequestParam("cenaWydarzenia") String cenaWydarzenia,
                                     @RequestParam("dataWydarzenia") String dataWydarzenia, @ModelAttribute com.mkyong.utils.Date wybranaData, Model model) {
        if (potwierdzenie.equals("T"))
            eventCreator.updateEventPayment(dataWydarzenia, cenaWydarzenia);
        createAtributtes(model, wybranaData);

        return "instruktor";
    }

    @PostMapping("/przedszkole/")
    public String confirmNurseryIncome(@RequestParam("potwierdzeniePrzedszkola") String potwierdzeniePrzedszkola, @RequestParam("liczbaDzieci") String liczbaDzieci,
                                       @RequestParam("dataPrzedszkola") String dataPrzedszkola, @ModelAttribute com.mkyong.utils.Date wybranaData, Model model) {
        if (potwierdzeniePrzedszkola.equals("T"))
            preSchoolCreator.updateNurseryPayment(dataPrzedszkola, liczbaDzieci);

        createAtributtes(model, wybranaData);

        return "instruktor";
    }

    @GetMapping("/Zyrafa")
    public String zyrafa() {

        return "lokalizacje/Zyrafa";
    }

    private void createAtributtes(Model model, com.mkyong.utils.Date wybranaData) {

        if (wybranaData.getData() != null) {
            model.addAttribute("nurserySchoolList", nurserySchoolSummary.getListOfNurserySchoolByMonth(wybranaData.getData()));
            model.addAttribute("eventList", eventSummary.getListOfEventsByMonth(wybranaData.getData()));
            model.addAttribute("expenseList", monthExpense.getExpenseListByDate(wybranaData.getData()));
            model.addAttribute("cashCollectionList", cashSummary.getListOfCashCollectionByMonth(wybranaData.getData()));

        } else {

            model.addAttribute("nurserySchoolList", nurserySchoolSummary.getListOfNurserySchoolByMonth(getCurrentDate()));
            model.addAttribute("eventList", eventSummary.getListOfEventsByMonth(getCurrentDate()));
            model.addAttribute("expenseList", monthExpense.getExpenseListByDate(getCurrentDate()));
            model.addAttribute("cashCollectionList", cashSummary.getListOfCashCollectionByMonth(getCurrentDate()));

        }
        model.addAttribute("wybranaData", new com.mkyong.utils.Date());
        model.addAttribute("expense", new Expense());
        model.addAttribute("cashCollection", new CashCollection());
        model.addAttribute("nurserySchool", new NurserySchool());
        model.addAttribute("event", new Event());

        HashMap<String, Object> stringObjectHashMap = new HashMap<>();

        stringObjectHashMap.put("Jakis string", null);


    }


    public String getCurrentDate() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        return dateFormat.format(date);
    }
}
