/*
 *  ing_farias@live.com
 */
package com.company.sgd.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class EscriturasController {
	
    @RequestMapping(value = "/escrituras")
    public ModelAndView sayHello() {
    	ModelAndView mv = new ModelAndView();
        //mv.addObject("message", "Hello Reader!");
        mv.setViewName("escrituras");
        return mv;
    }
}

