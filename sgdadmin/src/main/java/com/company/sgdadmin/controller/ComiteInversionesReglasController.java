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
public class ComiteInversionesReglasController {

    ContratosFirmadosService service;
    @Autowired
    FileManager fileManager;
    @Autowired
    HttpServletResponse response;

    @Autowired
    public ComiteInversionesReglasController(ContratosFirmadosService contratosService) {
        this.service = contratosService;
    }

    @GetMapping("/comiteinversionesreglas")
    public ModelAndView getComiteInversionesReglas() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("comiteInversionesReglas");
        return mv;
    }

    @PostMapping("/getdocumentocomiteinversionesreglas")
    public void getDocumentoComiteInversionesReglas(@RequestParam("year") String year, @RequestParam("documento") String documento) throws IOException {
        service.getDocumentosAnio(documento, year);
    }
}
