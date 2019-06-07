/*
 * ing.jorge.eduardo.p@gmail.com
 */
package com.company.sgdadmin.controller;

import com.company.sgdadmin.entity.DocumentosAcumuladosEntity;
import com.company.sgdadmin.repository.DocumentosAcumuladosRepository;
import com.company.sgdadmin.service.GeneralDownloadService;
import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Admin
 */
@Controller
public class GeneralDownloadController {

    @Autowired
    private DocumentosAcumuladosRepository acumuladosRepository;

    GeneralDownloadService service;

    @Autowired
    GeneralDownloadController(GeneralDownloadService service) {
        this.service = service;
    }

    @GetMapping("/gestor")
    @ResponseBody
    public ModelAndView newView(@RequestParam("pantalla") int pantalla) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("generaldownload");
        mv.addObject("pantalla", pantalla);
        mv.addObject("titulo",service.getTitulo(pantalla));
        return mv;
    }
    
    @GetMapping("/documentos")
    @ResponseBody
    public List<DocumentosAcumuladosEntity> listaJSON(@RequestParam("pantalla") int pantalla) {
        List<DocumentosAcumuladosEntity> entitys = service.getDocumentoList(pantalla);
        return entitys;
    }

    @PostMapping("/getdocumentogeneral")
    @ResponseBody
    public void escrituras(@RequestParam("id") int id, @RequestParam("pantalla") int pantalla) throws IOException {
        service.getDocumento(id, pantalla);
    }
}
