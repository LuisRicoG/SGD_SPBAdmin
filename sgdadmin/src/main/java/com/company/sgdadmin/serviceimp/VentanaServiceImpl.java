/*
 *  ing_farias@live.com
 */
package com.company.sgdadmin.serviceimp;

import com.company.sgdadmin.beans.Login;
import com.company.sgdadmin.dto.filemanager.FileManagerDTO;
import com.company.sgdadmin.entity.DocumentosActivosEntity;
import com.company.sgdadmin.exceptions.DownloadException;
import com.company.sgdadmin.repository.DocumentosActivosRepository;
import com.company.sgdadmin.service.FileManager;
import com.company.sgdadmin.service.VentanaServices;
import com.company.sgdadmin.util.ConstantsSGD;
import com.company.sgdadmin.util.CryptoFiles;
import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author jova_
 */
@Service
public class VentanaServiceImpl implements VentanaServices {

    @Value("${directorio}")
    private String dirPrincipal;
    @Value("${documentosunicos}")
    private String documentosunicos;
    @Value("${estadosfinacierosfolder}")
    private String estadosfinancieros;
    @Value("${doclegalfolder}")
    private String documentacionlegal;
    @Value("${asambleaordinariafolder}")
    private String asambleaordinaria;
    @Value("${avisoprivacidadfolder}")
    private String avisorpivacidad;
    @Value("${cumplimientooblifolder}")
    private String cumplimientoobligaciones;

    @Value("${escriturasfolder}")
    private String escrituras;

    @Value("${actaconstitutivafolder}")
    private String actaconstitutiva;
    @Value("${actaconstitutivafile}")
    private String actaconstitutivafile;

    @Value("${poderesfolder}")
    private String poderes;
    @Value("${poderesfile}")
    private String poderesfile;

    @Value("${reformaestatutosfolder}")
    private String reformaestatutos;
    @Value("${reformaestatutosfile}")
    private String reformaestatutosfile;

    @Value("${inscripcionrfc}")
    private String inscripcionrfc;

    @Value("${rfcfoder}")
    private String rfc;
    @Value("${rfcfile}")
    private String rfcfile;

    @Value("${fielfolder}")
    private String fiel;
    @Value("${fielfile}")
    private String fielfile;

    @Value("${sellodigitalfolder}")
    private String sellodigital;

    @Value("${sellodigitalfile}")
    private String sellodigitalfile;

    @Value("${avisoprivacidadfolder}")
    private String avisoprivacidadfolder;

    @Value("${avisoprivacidadfile}")
    private String avisoprivacidadfile;

    @Value("${cumplimientooblifolder}")
    private String cumplimientooblifolder;

    @Value("${cumplimientooblifile}")
    private String cumplimientooblifile;

    @Value("${comprobantedomicilio}")
    private String comprobantedomicilio;

    @Value("${comprobantedomiciliofile}")
    private String comprobantedomiciliofile;

    @Value("${asambleaordinariafolder}")
    private String asambleaordinariafolder;

    @Value("${asambleaordinariafile}")
    private String asambleaordinariafile;

    @Value("${estadosfinancieroscuatrimestre}")
    private String prefijoestadosfinancieros;

    @Value("${contratosfirmadosfolder}")
    private String contratosfirmadosfolder;

    @Value("${contratosfirmadosfinancierosfolder}")
    private String contratosfirmadosfinancierosfolder;

    @Value("${cffinancierosfile}")
    private String cffinancierosfile;

    @Value("${contratosfirmadosprovedoresfolder}")
    private String contratosfirmadosprovedoresfolder;

    @Value("${cfprovedoresfile}")
    private String cfprovedoresfile;

    @Value("${contratosfirmadosclientesfolder}")
    private String contratosfirmadosclientesfolder;

    @Value("${cfclientesfile}")
    private String cfclientesfile;

    @Value("${contratosfirmadospersonalfolder}")
    private String contratosfirmadospersonalfolder;

    @Value("${cfpersonalfile}")
    private String cfpersonalfile;

