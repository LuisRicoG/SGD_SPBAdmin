/**
 * 
 */
package com.company.sgdadmin.repository;

import com.company.sgdadmin.entity.LoginEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


/**
 * @author the_d
 *
 */
@Repository
public interface LoginRepository extends CrudRepository<LoginEntity,Integer>{
	
	LoginEntity findByUsuarioAndContrasena(String usuario,String contrasena);

}
