/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.sgdadmin.service;

import com.company.sgdadmin.entity.NombresEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 *
 * @author the_d
 */
@Service
public interface NombresService {
    public boolean registro(NombresEntity nombre);
    public void borrar(NombresEntity nombre);
    public Page<NombresEntity> findPaginated(Pageable pageable);
}
