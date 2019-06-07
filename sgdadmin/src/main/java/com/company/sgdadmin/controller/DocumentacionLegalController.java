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
public class DocumentacionLegalController {

    ContratosFirmadosService service;
    @Autowired
    FileManager fileManager;
    @Autowired
    HttpServletResponse response;

    @Autowired
    public DocumentacionLegalController(ContratosFirmadosService contratosService) {
        this.service = contratosService;
    }

    @GetMapping("/documentacionlegal")
    public ModelAndView sayHello() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("documentacionlegal");
        return mv;
    }

    //AQUI VA RFC DOWNLOAD
    @PostMapping("/getdocumentolegal")
    public void rfc(@RequestParam("documento") String documento) throws IOException {
        service.getContratosFirmados(documento);
    }
}
