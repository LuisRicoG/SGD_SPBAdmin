/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.sgdadmin.controller;
import com.company.sdg.mvc.service.FacturasActivosService;
import com.company.sgdadmin.service.FileManager;
import org.springframework.beans.factory.annotation.Autowired;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
/**
 *
 * @author the_d
 */
@Controller
public class FacturasActivosController {    
    @Autowired
    FileManager fileManager;
    @Autowired
    HttpServletResponse response;
    
    FacturasActivosService service;

    @Autowired
    public FacturasActivosController(FacturasActivosService FAService) {
        this.service = FAService;
    }
    
    @RequestMapping(value = "/facturasactivos")
    public ModelAndView sayHello() {
    	ModelAndView mv = new ModelAndView();        
        mv.setViewName("facturasactivos");
        return mv;
    }
    
    @PostMapping("/getFA")
    @ResponseBody
    public void getDocument(@RequestParam("x") String x) {
        service.getFA(x);
    }
    
    @PostMapping("/getFAZip")
    @ResponseBody
    public void getZip(@RequestParam("x") String x) {
        service.getZip(x);
    }
}
