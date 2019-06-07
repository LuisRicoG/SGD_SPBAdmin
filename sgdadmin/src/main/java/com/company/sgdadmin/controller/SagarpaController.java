/**
 *
 * @author lricogom
 */
package com.company.sgdadmin.controller;


import com.company.sgdadmin.service.ContratosFirmadosService;
import com.company.sgdadmin.service.FileManager;
import com.company.sgdadmin.util.LOVUtil;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SagarpaController {

    ContratosFirmadosService service;
    @Autowired
    FileManager fileManager;
    @Autowired
    HttpServletResponse response;
    @Autowired
    LOVUtil lovu;

    @Autowired
    public SagarpaController(ContratosFirmadosService contratosService) {
        this.service = contratosService;
    }

    @RequestMapping(value = "/sagarpa")
    public ModelAndView getSagarpa() {
        ModelAndView mv = new ModelAndView();
        mv.addObject("lista", lovu.getLov("anio"));
        mv.setViewName("sagarpa");
        return mv;
    }

    @PostMapping("/getdocumentoSagarpa")
    public void getDocumentoSagarpa(@RequestParam("year") String year, @RequestParam("documento") String documento) throws IOException {
        service.getDocumentosAnio(documento, year);
    }

}
