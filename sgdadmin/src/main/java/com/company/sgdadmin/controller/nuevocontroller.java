/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.sgdadmin.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
/**
 *
 * @author the_d
 */
@Controller
public class nuevocontroller {

    
    @GetMapping("/nuevocomitecomercial")
    public ModelAndView sayHello() {
    	ModelAndView mv = new ModelAndView();        
        mv.setViewName("nuevocomitecomercial");
        return mv;
    }

}
