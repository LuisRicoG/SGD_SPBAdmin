/*
 */
package com.company.sgdadmin.service;


import com.company.sgdadmin.entity.NombresEntity;
import java.util.List;
/**
 *
 * 
 */
public interface ContratosFirmadosService {
    public void getContratosFirmados(String documento);
    public void getDocumentosAnio(String documento,String year);
    public void getDocumentosMes(String documento,String year);
    public List<NombresEntity> getnombres(String vista);
}
