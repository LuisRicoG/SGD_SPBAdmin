/**
 * 
 */
package com.company.sgdadmin.repository;

import com.company.sgdadmin.entity.DocumentosHistoricoEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


/**
 * @author the_d
 *
 */
@Repository
public interface DocumentosHistoricoRepository extends CrudRepository<DocumentosHistoricoEntity,Integer>{

}
