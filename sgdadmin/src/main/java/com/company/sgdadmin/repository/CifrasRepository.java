package com.company.sgdadmin.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.company.sgdadmin.entity.CifrasEntity;
/**
 *
 * @author the_d
 */

@Repository
public interface CifrasRepository extends CrudRepository<CifrasEntity, Integer>{
    CifrasEntity findByCifraid(Integer id);
}
