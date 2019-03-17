/**
 * 
 */
package com.company.sgdadmin.repository;

import com.company.sgdadmin.entity.RolEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;



/**
 * @author the_d
 *
 */
@Repository
public interface RolRepository extends CrudRepository<RolEntity, Integer>{

}
