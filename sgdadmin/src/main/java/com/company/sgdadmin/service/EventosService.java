/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.sgdadmin.service;

import com.company.sgdadmin.beans.Calendario;
import org.springframework.stereotype.Service;

/**
 *
 * @author the_d
 */
@Service
public interface EventosService {
    public boolean registro(Calendario cal);
    public void borrar(Integer id);
}
