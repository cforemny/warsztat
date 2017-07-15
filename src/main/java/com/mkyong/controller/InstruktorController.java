package com.mkyong.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

/**
 * Created by Cyprian on 2017-07-15.
 */

@Controller
public class InstruktorController {

    @GetMapping("/instruktor")
    public String instruktor(@ModelAttribute String skosna, Model model) {
        String zawartosc = "jakas zawartosc z kontrolera";
        model.addAttribute("zawartosc", zawartosc);

        return "/instruktor";
    }

}
