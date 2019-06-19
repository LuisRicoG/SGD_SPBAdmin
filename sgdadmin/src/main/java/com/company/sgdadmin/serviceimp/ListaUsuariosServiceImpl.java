/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.sgdadmin.serviceimp;

import com.company.sgdadmin.entity.LoginEntity;
import com.company.sgdadmin.repository.LoginRepository;
import com.company.sgdadmin.service.ListaUsuariosService;
import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 *
 * @author JEPPLAP
 */
@Service
public class ListaUsuariosServiceImpl implements ListaUsuariosService {

    @Autowired
    private LoginRepository loginRepository;

    List<LoginEntity> entitys;

    @Override
    public Page<LoginEntity> findPaginated(Pageable pageable) {
        entitys = (List<LoginEntity>) loginRepository.findAll();
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<LoginEntity> list;

        if (entitys.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, entitys.size());
            list = entitys.subList(startItem, toIndex);
        }

        Page<LoginEntity> page
                = new PageImpl<>(list, PageRequest.of(currentPage, pageSize), entitys.size());

        return page;
    }
}
