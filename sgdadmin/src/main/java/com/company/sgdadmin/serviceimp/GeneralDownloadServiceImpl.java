/*
 * ing.jorge.eduardo.p@gmail.com
 */
package com.company.sgdadmin.serviceimp;

import com.company.sdg.mvc.exceptions.AJAXException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.company.sgdadmin.entity.DocumentosAcumuladosEntity;
import com.company.sgdadmin.repository.DocumentosAcumuladosRepository;
import com.company.sgdadmin.service.FileManager;
import com.company.sgdadmin.service.GeneralDownloadService;
import java.io.FileNotFoundException;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;

/**
 *
 * @author Admin
 */
@Service
public class GeneralDownloadServiceImpl implements GeneralDownloadService {

    @Autowired
    FileManager fileManager;
    @Autowired
    DocumentosAcumuladosRepository repository;
    @Value("${actasasamblea}")
    private String actasasamblea;
    @Value("${identificaciones}")
    private String identificaciones;
    @Value("${aumentoscapital}")
    private String aumentoscapital;
    @Value("${presentacionescorporativas}")
    private String presentacionescorporativas;
    @Value("${asambleaaccionistasfile}")
    private String asambleaaccionista;
    @Value("${documentosconsejoadministracion}")
    private String consejoadmdocumentos;
    @Value("${documentoscomitecomercial}")
    private String comitecomercialdocumentos;
    @Value("${documentoscomiteengorda}")
    private String comiteengordadocumentos;
    @Value("${documentoscomiteinversion}")
    private String comiteinversionesdocumentos;
    

    @Override
    public void getDocumento(int documentoId, int pantalla) {

        DocumentosAcumuladosEntity acumuladosEntity = repository.findBydocumentoIdAndPantalla(documentoId, pantalla);

        try {
            if (acumuladosEntity != null) {
                fileManager.downloadFile(acumuladosEntity, true);
            } else {
                throw new AJAXException("Archivo no Encontrado en db");
            }
        } catch (IOException  e) {
            Logger.getLogger(ContratosFirmadosServiceImpl.class.getName()).log(Level.SEVERE, null, e);
            throw new AJAXException(e.getMessage());
        }

    }

    @Override
    public List<DocumentosAcumuladosEntity> getDocumentoList(int pantalla) {
        List<DocumentosAcumuladosEntity> acumuladosEntity = repository.findByPantalla(pantalla);
        return acumuladosEntity;
    }

    @Override
    public String getTitulo(int pantalla) {
        String strReturn = null;
        switch (pantalla) {
            case 1:
                strReturn = actasasamblea;
                break;
            case 2:
                strReturn = identificaciones;
                break;
            case 3:
                strReturn = aumentoscapital;
                break;
            case 4:
                strReturn = presentacionescorporativas;
                break;
            case 5:
                strReturn = asambleaaccionista;
                break;
            case 6:
                strReturn = consejoadmdocumentos;
                break;
            case 7:
                strReturn = comitecomercialdocumentos;
                break;
            case 8:
                strReturn = comiteengordadocumentos;
                break;
            case 9:
                strReturn = comiteinversionesdocumentos;
                break;
        }
        return strReturn;
    }
}
