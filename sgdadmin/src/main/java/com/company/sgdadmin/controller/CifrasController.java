/*
    sayhello
 */
package com.company.sgdadmin.controller;

import com.company.sgdadmin.beans.Cifras;
import com.company.sgdadmin.entity.CifrasEntity;
import com.company.sgdadmin.repository.CifrasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author the_d
 */
@Controller
public class CifrasController {
    
    @Autowired
    private CifrasRepository cifrasRepository;
    
    @GetMapping(value = "/editarcifras")
    public String getCifras(Model model) {
    	CifrasEntity cifras=cifrasRepository.findByCifraid(1);
        model.addAttribute("registrocifra", cifras);
        return "editarcifras";
    }
    
    @PostMapping("/registrocifra")
    public String registro(@ModelAttribute("registrocifra") Cifras registrocifra, Model model) {
	CifrasEntity entity=new CifrasEntity();
	entity.setCifraid(1);
        entity.setEqcomputo(registrocifra.getEqcomputo());
	entity.setEqtrans(registrocifra.getEqtrans());
	entity.setInmuebles(registrocifra.getInmuebles());
        entity.setMaqyeq(registrocifra.getMaqyeq());
        entity.setMobiliario(registrocifra.getMobiliario());
        
        cifrasRepository.save(entity);
        
        return "redirect:editarcifras";
    }
}
