/**
 *
 */
package com.company.sdg.mvc.repository;

import com.company.sdg.mvc.entity.DocumentosAcumuladosEntity;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author the_d
 *
 */
@Repository
public interface DocumentosAcumuladosRepository extends CrudRepository<DocumentosAcumuladosEntity, Integer> {

    DocumentosAcumuladosEntity findBydocumentoIdAndPantalla(int documentoId, int pantalla);
    List<DocumentosAcumuladosEntity> findByPantalla(int pantalla);
}
