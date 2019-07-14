package com.company.sgdadmin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author jova_
 */
@Controller

public class ErroresController {

    @GetMapping("/500")
    public ModelAndView sayhello() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("500");
        return mv;
    }

    @GetMapping("/404")
    public ModelAndView error404() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("404");
        return mv;
    }

    @GetMapping("/403")
    public ModelAndView error403() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("403");
        return mv;
    }

    @GetMapping("/307")
    public ModelAndView error307() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("307");
        return mv;
    }
}
