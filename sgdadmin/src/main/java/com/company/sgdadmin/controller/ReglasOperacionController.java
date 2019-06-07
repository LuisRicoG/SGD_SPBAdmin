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
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ReglasOperacionController {

    ContratosFirmadosService service;

    @Autowired
    public ReglasOperacionController(ContratosFirmadosService contratosService) {
        this.service = contratosService;
    }

    @GetMapping(value = "/reglasoperacion")
    public ModelAndView getReglasOperacion() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("reglasOperacion");
        return mv;
    }
    @Autowired
    FileManager fileManager;
    @Autowired
    HttpServletResponse response;

    @PostMapping("/getdocumentoreglas")
    public void getDocumentoReglas(@RequestParam("documento") String documento) throws IOException {
        service.getContratosFirmados(documento);
    }
}