    @Value("${sagarpafolder}")
    private String sagarpafolder;

    @Value("${comprobantepagosfolder}")
    private String comprobantepagosfolder;

    @Value("${comprobantepagosfile}")
    private String comprobantepagosfile;

    @Value("${depositofolder}")
    private String depositofolder;

    @Value("${depositofile}")
    private String depositofile;

    @Value("${documentosolifolder}")
    private String documentosolifolder;

    @Value("${documentosolifile}")
    private String documentosolifile;

    @Value("${presentacionescorpfolder}")
    private String presentacionescorpfolder;

    @Value("${presentacionescorpfile}")
    private String presentacionescorpfile;

    @Value("${gobiernocorporativofolder}")
    private String gobiernocorporativofolder;

    @Value("${asambleaacciofolder}")
    private String asambleaacciofolder;

    @Value("${convocatoriafolder}")
    private String convocatoriafolder;

    @Value("${convocatoriafile}")
    private String convocatoriafile;

    @Value("${minutafolder}")
    private String minutafolder;

    @Value("${minutafile}")
    private String minutafile;

    @Value("${ordendiafolder}")
    private String ordendiafolder;

    @Value("${ordendiafile}")
    private String ordendiafile;

    @Value("${presentacioninformacionfolder}")
    private String presentacioninformacionfolder;

    @Value("${presentacioninformacionfile}")
    private String presentacioninformacionfile;

    @Value("${identificacionesfolder}")
    private String identificaciones;

    @Value("${identificacionesfile}")
    private String identificacionesfile;

    @Value("${prefijoreporteventas}")
    private String prefijoreporteventas;

    @Value("${reporteventasfolder}")
    private String reporteventasfolder;

    @Value("${protocoloacciofolder}")
    private String protocoloacciofolder;

    @Value("${comitesfolder}")
    private String comitesfolder;

    @Value("${consejofolder}")
    private String consejofolder;

    @Value("${informaactivifolder}")
    private String informaactivifolder;

    @Value("${plantrabajofolder}")
    private String plantrabajofolder;

    @Value("${reglasoperacionfolder}")
    private String reglasoperacionfolder;

    @Value("${comitesfile}")
    private String comitesfile;

    @Value("${consejofile}")
    private String consejofile;

    @Value("${informaactivifile}")
    private String informaactivifile;

    @Value("${plantrabajofile}")
    private String plantrabajofile;

    @Value("${protocoloacciofile}")
    private String protocoloacciofile;

    @Value("${reglasoperacionfile}")
    private String reglasoperacionfile;

    @Value("${facturasactivosfolder}")
    private String facturasactivos;

    @Value("${prefijofact}")
    private String prefijofact;

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

    @Value("${engorda}")
    private String engorda;

    @Value("${rastro}")
    private String rastro;

    @Value("${cortes}")
    private String cortes;

    @Value("${corporativo}")
    private String corporativo;

    @Value("${segurosfolder}")
    private String seguros;

    @Value("${prefijoseg}")
    private String prefijoseg;

    @Value("${avaluosactivosfolder}")
    private String avaluosactivos;

    @Value("${prefijoaval}")
    private String prefijoavaluo;

    @Value("${organigramafolder}")
    private String organigramafolder;

    @Value("${organigramafile}")
    private String organigramafile;

    @Value("${estudioseconofolder}")
    private String estudioseconofolder;

    @Value("${precioganadofolder}")
    private String precioganadofolder;

    @Value("${preciocarnecanfolder}")
    private String preciocarnecanfolder;

    @Value("${precioalimentofolder}")
    private String precioalimentofolder;

    @Value("${estudioseconofile}")
    private String estudioseconofile;

    @Value("${precioganadofile}")
    private String precioganadofile;

    @Value("${preciocarnecanfile}")
    private String preciocarnecanfile;

    @Value("${precioalimentofile}")
    private String precioalimentofile;

    @Value("${informacionIndusfolder}")
    private String informacionIndusfolder;

    @Autowired
    DocumentosActivosRepository repository;

