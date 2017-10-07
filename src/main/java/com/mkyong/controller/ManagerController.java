package com.mkyong.controller;

import com.mkyong.sqlBase.JobSummaryCreator;
import com.mkyong.sqlBase.ManagerTaskCreator;
import com.mkyong.utils.Instructor;
import com.mkyong.utils.ManagerTask;
import com.mkyong.utils.Note;
import com.mkyong.utils.WorkSummary;
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
 * Created by Cyprian on 2017-09-25.
 */

@Controller
@RequestMapping("/manager")
public class ManagerController {

    @Autowired
    private JobSummaryCreator jobSummaryCreator;
    @Autowired
    private ManagerTaskCreator managerTaskCreator;

    @GetMapping("")
    public String getInstrucorWorkList(Model model) {

        model.addAttribute("instructorWorkSummary", jobSummaryCreator.getinstructorWorkSummaryByMonth(getCurrentDate()));
        model.addAttribute("instructorMap", jobSummaryCreator.getInstrucorsList(getCurrentDate()));
        model.addAttribute("instructor", new Instructor());
        model.addAttribute("task", new ManagerTask());
        model.addAttribute("wybranaData", new com.mkyong.utils.Date());
        model.addAttribute("notatka", new Note());
        model.addAttribute("taskList", managerTaskCreator.getTasksList());
        model.addAttribute("noteList", managerTaskCreator.getNoteList());
        return "manager";
    }


    @PostMapping("")
    public String insertWorkingHours(@ModelAttribute com.mkyong.utils.Date wybranaData, @ModelAttribute Instructor instructor,
                                     @ModelAttribute Note note, @ModelAttribute ManagerTask managerTask,
                                     @ModelAttribute WorkSummary workSummaryDate, Model model) {


        if (instructor.getName() != null) {
            jobSummaryCreator.insertWorkingHour(instructor);
        }
        if (note.getContent() != null) {
            managerTaskCreator.createNote(note);
        }if(managerTask.getTask() != null){
            managerTaskCreator.confirmTask(managerTask);
        }
        if (wybranaData.getDate() == null)
            wybranaData.setDate(getCurrentDate());

        model.addAttribute("wybranaData", new com.mkyong.utils.Date());
        model.addAttribute("instructorMap", jobSummaryCreator.getInstrucorsList(wybranaData.getDate()));
        model.addAttribute("instructorWorkSummary", jobSummaryCreator.getinstructorWorkSummaryByMonth(wybranaData.getDate()));
        model.addAttribute("instructor", new Instructor());
        model.addAttribute("task", new ManagerTask());
        model.addAttribute("notatka", new Note());
        model.addAttribute("taskList", managerTaskCreator.getTasksList());
        model.addAttribute("noteList", managerTaskCreator.getNoteList());
        return "manager";
    }

    private String getCurrentDate() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM");
        Date date = new Date();
        return dateFormat.format(date);
    }
}
