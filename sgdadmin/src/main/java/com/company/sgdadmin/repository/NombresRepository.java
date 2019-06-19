package com.company.sgdadmin.repository;

import com.company.sgdadmin.entity.NombresEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 *
 * @author the_d
 */

@Repository
public interface NombresRepository extends CrudRepository<NombresEntity, Integer>{
    List<NombresEntity> findByVista(String vista);
}
