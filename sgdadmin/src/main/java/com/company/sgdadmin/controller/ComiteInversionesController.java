package com.company.sgdadmin.controller;


import com.company.sgdadmin.entity.NombresEntity;
import com.company.sgdadmin.service.ContratosFirmadosService;
import com.company.sgdadmin.service.FileManager;
import org.springframework.ui.Model;
import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ComiteInversionesController {

    ContratosFirmadosService service;
    @Autowired
    FileManager fileManager;
    @Autowired
    HttpServletResponse response;

    @Autowired
    public ComiteInversionesController(ContratosFirmadosService contratosService) {
        this.service = contratosService;
    }

    @GetMapping("/comiteinversiones")
    public ModelAndView getComiteInversiones(Model model) {
        List<NombresEntity> nombres = service.getnombres("CI"); 
        ModelAndView mv = new ModelAndView();
        mv.setViewName("comiteInversiones");
        model.addAttribute("listanombres", nombres);
        return mv;
    }

    @PostMapping("/getdocumentocomiteinversiones")
    public void getDocumentoComiteInversiones(@RequestParam("year") String year, @RequestParam("documento") String documento) throws IOException {
        service.getDocumentosAnio(documento, year);
    }
}
