/**
 * 
 */
package com.company.sgdadmin.repository;

import com.company.sgdadmin.entity.ParametrosEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


/**
 * @author the_d
 *
 */
@Repository
public interface ParametrosRepository extends CrudRepository<ParametrosEntity,Integer>{

}
