package com.company.sgd.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MenuController {
	
    @RequestMapping(value = "/menu")
    public ModelAndView sayHello() {
    	ModelAndView mv = new ModelAndView();
        //mv.addObject("message", "Hello Reader!");
        mv.setViewName("menu2");
        return mv;
    }
    @RequestMapping(value = "/menu", method = RequestMethod.POST)
    public ModelAndView menu(@ModelAttribute String vinculo) {
        System.out.println("ingreso :" + vinculo);
    	ModelAndView mv = new ModelAndView();
        mv.setViewName("menu2");
        return mv;
    }    
}

