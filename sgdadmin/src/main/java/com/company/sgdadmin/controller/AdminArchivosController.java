/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.sgdadmin.controller;

import com.company.sgdadmin.entity.DocumentosActivosEntity;
import com.company.sgdadmin.repository.DocumentosActivosRepository;
import com.company.sgdadmin.service.FileManager;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Allan Flores Rojas
 */
@Controller
public class AdminArchivosController {
    
    @Autowired
    private DocumentosActivosRepository doctosRepository;
    
    @Autowired
    FileManager fileManager;
    
    @GetMapping("/listaarchivos")
    public String getUsuarios2(Model model) {
        
        List<DocumentosActivosEntity> doctos=(List<DocumentosActivosEntity>)doctosRepository.findAll();
        model.addAttribute("listaarchivos", doctos);
        
        return "listaarchivos";
    }
    
    @RequestMapping(value = "/getDocto", method = RequestMethod.GET)
    public String getDocument(@RequestParam("name") String name, @RequestParam("path") String path) {
        DocumentosActivosEntity activosEntity = doctosRepository.findByRutaAndNombre(path, name);
        try {
            if (activosEntity != null) {
                fileManager.downloadFile(activosEntity);
            } else {
                //return "archivo no encontrado"
            }
        } catch (IOException ex) {
            Logger.getLogger(AdminArchivosController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "redirect:listaarchivos";
    }
}
