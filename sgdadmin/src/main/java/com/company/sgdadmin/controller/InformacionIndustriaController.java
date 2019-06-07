/**
 *
 * @author lricogom
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
import org.springframework.web.servlet.ModelAndView;

@Controller
public class InformacionIndustriaController {

    ContratosFirmadosService service;
    @Autowired
    FileManager fileManager;
    @Autowired
    HttpServletResponse response;

    @Autowired
    public InformacionIndustriaController(ContratosFirmadosService contratosService) {
        this.service = contratosService;
    }

    @GetMapping("/informacionindus")
    public ModelAndView getInformacionIndustria() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("informacionIndustria");
        return mv;
    }

    @PostMapping("/getdocumentoinformacion")
    public void getInformacionIndustria(@RequestParam("documento") String documento) throws IOException {
        service.getContratosFirmados(documento);
    }
}
