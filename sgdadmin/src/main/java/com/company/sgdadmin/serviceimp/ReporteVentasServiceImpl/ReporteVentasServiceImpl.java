/*
 * ing.jorge.eduardo.p@gmail.com
 */
package com.company.sgdadmin.serviceimp.ReporteVentasServiceImpl;

import com.company.sdg.mvc.exceptions.AJAXException;
import com.company.sdg.mvc.service.ReporteVentasService.ReporteVentasService;
import com.company.sgdadmin.entity.DocumentosActivosEntity;
import com.company.sgdadmin.repository.DocumentosActivosRepository;
import com.company.sgdadmin.service.FileManager;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 *
 * @author JEPPLAP
 */
@Service
public class ReporteVentasServiceImpl implements ReporteVentasService {

    @Autowired
    DocumentosActivosRepository repository;

    @Autowired
    FileManager fileManager;
    @Value("${directorio}")
    private String dirPrincipal;
    @Value("${reporteventasfolder}")
    private String ventas;
    @Value("${documentosunicos}")
    private String documentosunicos;
    @Value("${prefijoreporteventas}")
    private String prefijoreporteventas;

    @Override
    public void getReporteVentas(String year, String month) {
        try {
            Date date = new SimpleDateFormat("MMMM", new Locale("es", "ES")).parse(month);
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            String path = File.separator + dirPrincipal + File.separator + documentosunicos + File.separator + ventas + File.separator + year + File.separator;
            String fileName = prefijoreporteventas + month + "-" + year + ".pdf";
            DocumentosActivosEntity activosEntity = repository.findByRutaAndNombre(path, fileName);
            if (activosEntity != null) {
                fileManager.downloadFile(activosEntity, true);
            } else {
                throw new AJAXException("Archivo no Encontrado en db");
            }
        } catch (IOException | ParseException  ex) {
            Logger.getLogger(ReporteVentasServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new AJAXException(ex.getMessage());
        }
    }

}
