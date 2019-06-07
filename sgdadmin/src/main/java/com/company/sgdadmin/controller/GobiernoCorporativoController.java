package com.company.sgdadmin.controller;


import com.company.sgdadmin.service.ContratosFirmadosService;
import com.company.sgdadmin.service.FileManager;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class GobiernoCorporativoController {

    ContratosFirmadosService service;

    @Autowired
    public GobiernoCorporativoController(ContratosFirmadosService contratosService) {
        this.service = contratosService;
    }

    @RequestMapping(value = "/gobiernocorporativo")
    public ModelAndView getGobiernoCorporativo() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("gobiernoCorporativo");
        return mv;
    }
    @Autowired
    FileManager fileManager;
    @Autowired
    HttpServletResponse response;

    @PostMapping("/getdocumentogobierno")
    public void getDocumentoGobierno(@RequestParam("documento") String documento) throws IOException {
        service.getContratosFirmados(documento);
    }
}
