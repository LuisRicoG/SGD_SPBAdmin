/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.sgdadmin.controller;

import com.company.sgdadmin.beans.Cifras;
import com.company.sgdadmin.service.AvaluoActivosService;
import com.company.sgdadmin.service.FileManager;
import org.springframework.beans.factory.annotation.Autowired;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author the_d
 */
@Controller
public class AvaluoActivosController {

    @Autowired
    FileManager fileManager;
    @Autowired
    HttpServletResponse response;

    AvaluoActivosService service;
    
    public AvaluoActivosController(AvaluoActivosService AAService) {
        this.service = AAService;
    }

    @GetMapping(value = "/avaluoactivos")
    public String getCifras(Model model) {
        Cifras cifras = service.valoresContables();
        model.addAttribute("valorescontables", cifras);
        return "avaluoactivos";
    }

    @PostMapping("/getAA")
    @ResponseBody
    public void getDocument(@RequestParam("x") String x) {
        service.getAA(x);
    }

    @PostMapping("/getAAZip")
    @ResponseBody
    public void getZip(@RequestParam("x") String x) {
        service.getZip(x);
    }
}
