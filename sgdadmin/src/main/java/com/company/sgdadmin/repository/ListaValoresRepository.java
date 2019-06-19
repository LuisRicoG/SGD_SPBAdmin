/**
 * 
 */
package com.company.sgdadmin.repository;

import com.company.sgdadmin.entity.ListaValoresEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


/**
 * @author the_d
 *
 */
@Repository
public interface ListaValoresRepository extends CrudRepository<ListaValoresEntity,Integer>{

}
