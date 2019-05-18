/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.sgdadmin.repository;

import com.company.sgdadmin.entity.CalendarioEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author the_d
 */
@Repository
public interface CalendarioRepository extends CrudRepository<CalendarioEntity, Integer>{
    CalendarioEntity findByCalendarioid(Integer id);
}
