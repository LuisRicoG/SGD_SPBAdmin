package com.company.sgdadmin.controller;

import com.company.sdg.mvc.service.EdosFinancierosService.EdosFinancierosService;
import com.company.sgdadmin.util.LOVUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class EdosFinancierosController {

    EdosFinancierosService service;
    @Autowired
    LOVUtil lovu;

    @Autowired
    public EdosFinancierosController(EdosFinancierosService edosService) {
        this.service = edosService;
    }

    @GetMapping("/edosfinancieros")
    public ModelAndView getEdosFinancieros() {
        ModelAndView mv = new ModelAndView();
        mv.addObject("lista", lovu.getLov("anio"));
        mv.setViewName("estadosfinancieros");
        return mv;
    }

    @PostMapping("/getEstado")
    @ResponseBody
    public void getDocument(@RequestParam("year") String year, @RequestParam("month") String month) {
        service.getEstadoFinanciero(year, month);
    }
}
