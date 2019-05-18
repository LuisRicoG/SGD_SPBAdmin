/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.sgdadmin.serviceimp;

import com.company.sgdadmin.entity.CalendarioEntity;
import com.company.sgdadmin.repository.CalendarioRepository;
import com.company.sgdadmin.service.EventosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author the_d
 */
@Service
public class EventosServiceImpl implements EventosService{
    @Autowired
    public CalendarioRepository calendarioRepository;
    
    @Override
    public boolean registro(CalendarioEntity cal){
        cal = calendarioRepository.save(cal);
        int calId = cal.getCalendarioid();
        if (calId > 0) {
            return true;
        }
        else {
            return false;
        }
    }
    
    @Override
    public void borrar(CalendarioEntity cal){
        calendarioRepository.deleteById(cal.getCalendarioid());
    }
}
