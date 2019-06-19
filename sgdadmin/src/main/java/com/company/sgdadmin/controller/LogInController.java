package com.company.sgdadmin.controller;

import com.company.sgdadmin.beans.Login;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/index")
public class LogInController {

    @GetMapping
    public ModelAndView getLogin() {
        ModelAndView mv = new ModelAndView();
        mv.addObject("user", new Login());
        mv.setViewName("index");
        return mv;
    }
}
