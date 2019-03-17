/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.sgdadmin.service;

import com.company.sgdadmin.entity.LoginEntity;
import java.awt.print.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author JEPPLAP
 */
public interface ListaUsuariosService {

    public Page<LoginEntity> findPaginated(Pageable pageable);
    
}
