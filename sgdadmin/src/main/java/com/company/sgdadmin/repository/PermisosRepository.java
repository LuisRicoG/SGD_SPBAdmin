/**
 * 
 */
package com.company.sgdadmin.repository;

import com.company.sgdadmin.entity.PermisosEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


/**
 * @author the_d
 *
 */
@Repository
public interface PermisosRepository extends CrudRepository<PermisosEntity,Integer>{

}
