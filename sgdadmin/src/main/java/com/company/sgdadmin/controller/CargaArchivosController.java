/*
 *  ing_farias@live.com
 */
package com.company.sgdadmin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CargaArchivosController {
	
    @RequestMapping(value = "/cargaarchivos")
    public ModelAndView sayHello() {
    	ModelAndView mv = new ModelAndView();
        //mv.addObject("message", "Hello Reader!");
        mv.setViewName("cargaarchivos");
        return mv;
    }
}

