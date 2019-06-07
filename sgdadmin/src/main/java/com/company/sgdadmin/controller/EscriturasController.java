/*
 *  ing_farias@live.com
 */
package com.company.sgdadmin.controller;

import com.company.sgdadmin.service.ContratosFirmadosService;
import com.company.sgdadmin.service.FileManager;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class EscriturasController {

    ContratosFirmadosService service;
    @Autowired
    FileManager fileManager;
    @Autowired
    HttpServletResponse response;

    @Autowired
    public EscriturasController(ContratosFirmadosService contratosService) {
        this.service = contratosService;
    }

    @GetMapping("/escrituras")
    public ModelAndView sayHello() {
        ModelAndView mv = new ModelAndView();
        //mv.addObject("message", "Hello Reader!");
        mv.setViewName("escrituras");
        return mv;
    }

    @PostMapping("/getdocumentoescrituras")
    @ResponseBody
    public void escrituras(@RequestParam("documento") String documento) throws IOException {
        service.getContratosFirmados(documento);
    }
}