    @Autowired
    FileManager fileManager;

    @Autowired
    CryptoFiles cryptoFiles;

    @Override
    public void getVentanas(MultipartFile file, String year, String month, String date, String direccion) {

        try {
            String HOME = ConstantsSGD.HOME;

            String path = File.separator + dirPrincipal + File.separator + documentosunicos + File.separator;
            String fileName = "";

            switch (direccion) {

                case "Acta Constitutiva":
                    path += documentacionlegal + File.separator + escrituras + File.separator + actaconstitutiva + File.separator;
                    fileName = actaconstitutivafile;
                    break;

                case "Poderes":
                    path += documentacionlegal + File.separator + escrituras + File.separator + poderes + File.separator;
                    fileName = poderesfile;
                    break;

                case "Reforma Estatutos":
                    path += documentacionlegal + File.separator + escrituras + File.separator + reformaestatutos + File.separator;
                    fileName = reformaestatutosfile;
                    break;

                case "RFC":
                    path += documentacionlegal + File.separator + rfc + File.separator;
                    fileName = rfcfile;
                    break;

                case "Identificaciones":

                    path += documentacionlegal + File.separator + identificaciones + File.separator;
                    fileName = identificacionesfile;
                    break;

                case "Fiel":
                    path += documentacionlegal + File.separator + fiel + File.separator;
                    fileName = fielfile;
                    break;

                case "Sello Digital":
                    path += documentacionlegal + File.separator + sellodigital + File.separator;
                    fileName = sellodigitalfile;
                    break;

                case "Aviso Privacidad":
                    path += documentacionlegal + File.separator + avisoprivacidadfolder + File.separator;
                    fileName = avisoprivacidadfile;
                    break;

                case "Cumplimiento de Obligaciones":
                    path += documentacionlegal + File.separator + cumplimientooblifolder + File.separator;
                    fileName = cumplimientooblifile;
                    break;

                case "Comprobante de Domicilio":
                    path += documentacionlegal + File.separator + comprobantedomicilio + File.separator;
                    fileName = comprobantedomiciliofile;
                    break;

                case "Asamblea Ordinaria Aumento de Capital":
                    path += documentacionlegal + File.separator + asambleaordinariafolder + File.separator;
                    fileName = asambleaordinariafile;
                    break;

                case "Estados Financieros":
                    path += estadosfinancieros + File.separator + year + File.separator;
                    fileName = prefijoestadosfinancieros + month + "-" + year + ".pdf";
                    break;

                case "Contratos Firmados Financieros":
                    path += contratosfirmadosfolder + File.separator + contratosfirmadosfinancierosfolder + File.separator;
                    fileName = cffinancierosfile;
                    break;

                case "Contratos Firmados Proveedores":
                    path += contratosfirmadosfolder + File.separator + contratosfirmadosprovedoresfolder + File.separator;
                    fileName = cfprovedoresfile;
                    break;

                case "Contratos Firmados Clientes":
                    path += contratosfirmadosfolder + File.separator + contratosfirmadosclientesfolder + File.separator;
                    fileName = cfclientesfile;
                    break;

                case "Contratos Firmados Personal":
                    path += contratosfirmadosfolder + File.separator + contratosfirmadospersonalfolder + File.separator;
                    fileName = cfpersonalfile;
                    break;

                case "Reporte de Ventas":
                    path += reporteventasfolder + File.separator + year + File.separator;
                    fileName = prefijoreporteventas + month + "-" + year + ".pdf";
                    break;

                case "Sagarpa Documentos de Solicitud":
                    path += sagarpafolder + File.separator + documentosolifolder + File.separator + year + File.separator;
                    fileName =  documentosolifile + year + ".pdf";
                    break;

                case "Sagarpa Deposito":
                    path += sagarpafolder + File.separator + depositofolder + File.separator + year + File.separator ;
                    fileName = depositofile + year + ".pdf";
                    break;

                case "Sagarpa Comprobante de Pagos":
                    path += sagarpafolder + File.separator + comprobantepagosfolder + File.separator + year + File.separator;
                    fileName = comprobantepagosfile + year + ".pdf";
                    break;

                case "Presentaciones Corporativas":
                    path += presentacionescorpfolder + File.separator + year + File.separator;
                    fileName = presentacionescorpfile + year + ".pdf";
                    break;

                case "Asamblea de Accionistas Convocatoria":
                    path += gobiernocorporativofolder + File.separator + asambleaacciofolder + File.separator + convocatoriafolder + File.separator;
                    fileName = convocatoriafile + year + ".pdf";
                    break;

                case "Asamblea de Accionistas Minuta":
                    path += gobiernocorporativofolder + File.separator + asambleaacciofolder + File.separator + minutafolder + File.separator;
                    fileName = minutafile + year + ".pdf";
                    break;

                case "Asamblea de Accionistas Orden del Dia":
                    path += gobiernocorporativofolder + File.separator + asambleaacciofolder + File.separator + ordendiafolder + File.separator;
                    fileName = ordendiafile + year + ".pdf";
                    break;

                case "Asamblea de Accionistas Presentacion de Informacion":
                    path += gobiernocorporativofolder + File.separator + asambleaacciofolder + File.separator + presentacioninformacionfolder + File.separator;
                    fileName = presentacioninformacionfile + year + ".pdf";
                    break;

                case "Gobierno Corporativo Consejo":
                    path += gobiernocorporativofolder + File.separator + asambleaacciofolder + File.separator + presentacioninformacionfolder + File.separator;
                    fileName = presentacioninformacionfile + year + ".pdf";
                    break;

                case "Gobierno Corporativo Comites":
                    path += gobiernocorporativofolder + File.separator + asambleaacciofolder + File.separator + presentacioninformacionfolder + File.separator;
                    fileName = presentacioninformacionfile + year + ".pdf";
                    break;

                case "Gobierno Corporativo Protocolo de Accionistas":
                    path += gobiernocorporativofolder + File.separator + asambleaacciofolder + File.separator + presentacioninformacionfolder + File.separator;
                    fileName = presentacioninformacionfile + year + ".pdf";
                    break;

                case "Gobierno Corporativo Reglas de Operacion":
                    path += gobiernocorporativofolder + File.separator + asambleaacciofolder + File.separator + presentacioninformacionfolder + File.separator;
                    fileName = presentacioninformacionfile + year + ".pdf";
                    break;

                case "Gobierno Corporativo Plan de Trabajo":
                    path += gobiernocorporativofolder + File.separator + asambleaacciofolder + File.separator + presentacioninformacionfolder + File.separator;
                    fileName = presentacioninformacionfile + year + ".pdf";
                    break;

                case "Gobierno Corporativo Informe de Actividades":
                    path += gobiernocorporativofolder + File.separator + asambleaacciofolder + File.separator + presentacioninformacionfolder + File.separator;
                    fileName = presentacioninformacionfile + year + ".pdf";
                    break;

                case "Activos Engorda Inmuebles":
                    path += facturasactivos + File.separator + inmuebles + File.separator;
                    fileName = prefijofact + "I" + engorda + ".pdf";
                    break;

                case "Activos Engorda Maquinaria":
                    path += facturasactivos + File.separator + maquinariayequipo + File.separator;
                    fileName = prefijofact + "MyE" + engorda + ".pdf";
                    break;

                case "Activos Engorda transporte":
                    path += facturasactivos + File.separator + equipotransporte + File.separator;
                    fileName = prefijofact + "EdT" + engorda + ".pdf";
                    break;

                case "Activos Engorda Mobiliario":
                    path += facturasactivos + File.separator + mobiliario + File.separator;
                    fileName = prefijofact + "Mo" + engorda + ".pdf";
                    break;

                case "Activos Engorda Computo":
                    path += facturasactivos + File.separator + equipocomputo + File.separator;
                    fileName = prefijofact + "EdC" + engorda + ".pdf";
                    break;

                case "Activos Rastro Inmuebles":
                    path += facturasactivos + File.separator + inmuebles + File.separator;
                    fileName = prefijofact + "I" + rastro + ".pdf";
                    break;

                case "Activos Rastro Maquinaria":
                    path += facturasactivos + File.separator + maquinariayequipo + File.separator;
                    fileName = prefijofact + "MyE" + rastro + ".pdf";
                    break;

                case "Activos Rastro transporte":
                    path += facturasactivos + File.separator + equipotransporte + File.separator;
                    fileName = prefijofact + "EdT" + rastro + ".pdf";
                    break;

                case "Activos Rastro Mobiliario":
                    path += facturasactivos + File.separator
                            + mobiliario + File.separator;
                    fileName = prefijofact + "Mo" + rastro + ".pdf";
                    break;

                case "Activos Rastro Computo":
                    path += facturasactivos + File.separator + equipocomputo + File.separator;
                    fileName = prefijofact + "EdC" + rastro + ".pdf";
                    break;

                case "Activos Cortes Inmuebles":
                    path += facturasactivos + File.separator + inmuebles + File.separator;
                    fileName = prefijofact + "I" + cortes + ".pdf";
                    break;

                case "Activos Cortes Maquinaria":
                    path += facturasactivos + File.separator + maquinariayequipo + File.separator;
                    fileName = prefijofact + "MyE" + cortes + ".pdf";
                    break;

                case "Activos Cortes transporte":
                    path += facturasactivos + File.separator + equipotransporte + File.separator;
                    fileName = prefijofact + "EdT" + cortes + ".pdf";
                    break;

                case "Activos Cortes Mobiliario":
                    path += facturasactivos + File.separator + mobiliario + File.separator;
                    fileName = prefijofact + "Mo" + cortes + ".pdf";
                    break;

                case "Activos Cortes Computo":
                    path += facturasactivos + File.separator + equipocomputo + File.separator;
                    fileName = prefijofact + "EdC" + cortes + ".pdf";
                    break;

                case "Activos Corporativo Inmuebles":
                    path += facturasactivos + File.separator + inmuebles + File.separator;
                    fileName = prefijofact + "I" + corporativo + ".pdf";
                    break;

                case "Activos Corporativo Maquinaria":
                    path += facturasactivos + File.separator + maquinariayequipo + File.separator;
                    fileName = prefijofact + "MyE" + corporativo + ".pdf";
                    break;

                case "Activos Corporativo transporte":
                    path += facturasactivos + File.separator + equipotransporte + File.separator;
                    fileName = prefijofact + "EdT" + corporativo + ".pdf";
                    break;

                case "Activos Corporativo Mobiliario":
                    path += facturasactivos + File.separator + mobiliario + File.separator;
                    fileName = prefijofact + "Mo" + corporativo + ".pdf";
                    break;

                case "Activos Corporativo Computo":
                    path += facturasactivos + File.separator + equipocomputo + File.separator;
                    fileName = prefijofact + "EdC" + corporativo + ".pdf";
                    break;

                case "Seguros Engorda Inmuebles":
                    path += seguros + File.separator + inmuebles + File.separator;
                    fileName = prefijoseg + "I" + engorda + ".pdf";
                    break;

                case "Seguros Engorda Maquinaria":
                    path += seguros + File.separator + maquinariayequipo + File.separator;
                    fileName = prefijoseg + "MyE" + engorda + ".pdf";
                    break;

                case "Seguros Engorda transporte":
                    path += seguros + File.separator + equipotransporte + File.separator;
                    fileName = prefijoseg + "EdT" + engorda + ".pdf";
                    break;

                case "Seguros Engorda Mobiliario":
                    path += seguros + File.separator + mobiliario + File.separator;
                    fileName = prefijoseg + "Mo" + engorda + ".pdf";
                    break;

                case "Seguros Engorda Computo":
                    path += seguros + File.separator + equipocomputo + File.separator;
                    fileName = prefijoseg + "EdC" + engorda + ".pdf";
                    break;

                case "Seguros Rastro Inmuebles":
                    path += seguros + File.separator + inmuebles + File.separator;
                    fileName = prefijoseg + "I" + rastro + ".pdf";
                    break;

                case "Seguros Rastro Maquinaria":
                    path += seguros + File.separator + maquinariayequipo + File.separator;
                    fileName = prefijoseg + "MyE" + rastro + ".pdf";
                    break;

                case "Seguros Rastro transporte":
                    path += seguros + File.separator
                            + equipotransporte + File.separator;
                    fileName = prefijoseg + "EdT" + rastro + ".pdf";
                    break;

                case "Seguros Rastro Mobiliario":
                    path += seguros + File.separator + mobiliario + File.separator;
                    fileName = prefijoseg + "Mo" + rastro + ".pdf";
                    break;

                case "Seguros Rastro Computo":
                    path += seguros + File.separator + equipocomputo + File.separator;
                    fileName = prefijoseg + "EdC" + rastro + ".pdf";
                    break;

                case "Seguros Cortes Inmuebles":
                    path += seguros + File.separator + inmuebles + File.separator;
                    fileName = prefijoseg + "I" + cortes + ".pdf";
                    break;

                case "Seguros Cortes Maquinaria":
                    path += seguros + File.separator + maquinariayequipo + File.separator;
                    fileName = prefijoseg + "MyE" + cortes + ".pdf";
                    break;

                case "Seguros Cortes transporte":
                    path += seguros + File.separator + equipotransporte + File.separator;
                    fileName = prefijoseg + "EdT" + cortes + ".pdf";
                    break;

                case "Seguros Cortes Mobiliario":
                    path += seguros + File.separator + mobiliario + File.separator;
                    fileName = prefijoseg + "Mo" + cortes + ".pdf";
                    break;

                case "Seguros Cortes Computo":
                    path += seguros + File.separator + equipocomputo + File.separator;
                    fileName = prefijoseg + "EdC" + cortes + ".pdf";
                    break;

                case "Seguros Corporativo Inmuebles":
                    path += seguros + File.separator + inmuebles + File.separator;
                    fileName = prefijoseg + "I" + corporativo + ".pdf";
                    break;

                case "Seguros Corporativo Maquinaria":
                    path += seguros + File.separator + maquinariayequipo + File.separator;
                    fileName = prefijoseg + "MyE" + corporativo + ".pdf";
                    break;

                case "Seguros Corporativo transporte":
                    path += seguros + File.separator + equipotransporte + File.separator;
                    fileName = prefijoseg + "EdT" + corporativo + ".pdf";
                    break;

                case "Seguros Corporativo Mobiliario":
                    path += seguros + File.separator + mobiliario + File.separator;
                    fileName = prefijoseg + "Mo" + corporativo + ".pdf";
                    break;

                case "Seguros Corporativo Computo":
                    path += seguros + File.separator + equipocomputo + File.separator;
                    fileName = prefijoseg + "EdC" + corporativo + ".pdf";
                    break;

                case "Avaluo Engorda Inmuebles":
                    path += avaluosactivos + File.separator + inmuebles + File.separator;
                    fileName = prefijoavaluo + "I" + engorda + ".pdf";
                    break;

                case "Avaluo Engorda Maquinaria":
                    path += avaluosactivos + File.separator + maquinariayequipo + File.separator;
                    fileName = prefijoavaluo + "MyE" + engorda + ".pdf";
                    break;

                case "Avaluo Engorda transporte":
                    path += avaluosactivos + File.separator + equipotransporte + File.separator;
                    fileName = prefijoavaluo + "EdT" + engorda + ".pdf";
                    break;

                case "Avaluo Engorda Mobiliario":
                    path += avaluosactivos + File.separator + mobiliario + File.separator;
                    fileName = prefijoavaluo + "Mo" + engorda + ".pdf";
                    break;

                case "Avaluo Engorda Computo":
                    path += avaluosactivos + File.separator + equipocomputo + File.separator;
                    fileName = prefijoavaluo + "EdC" + engorda + ".pdf";
                    break;

                case "Avaluo Rastro Inmuebles":
                    path += avaluosactivos + File.separator + inmuebles + File.separator;
                    fileName = prefijoavaluo + "I" + rastro + ".pdf";
                    break;

                case "Avaluo Rastro Maquinaria":
                    path += avaluosactivos + File.separator + maquinariayequipo + File.separator;
                    fileName = prefijoavaluo + "MyE" + rastro + ".pdf";
                    break;

                case "Avaluo Rastro transporte":
                    path += avaluosactivos + File.separator + equipotransporte + File.separator;
                    fileName = prefijoavaluo + "EdT" + rastro + ".pdf";
                    break;

                case "Avaluo Rastro Mobiliario":
                    path += avaluosactivos + File.separator + mobiliario + File.separator;
                    fileName = prefijoavaluo + "Mo" + rastro + ".pdf";
                    break;

                case "Avaluo Rastro Computo":
                    path += avaluosactivos + File.separator + equipocomputo + File.separator;
                    fileName = prefijoavaluo + "EdC" + rastro + ".pdf";
                    break;

                case "Avaluo Cortes Inmuebles":
                    path += avaluosactivos + File.separator + inmuebles + File.separator;
                    fileName = prefijoavaluo + "I" + cortes + ".pdf";
                    break;

                case "Avaluo Cortes Maquinaria":
                    path += avaluosactivos + File.separator + maquinariayequipo + File.separator;
                    fileName = prefijoavaluo + "MyE" + cortes + ".pdf";
                    break;

                case "Avaluo Cortes transporte":
                    path += avaluosactivos + File.separator + equipotransporte + File.separator;
                    fileName = prefijoavaluo + "EdT" + cortes + ".pdf";
                    break;

                case "Avaluo Cortes Mobiliario":
                    path += avaluosactivos + File.separator + mobiliario + File.separator;
                    fileName = prefijoavaluo + "Mo" + cortes + ".pdf";
                    break;

                case "Avaluo Cortes Computo":
                    path += avaluosactivos + File.separator + equipocomputo + File.separator;
                    fileName = prefijoavaluo + "EdC" + cortes + ".pdf";
                    break;

                case "Avaluo Corporativo Inmuebles":
                    path += avaluosactivos + File.separator + inmuebles + File.separator;
                    fileName = prefijoavaluo + "I" + corporativo + ".pdf";
                    break;

                case "Avaluo Corporativo Maquinaria":
                    path += avaluosactivos + File.separator + maquinariayequipo + File.separator;
                    fileName = prefijoavaluo + "MyE" + corporativo + ".pdf";
                    break;

                case "Avaluo Corporativo transporte":
                    path += avaluosactivos + File.separator + equipotransporte + File.separator;
                    fileName = prefijoavaluo + "EdT" + corporativo + ".pdf";
                    break;

                case "Avaluo Corporativo Mobiliario":
                    path += avaluosactivos + File.separator + mobiliario + File.separator;
                    fileName = prefijoavaluo + "Mo" + corporativo + ".pdf";
                    break;

                case "Avaluo Corporativo Computo":
                    path += avaluosactivos + File.separator + equipocomputo + File.separator;
                    fileName = prefijoavaluo + "EdC" + corporativo + ".pdf";
                    break;

                case "Organigrama":
                    path += organigramafolder + File.separator;
                    fileName = organigramafile + ".pdf";
                    break;

                case "Estudios Economicos":
                    path += informacionIndusfolder + File.separator + estudioseconofolder + File.separator;
                    fileName = estudioseconofile;
                    break;

                case "Precio Ganado en Pie":
                    path += informacionIndusfolder + File.separator + precioganadofolder + File.separator;
                    fileName = precioganadofile;
                    break;

                case "Precio Carne en Canal":
                    path += informacionIndusfolder + File.separator + preciocarnecanfolder + File.separator;
                    fileName = preciocarnecanfile;
                    break;

                case "Precio Alimento":
                    path += informacionIndusfolder + File.separator + precioalimentofolder + File.separator;
                    fileName = precioalimentofile;
                    break;
                default:
                    throw new DownloadException();

            }

            FileManagerDTO fileManagerDTO = new FileManagerDTO();
            fileManagerDTO.setFile(file);
            fileManagerDTO.setPath(HOME + path);
            fileManagerDTO.setName(fileName);

            File pathencript = new File(HOME + path + fileName);

            fileManager.uploading(fileManagerDTO);

            File fileEnc = cryptoFiles.processFileEncrypt(pathencript, false);

            if (fileEnc != null) {

                DocumentosActivosEntity doctoExiste = repository.findByRutaAndNombre(path, fileName);
                DocumentosActivosEntity entity = new DocumentosActivosEntity();

                if (doctoExiste != null) {
                    doctoExiste.setFecha(new Timestamp(System.currentTimeMillis()));
                    repository.save(doctoExiste);
                } else {
                    entity.setFecha(new Timestamp(System.currentTimeMillis()));
                    entity.setRuta(path);
                    entity.setNombre(fileName);
                    entity.setUsuario_id(1);
                    repository.save(entity);
                }
            } else {
                throw new DownloadException();
            }
        } catch (IOException ex) {
            Logger.getLogger(VentanaServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new DownloadException();
        }
    }

    private static EntityManagerFactory factory;

    public int GetUsuarioID(String usuario) {
        EntityManager em = factory.createEntityManager();
        Query q = em.createQuery("SELECT usuario_id FROM usuario where usuario='" + usuario + "'");
        Login user = (Login) q.getSingleResult();
        return user.getUsuarioid();
    }

    @Override
    public void visibleOptions(ModelAndView mv, String direccion) {
        switch (direccion) {
            case "Estados Financieros":
                mv.addObject("lista1", true);
                mv.addObject("lista2", true);
                mv.addObject("tipo","accept=.pdf");
                break;
            case "Reporte de Ventas":
                mv.addObject("lista1", true);
                mv.addObject("lista2", true);
                break;

            case "Sagarpa Documentos de Solicitud":
                mv.addObject("lista1", true);
                break;

            case "Sagarpa Deposito":
                mv.addObject("lista1", true);
                break;

            case "Sagarpa Comprobante de Pagos":
                mv.addObject("lista1", true);
                break;

            case "Presentaciones Corporativas":
                mv.addObject("lista1", true);
                break;

            case "Asamblea de Accionistas Convocatoria":
                mv.addObject("lista1", true);
                break;

            case "Asamblea de Accionistas Minuta":
                mv.addObject("lista1", true);
                break;

            case "Asamblea de Accionistas Orden del Dia":
                mv.addObject("lista1", true);
                break;

            case "Asamblea de Accionistas Presentacion de Informacion":
                mv.addObject("lista1", true);
                break;
        }
    }
    
    public void archivetipe(ModelAndView mv, String direccion) {
        switch (direccion) {
            case "Estados Financieros":
                 mv.addObject("lista1", true);
                mv.addObject("lista2", true);
                mv.addObject("tipo","accept=.pdf");
                break;
            case "Reporte de Ventas":
                mv.addObject("lista1", true);
                mv.addObject("lista2", true);
                
                break;

            case "Sagarpa Documentos de Solicitud":
                mv.addObject("lista1", true);
                break;

            case "Sagarpa Deposito":
                mv.addObject("lista1", true);
                break;

            case "Sagarpa Comprobante de Pagos":
                mv.addObject("lista1", true);
                break;

            case "Presentaciones Corporativas":
                mv.addObject("lista1", true);
                break;

            case "Asamblea de Accionistas Convocatoria":
                mv.addObject("lista1", true);
                break;

            case "Asamblea de Accionistas Minuta":
                mv.addObject("lista1", true);
                break;

            case "Asamblea de Accionistas Orden del Dia":
                mv.addObject("lista1", true);
                break;

            case "Asamblea de Accionistas Presentacion de Informacion":
                mv.addObject("lista1", true);
                break;
        }
    }

}
