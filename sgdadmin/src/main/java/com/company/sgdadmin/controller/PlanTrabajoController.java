package com.company.sgdadmin.controller;


import com.company.sdg.mvc.service.PlanTrabajoService;
import com.company.sgdadmin.beans.Calendario;
import com.company.sgdadmin.entity.CalendarioEntity;
import com.company.sgdadmin.repository.CalendarioRepository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author the_d
 */

@Controller
@RequestMapping("/plantrabajo")
public class PlanTrabajoController {
    @Autowired
    private CalendarioRepository calRepository;
    
    PlanTrabajoService service; 
    @Autowired PlanTrabajoController (PlanTrabajoService planTrabajoService){
        this.service = planTrabajoService;
    }
    
    @GetMapping
    public ModelAndView planTrabajo() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("plantrabajo");
        return mv;
    }
    
    @PostMapping("/descargaplan")
    @ResponseBody
    public void org() {
        service.planTrabajo();
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
            c.setGroupId(ce.getGroupid());
            c.setAllDay(ce.getAllday());
            c.setStart(ce.getStart());
            c.setEnd(ce.getEnd());
            c.setTitle(ce.getTitle());
            c.setClassName(ce.getClassname());
            bean.add(c);
        }
        return bean;
    }
}
