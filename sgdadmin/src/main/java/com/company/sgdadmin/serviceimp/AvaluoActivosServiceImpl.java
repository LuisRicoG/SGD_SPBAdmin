package com.company.sgdadmin.serviceimp;

import com.company.sdg.mvc.exceptions.AJAXException;
import com.company.sdg.mvc.exceptions.RedirectException;
import com.company.sgdadmin.beans.Cifras;
import com.company.sgdadmin.entity.CifrasEntity;
import com.company.sgdadmin.entity.DocumentosActivosEntity;
import com.company.sgdadmin.repository.CifrasRepository;
import com.company.sgdadmin.repository.DocumentosActivosRepository;
import com.company.sgdadmin.service.AvaluoActivosService;
import com.company.sgdadmin.service.FileManager;
import com.company.sgdadmin.util.ConstantsSGD;
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
 * @author the_d
 */
@Service
public class AvaluoActivosServiceImpl implements AvaluoActivosService {

    @Value("${directorio}")
    private String dirPrincipal;
    @Value("${avaluosactivosfolder}")
    private String avaluosactivos;
    @Value("${prefijoaval}")
    private String prefijo;
    @Value("${inmueblesfolder}")
    private String inmuebles;
    @Value("${equipocomputofolder}")
    private String equipocomputo;
    @Value("${equipotransportefolder}")
    private String equipotransporte;
    @Value("${maquinariayequipofolder}")
    private String maquinariayequipo;
    @Value("${mobiliariofolder}")
    private String mobiliario;
    @Value("${documentosunicos}")
    private String docunicos;
    @Autowired
    DocumentosActivosRepository repository;
    @Autowired
    private CifrasRepository cifrasRepository;
    @Autowired
    FileManager fileManager;

    @Override
    public void getAA(String x) {
        try {
            String path = File.separator + dirPrincipal + File.separator + docunicos + File.separator + avaluosactivos + File.separator;
            if (x.startsWith("I")) {
                path = path + inmuebles + File.separator;
            }
            if (x.startsWith("MyE")) {
                path = path + maquinariayequipo + File.separator;
            }
            if (x.startsWith("EdT")) {
                path = path + equipotransporte + File.separator;
            }
            if (x.startsWith("Mo")) {
                path = path + mobiliario + File.separator;
            }
            if (x.startsWith("EdC")) {
                path = path + equipocomputo + File.separator;
            }
            String fileName = prefijo + x + ".pdf";
            DocumentosActivosEntity activosEntity = repository.findByRutaAndNombre(path, fileName);
            if (activosEntity != null) {
                fileManager.downloadFile(activosEntity, true);
            } else {
                throw new AJAXException("Archivo no Encontrado en db");
            }
        } catch (IOException  ex) {
            Logger.getLogger(AvaluoActivosServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new AJAXException(ex.getMessage());
        }
    }

    @Override
    public void getZip(String x) {
        String HOME = ConstantsSGD.HOME;
        try {
            String path = File.separator + dirPrincipal + File.separator + docunicos + File.separator + avaluosactivos + File.separator + x + File.separator;
            String fileName = prefijo + x + ".zip";
            fileManager.zipFile(HOME + path, fileName);
            DocumentosActivosEntity entity = new DocumentosActivosEntity();
            entity.setRuta(path);
            entity.setNombre(fileName);
            fileManager.downloadFile(entity, false);
            
        } catch (IOException  ex) {
            Logger.getLogger(AvaluoActivosServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new RedirectException(ex.getMessage());
        }
    }

    @Override
    public Cifras valoresContables() {
        CifrasEntity cifras = cifrasRepository.findByCifraid(1);
        Cifras cifrasBean = null;
        if (cifras != null) {
            cifrasBean = new Cifras();
            cifrasBean.setCifraid(cifras.getCifraid());
            cifrasBean.setEqcomputo(cifras.getEqcomputo());
            cifrasBean.setEqtrans(cifras.getEqtrans());
            cifrasBean.setInmuebles(cifras.getInmuebles());
            cifrasBean.setMaqyeq(cifras.getMaqyeq());
            cifrasBean.setMobiliario(cifras.getMobiliario());
            cifrasBean.setTotal(cifras.getEqcomputo() + cifras.getEqtrans() + cifras.getInmuebles() + cifras.getMaqyeq() + cifras.getMobiliario());
        }
        return cifrasBean;
    }
}
