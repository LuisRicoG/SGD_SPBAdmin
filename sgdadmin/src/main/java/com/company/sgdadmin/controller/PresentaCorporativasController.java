package com.company.sgdadmin.controller;

import com.company.sgdadmin.service.ContratosFirmadosService;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PresentaCorporativasController {

    ContratosFirmadosService service;
    
      @GetMapping("/presentacionesCorporativas")
    public ModelAndView sayHello() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("presentacionesCorporativas");
        return mv;
    }

    @Autowired
    public PresentaCorporativasController(ContratosFirmadosService contratosService) {
        this.service = contratosService;
    }

    @PostMapping("/getdocumentopresentaciones")
   public void rfc(@RequestParam("documento") String documento) throws IOException {
        service.getContratosFirmados(documento);
    }
}
