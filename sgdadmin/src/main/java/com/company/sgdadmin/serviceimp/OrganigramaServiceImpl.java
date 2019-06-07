package com.company.sgdadmin.serviceimp;


import com.company.sdg.mvc.exceptions.AJAXException;
import com.company.sdg.mvc.service.OrganigramaService;
import com.company.sgdadmin.entity.DocumentosActivosEntity;
import com.company.sgdadmin.repository.DocumentosActivosRepository;
import com.company.sgdadmin.service.FileManager;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 *
 * @author Qualtop
 */
@Service
public class OrganigramaServiceImpl implements OrganigramaService {

    @Value("${directorio}")
    private String dirPrincipal;
    @Value("${documentosunicos}")
    private String docunicos;
    @Value("${organigramafolder}")
    private String organigrama;
    @Autowired
    DocumentosActivosRepository repository;
    @Autowired
    FileManager fileManager;

    @Override
    public void organigrama() {
        String path = File.separator + dirPrincipal + File.separator + docunicos + File.separator + organigrama + File.separator;
        String fileName = organigrama + ".pdf";
        try {
            DocumentosActivosEntity activosEntity = repository.findByRutaAndNombre(path, fileName);
            if (activosEntity != null) {
                fileManager.downloadFile(activosEntity, true);
            } else {
                throw new AJAXException("Archivo no Encontrado en db");
            }
        } catch (IOException  ex) {
            Logger.getLogger(OrganigramaServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new AJAXException(ex.getMessage());
        }
    }

}
