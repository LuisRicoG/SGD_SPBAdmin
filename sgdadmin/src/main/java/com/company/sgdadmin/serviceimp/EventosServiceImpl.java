/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.sgdadmin.serviceimp;

import com.company.sgdadmin.entity.CalendarioEntity;
import com.company.sgdadmin.beans.Calendario;
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
    public boolean registro(Calendario cal){
        CalendarioEntity calen = new CalendarioEntity();
        if (cal.getId()!=null)
            calen.setCalendarioid(cal.getId());
        calen.setGroupid(cal.getGroupId());
        calen.setAllday(cal.getAllDay());
        calen.setTitle(cal.getTitle());
        calen.setStart(cal.getStart());
        calen.setEnd(cal.getEnd());
        calen.setClassname(cal.getClassName());
        calen = calendarioRepository.save(calen);
        System.out.println("Evento: "+calen.getCalendarioid()+" "+calen.getTitle()+" "+calen.getStart()+" "+calen.getEnd()+" "+calen.getAllday()+" "+calen.getGroupid()+" "+calen.getClassname());
        int calId = calen.getCalendarioid();
        if (calId > 0) {
            return true;
        }
        else {
            return false;
        }
    }
    
    @Override
    public void borrar(Integer id){
        calendarioRepository.deleteById(id);
    }
}
