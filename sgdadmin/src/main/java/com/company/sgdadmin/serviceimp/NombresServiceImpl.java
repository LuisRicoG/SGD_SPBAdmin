/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.sgdadmin.serviceimp;

import com.company.sgdadmin.entity.NombresEntity;
import com.company.sgdadmin.repository.NombresRepository;
import com.company.sgdadmin.service.NombresService;
import java.util.Collections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 *
 * @author the_d
 */
@Service
public class NombresServiceImpl implements NombresService{
    @Autowired
    public NombresRepository nombresRepository;
    
    List<NombresEntity> entitys;
    
    @Override
    public boolean registro(NombresEntity nombre){
        nombre = nombresRepository.save(nombre);
        int nombreId = nombre.getNombreid();
        if (nombreId > 0) {
            return true;
        }
        else {
            return false;
        }
    }
    
    @Override
    public void borrar(NombresEntity nombre){
        nombresRepository.deleteById(nombre.getNombreid());
    }
    
    @Override
    public Page<NombresEntity> findPaginated(Pageable pageable) {
        entitys = (List<NombresEntity>) nombresRepository.findAll();
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<NombresEntity> list;

        if (entitys.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, entitys.size());
            list = entitys.subList(startItem, toIndex);
        }

        Page<NombresEntity> page
                = new PageImpl<>(list, PageRequest.of(currentPage, pageSize), entitys.size());

        return page;
    }
}
