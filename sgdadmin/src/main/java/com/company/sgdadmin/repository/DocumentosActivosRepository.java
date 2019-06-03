/**
 *
 */
package com.company.sgdadmin.repository;

import com.company.sgdadmin.entity.DocumentosActivosEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author the_d
 *
 */
@Repository
public interface DocumentosActivosRepository extends CrudRepository<DocumentosActivosEntity, Integer> {
    DocumentosActivosEntity findByDocumentoid(Integer documentoid);
    DocumentosActivosEntity findByRutaAndNombre(String ruta, String nombre);
}
