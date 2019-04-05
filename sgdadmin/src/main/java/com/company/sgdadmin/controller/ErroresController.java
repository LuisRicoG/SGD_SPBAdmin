package com.company.sgdadmin.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author jova_
 */

@Controller

public class ErroresController {
    
   
        @RequestMapping(value = "/500")
    public ModelAndView sayhello() {
        ModelAndView mv = new ModelAndView();
        //mv.addObject("message", "Hello Reader!");
        mv.setViewName("500");
        return mv;
    }
    
       @RequestMapping(value = "/404")
    public ModelAndView error404() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("404");
        return mv;
    }
    
       @RequestMapping(value = "/403")
    public ModelAndView error403() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("403");
        return mv;
    }
    
    
    
    
    
    }
