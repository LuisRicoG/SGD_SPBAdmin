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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class InformeActividadesController {

    ContratosFirmadosService service;
    @Autowired
    FileManager fileManager;
    @Autowired
    HttpServletResponse response;
    @Autowired
    LOVUtil lovu;

    @Autowired
    public InformeActividadesController(ContratosFirmadosService contratosService) {
        this.service = contratosService;
    }

    @GetMapping("/informeactividades")
    public ModelAndView getAsambleaAccionistas() {
        ModelAndView mv = new ModelAndView();
        mv.addObject("lista", lovu.getLov("anio"));
        mv.setViewName("informeActividades");
        return mv;
    }

    @PostMapping("/getdocumentoInforme")
    public void getDocumentosInformes(@RequestParam("year") String year, @RequestParam("documento") String documento) throws IOException {
        service.getDocumentosMes(documento, year);
    }

}
