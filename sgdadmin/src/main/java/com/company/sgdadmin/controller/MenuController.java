package com.company.sgdadmin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MenuController {
	
    @RequestMapping(value = "/menu")
    public ModelAndView sayHello() {
    	ModelAndView mv = new ModelAndView();
        mv.setViewName("menu");
        return mv;
    }
}

