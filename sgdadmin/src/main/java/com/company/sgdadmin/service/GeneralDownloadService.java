/*
 * ing.jorge.eduardo.p@gmail.com
 */
package com.company.sgdadmin.service;


import com.company.sgdadmin.entity.DocumentosAcumuladosEntity;
import java.util.List;

/**
 *
 * @author Admin
 */
public interface GeneralDownloadService {

    public void getDocumento(int id, int pantalla);

    public List<DocumentosAcumuladosEntity> getDocumentoList(int pantalla);

    public String getTitulo(int pantalla);
}
