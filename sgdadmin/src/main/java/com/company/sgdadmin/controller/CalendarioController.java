/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.sgdadmin.controller;

import com.company.sgdadmin.beans.Calendario;
import com.company.sgdadmin.entity.CalendarioEntity;
import com.company.sgdadmin.repository.CalendarioRepository;
import com.company.sgdadmin.service.EventosService;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author the_d
 */
@Controller
@RequestMapping("/calendario")
public class CalendarioController {
    @Autowired
    private CalendarioRepository calRepository;
    
    @Autowired
    private EventosService service;
    
    CalendarioController(EventosService eventosService){
        this.service=eventosService;
    }
    
    @GetMapping
    public ModelAndView listaEventos() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("calendario");
        return mv;
    }
    
    @PostMapping(value = "/registroevento", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity registro(@RequestBody Calendario cal, Model model) {

        boolean respuesta = service.registro(cal);

        if (!respuesta) {
            return new ResponseEntity("No se encontró registro con el ID: " + cal.getId(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(cal, HttpStatus.OK);
    }
    
    @GetMapping("/listaeventos")
    @ResponseBody
    public List<Calendario> listaJSON() {
        List<CalendarioEntity> eventos = (List<CalendarioEntity>)calRepository.findAll();
        List<Calendario> bean = new ArrayList<>();
        Iterator<CalendarioEntity> eventit = eventos.iterator();
        while(eventit.hasNext()){
            CalendarioEntity ce= eventit.next();
            Calendario c=new Calendario();
            c.setId(ce.getCalendarioid());
            //c.setGroupId(ce.getGroupid());
            c.setAllDay(ce.getAllday());
            c.setStart(ce.getStart());
            c.setEnd(ce.getEnd());
            c.setTitle(ce.getTitle());
            c.setClassName(ce.getClassname());
            bean.add(c);
        }
        return bean;
    }
    
    @PutMapping(value = "/updateevento", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity updateEvento(@RequestBody Calendario cal) {

        boolean respuesta = service.registro(cal);
        if (!respuesta) {
            return new ResponseEntity("No se encuentra registro con ID: " + cal.getId(), HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity(cal, HttpStatus.OK);
    }
    
    @PutMapping(value = "/deleteevento", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity deleteEvento(@RequestBody Calendario cal) {

        service.borrar(cal.getId());

        return new ResponseEntity(cal, HttpStatus.OK);
    }
}
