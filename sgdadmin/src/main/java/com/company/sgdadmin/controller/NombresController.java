/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.sgdadmin.controller;

import com.company.sgdadmin.entity.NombresEntity;
import com.company.sgdadmin.repository.NombresRepository;
import com.company.sgdadmin.service.NombresService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author the_d
 */
@Controller
@RequestMapping("/editarnombres")
public class NombresController {
    @Autowired
    private NombresRepository nombresRepository;
    
    @Autowired
    private NombresService service;
    
    NombresController(NombresService nombresService){
        this.service=nombresService;
    }
    
    @GetMapping
    public ModelAndView listaNombres() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("editarnombres");
        return mv;
    }
    
    @PostMapping(value = "/registronombre", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity registro(@RequestBody NombresEntity nombre, Model model) {

        boolean respuesta = service.registro(nombre);

        if (!respuesta) {
            return new ResponseEntity("No se encontró registro con el ID: " + nombre.getNombreid(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(nombre, HttpStatus.OK);
    }
    
    @GetMapping("/listanombres")
    @ResponseBody
    public List<NombresEntity> listaJSON() {
        return (List<NombresEntity>) nombresRepository.findAll();
    }
    
    @PutMapping(value = "/updatenombre", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity updateNombre(@RequestBody NombresEntity nombre) {

        boolean respuesta = service.registro(nombre);
        if (!respuesta) {
            return new ResponseEntity("No se encuentra registro con ID: " + nombre.getNombreid(), HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity(nombre, HttpStatus.OK);
    }
    
    @PutMapping(value = "/deletenombre", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity deleteNombre(@RequestBody NombresEntity nombre) {

        service.borrar(nombre);

        return new ResponseEntity(nombre, HttpStatus.OK);
    }
}
