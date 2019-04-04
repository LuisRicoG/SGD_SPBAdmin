/*
 *  ing_farias@live.com
 */
package com.company.sgdadmin.serviceimp;

import com.company.sgdadmin.beans.Login;
import com.company.sgdadmin.dto.filemanager.FileManagerDTO;
import com.company.sgdadmin.entity.DocumentosActivosEntity;
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

    @Value("${prefijoestadosfinancieros}")
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

            String path = "";

            switch (direccion) {

                case "Acta Constitutiva":
                    path = HOME + File.separator + dirPrincipal + File.separator + documentosunicos + File.separator + documentacionlegal
                            + File.separator + escrituras + File.separator + actaconstitutiva + File.separator + actaconstitutivafile;
                    break;

                case "Poderes":
                    path = HOME + File.separator + dirPrincipal + File.separator + documentosunicos + File.separator + documentacionlegal
                            + File.separator + escrituras + File.separator + poderes + File.separator + poderesfile;
                    break;

                case "Reforma Estatutos":
                    path = HOME + File.separator + dirPrincipal + File.separator + documentosunicos + File.separator + documentacionlegal
                            + File.separator + escrituras + File.separator + reformaestatutos + File.separator + reformaestatutosfile;
                    break;

                case "RFC":

                    path = HOME + File.separator + dirPrincipal + File.separator + documentosunicos + File.separator + documentacionlegal
                            + File.separator + rfc + File.separator + rfcfile;
                    break;

                case "Identificaciones":

                    path = HOME + File.separator + dirPrincipal + File.separator + documentosunicos + File.separator + documentacionlegal
                            + File.separator + identificaciones + File.separator + identificacionesfile;
                    break;

                case "Fiel":
                    path = HOME + File.separator + dirPrincipal + File.separator + documentosunicos + File.separator + documentacionlegal
                            + File.separator + fiel + File.separator + fielfile;
                    break;

                case "Sello Digital":
                    path = HOME + File.separator + dirPrincipal + File.separator + documentosunicos + File.separator + documentacionlegal
                            + File.separator + sellodigital + File.separator + sellodigitalfile;
                    break;

                case "Aviso Privacidad":
                    path = HOME + File.separator + dirPrincipal + File.separator + documentosunicos + File.separator + documentacionlegal
                            + File.separator + avisoprivacidadfolder + File.separator + avisoprivacidadfile;
                    break;

                case "Cumplimiento de Obligaciones":
                    path = HOME + File.separator + dirPrincipal + File.separator + documentosunicos + File.separator + documentacionlegal
                            + File.separator + cumplimientooblifolder + File.separator + cumplimientooblifile;
                    break;

                case "Comprobante de Domicilio":
                    path = HOME + File.separator + dirPrincipal + File.separator + documentosunicos + File.separator + documentacionlegal
                            + File.separator + comprobantedomicilio + File.separator + comprobantedomiciliofile;
                    break;

                case "Asamblea Ordinaria Aumento de Capital":
                    path = HOME + File.separator + dirPrincipal + File.separator + documentosunicos + File.separator + documentacionlegal
                            + File.separator + asambleaordinariafolder + File.separator + asambleaordinariafile;
                    break;

                case "Estados Financieros":
                    path = HOME + File.separator + dirPrincipal + File.separator + documentosunicos + File.separator + estadosfinancieros
                            + File.separator + year + File.separator + prefijoestadosfinancieros + month + "-" + year + ".pdf";
                    break;

                case "Contratos Firmados Financieros":
                    path = HOME + File.separator + dirPrincipal + File.separator + documentosunicos + File.separator + contratosfirmadosfolder
                            + File.separator + contratosfirmadosfinancierosfolder + File.separator + cffinancierosfile;
                    break;

                case "Contratos Firmados Proveedores":
                    path = HOME + File.separator + dirPrincipal + File.separator + documentosunicos + File.separator + contratosfirmadosfolder
                            + File.separator + contratosfirmadosprovedoresfolder + File.separator + cfprovedoresfile;
                    break;

                case "Contratos Firmados Clientes":
                    path = HOME + File.separator + dirPrincipal + File.separator + documentosunicos + File.separator + contratosfirmadosfolder
                            + File.separator + contratosfirmadosclientesfolder + File.separator + cfclientesfile;
                    break;

                case "Contratos Firmados Personal":
                    path = HOME + File.separator + dirPrincipal + File.separator + documentosunicos + File.separator + contratosfirmadosfolder
                            + File.separator + contratosfirmadospersonalfolder + File.separator + cfpersonalfile;
                    break;

                case "Reporte de Ventas":
                    path = HOME + File.separator + dirPrincipal + File.separator + documentosunicos + File.separator + reporteventasfolder
                            + File.separator + year + File.separator + prefijoreporteventas + month + "-" + year + ".pdf";
                    break;

                case "Sagarpa Documentos de Solicitud":
                    path = HOME + File.separator + dirPrincipal + File.separator + documentosunicos + File.separator + sagarpafolder
                            + File.separator + documentosolifolder + File.separator + year + File.separator + documentosolifile + year + ".pdf";
                    break;

                case "Sagarpa Deposito":
                    path = HOME + File.separator + dirPrincipal + File.separator + documentosunicos + File.separator + sagarpafolder
                            + File.separator + depositofolder + File.separator + year + File.separator + depositofile + year + ".pdf";
                    break;

                case "Sagarpa Comprobante de Pagos":
                    path = HOME + File.separator + dirPrincipal + File.separator + documentosunicos + File.separator + sagarpafolder
                            + File.separator + comprobantepagosfolder + File.separator + year + File.separator + comprobantepagosfile + year + ".pdf";
                    break;

                case "Presentaciones Corporativas":
                    path = HOME + File.separator + dirPrincipal + File.separator + documentosunicos + File.separator + presentacionescorpfolder
                            + File.separator + year + File.separator + presentacionescorpfile + year + ".pdf";
                    break;

                case "Asamblea de Accionistas Convocatoria":
                    path = HOME + File.separator + dirPrincipal + File.separator + documentosunicos + File.separator + gobiernocorporativofolder
                            + File.separator + asambleaacciofolder + File.separator + convocatoriafolder + File.separator + convocatoriafile + year + ".pdf";
                    break;

                case "Asamblea de Accionistas Minuta":
                    path = HOME + File.separator + dirPrincipal + File.separator + documentosunicos + File.separator + gobiernocorporativofolder
                            + File.separator + asambleaacciofolder + File.separator + minutafolder + File.separator + minutafile + year + ".pdf";
                    break;

                case "Asamblea de Accionistas Orden del Dia":
                    path = HOME + File.separator + dirPrincipal + File.separator + documentosunicos + File.separator + gobiernocorporativofolder
                            + File.separator + asambleaacciofolder + File.separator + ordendiafolder + File.separator + ordendiafile + year + ".pdf";
                    break;

                case "Asamblea de Accionistas Presentacion de Informacion":
                    path = HOME + File.separator + dirPrincipal + File.separator + documentosunicos + File.separator + gobiernocorporativofolder
                            + File.separator + asambleaacciofolder + File.separator + presentacioninformacionfolder + File.separator + presentacioninformacionfile + year + ".pdf";
                    break;

                case "Gobierno Corporativo Consejo":
                    path = HOME + File.separator + dirPrincipal + File.separator + documentosunicos + File.separator + gobiernocorporativofolder
                            + File.separator + asambleaacciofolder + File.separator + presentacioninformacionfolder + File.separator + presentacioninformacionfile + year + ".pdf";
                    break;

                case "Gobierno Corporativo Comites":
                    path = HOME + File.separator + dirPrincipal + File.separator + documentosunicos + File.separator + gobiernocorporativofolder
                            + File.separator + asambleaacciofolder + File.separator + presentacioninformacionfolder + File.separator + presentacioninformacionfile + year + ".pdf";
                    break;

                case "Gobierno Corporativo Protocolo de Accionistas":
                    path = HOME + File.separator + dirPrincipal + File.separator + documentosunicos + File.separator + gobiernocorporativofolder
                            + File.separator + asambleaacciofolder + File.separator + presentacioninformacionfolder + File.separator + presentacioninformacionfile + year + ".pdf";
                    break;

                case "Gobierno Corporativo Reglas de Operacion":
                    path = HOME + File.separator + dirPrincipal + File.separator + documentosunicos + File.separator + gobiernocorporativofolder
                            + File.separator + asambleaacciofolder + File.separator + presentacioninformacionfolder + File.separator + presentacioninformacionfile + year + ".pdf";
                    break;

                case "Gobierno Corporativo Plan de Trabajo":
                    path = HOME + File.separator + dirPrincipal + File.separator + documentosunicos + File.separator + gobiernocorporativofolder
                            + File.separator + asambleaacciofolder + File.separator + presentacioninformacionfolder + File.separator + presentacioninformacionfile + year + ".pdf";
                    break;

                case "Gobierno Corporativo Informe de Actividades":
                    path = HOME + File.separator + dirPrincipal + File.separator + documentosunicos + File.separator + gobiernocorporativofolder
                            + File.separator + asambleaacciofolder + File.separator + presentacioninformacionfolder + File.separator + presentacioninformacionfile + year + ".pdf";
                    break;

                case "Activos Engorda Inmuebles":
                    path = HOME + File.separator + dirPrincipal + File.separator + documentosunicos + File.separator + facturasactivos + File.separator
                            + inmuebles + File.separator + prefijofact + "I" + engorda + ".pdf";
                    break;

                case "Activos Engorda Maquinaria":
                    path = HOME + File.separator + dirPrincipal + File.separator + documentosunicos + File.separator + facturasactivos + File.separator
                            + maquinariayequipo + File.separator + prefijofact + "MyE" + engorda + ".pdf";
                    break;

                case "Activos Engorda transporte":
                    path = HOME + File.separator + dirPrincipal + File.separator + documentosunicos + File.separator + facturasactivos + File.separator
                            + equipotransporte + File.separator + prefijofact + "EdT" + engorda + ".pdf";
                    break;

                case "Activos Engorda Mobiliario":
                    path = HOME + File.separator + dirPrincipal + File.separator + documentosunicos + File.separator + facturasactivos + File.separator
                            + mobiliario + File.separator + prefijofact + "Mo" + engorda + ".pdf";
                    break;

                case "Activos Engorda Computo":
                    path = HOME + File.separator + dirPrincipal + File.separator + documentosunicos + File.separator + facturasactivos + File.separator
                            + equipocomputo + File.separator + prefijofact + "EdC" + engorda + ".pdf";
                    break;

                case "Activos Rastro Inmuebles":
                    path = HOME + File.separator + dirPrincipal + File.separator + documentosunicos + File.separator + facturasactivos + File.separator
                            + inmuebles + File.separator + prefijofact + "I" + rastro + ".pdf";
                    break;

                case "Activos Rastro Maquinaria":
                    path = HOME + File.separator + dirPrincipal + File.separator + documentosunicos + File.separator + facturasactivos + File.separator
                            + maquinariayequipo + File.separator + prefijofact + "MyE" + rastro + ".pdf";
                    break;

                case "Activos Rastro transporte":
                    path = HOME + File.separator + dirPrincipal + File.separator + documentosunicos + File.separator + facturasactivos + File.separator
                            + equipotransporte + File.separator + prefijofact + "EdT" + rastro + ".pdf";
                    break;

                case "Activos Rastro Mobiliario":
                    path = HOME + File.separator + dirPrincipal + File.separator + documentosunicos + File.separator + facturasactivos + File.separator
                            + mobiliario + File.separator + prefijofact + "Mo" + rastro + ".pdf";
                    break;

                case "Activos Rastro Computo":
                    path = HOME + File.separator + dirPrincipal + File.separator + documentosunicos + File.separator + facturasactivos + File.separator
                            + equipocomputo + File.separator + prefijofact + "EdC" + rastro + ".pdf";
                    break;

                case "Activos Cortes Inmuebles":
                    path = HOME + File.separator + dirPrincipal + File.separator + documentosunicos + File.separator + facturasactivos + File.separator
                            + inmuebles + File.separator + prefijofact + "I" + cortes + ".pdf";
                    break;

                case "Activos Cortes Maquinaria":
                    path = HOME + File.separator + dirPrincipal + File.separator + documentosunicos + File.separator + facturasactivos + File.separator
                            + maquinariayequipo + File.separator + prefijofact + "MyE" + cortes + ".pdf";
                    break;

                case "Activos Cortes transporte":
                    path = HOME + File.separator + dirPrincipal + File.separator + documentosunicos + File.separator + facturasactivos + File.separator
                            + equipotransporte + File.separator + prefijofact + "EdT" + cortes + ".pdf";
                    break;

                case "Activos Cortes Mobiliario":
                    path = HOME + File.separator + dirPrincipal + File.separator + documentosunicos + File.separator + facturasactivos + File.separator
                            + mobiliario + File.separator + prefijofact + "Mo" + cortes + ".pdf";
                    break;

                case "Activos Cortes Computo":
                    path = HOME + File.separator + dirPrincipal + File.separator + documentosunicos + File.separator + facturasactivos + File.separator
                            + equipocomputo + File.separator + prefijofact + "EdC" + cortes + ".pdf";
                    break;

                case "Activos Corporativo Inmuebles":
                    path = HOME + File.separator + dirPrincipal + File.separator + documentosunicos + File.separator + facturasactivos + File.separator
                            + inmuebles + File.separator + prefijofact + "I" + corporativo + ".pdf";
                    break;

                case "Activos Corporativo Maquinaria":
                    path = HOME + File.separator + dirPrincipal + File.separator + documentosunicos + File.separator + facturasactivos + File.separator
                            + maquinariayequipo + File.separator + prefijofact + "MyE" + corporativo + ".pdf";
                    break;

                case "Activos Corporativo transporte":
                    path = HOME + File.separator + dirPrincipal + File.separator + documentosunicos + File.separator + facturasactivos + File.separator
                            + equipotransporte + File.separator + prefijofact + "EdT" + corporativo + ".pdf";
                    break;

                case "Activos Corporativo Mobiliario":
                    path = HOME + File.separator + dirPrincipal + File.separator + documentosunicos + File.separator + facturasactivos + File.separator
                            + mobiliario + File.separator + prefijofact + "Mo" + corporativo + ".pdf";
                    break;

                case "Activos Corporativo Computo":
                    path = HOME + File.separator + dirPrincipal + File.separator + documentosunicos + File.separator + facturasactivos + File.separator
                            + equipocomputo + File.separator + prefijofact + "EdC" + corporativo + ".pdf";
                    break;

                case "Seguros Engorda Inmuebles":
                    path = HOME + File.separator + dirPrincipal + File.separator + documentosunicos + File.separator + seguros + File.separator
                            + inmuebles + File.separator + prefijoseg + "I" + engorda + ".pdf";
                    break;

                case "Seguros Engorda Maquinaria":
                    path = HOME + File.separator + dirPrincipal + File.separator + documentosunicos + File.separator + seguros + File.separator
                            + maquinariayequipo + File.separator + prefijoseg + "MyE" + engorda + ".pdf";
                    break;

                case "Seguros Engorda transporte":
                    path = HOME + File.separator + dirPrincipal + File.separator + documentosunicos + File.separator + seguros + File.separator
                            + equipotransporte + File.separator + prefijoseg + "EdT" + engorda + ".pdf";
                    break;

                case "Seguros Engorda Mobiliario":
                    path = HOME + File.separator + dirPrincipal + File.separator + documentosunicos + File.separator + seguros + File.separator
                            + mobiliario + File.separator + prefijoseg + "Mo" + engorda + ".pdf";
                    break;

                case "Seguros Engorda Computo":
                    path = HOME + File.separator + dirPrincipal + File.separator + documentosunicos + File.separator + seguros + File.separator
                            + equipocomputo + File.separator + prefijoseg + "EdC" + engorda + ".pdf";
                    break;

                case "Seguros Rastro Inmuebles":
                    path = HOME + File.separator + dirPrincipal + File.separator + documentosunicos + File.separator + seguros + File.separator
                            + inmuebles + File.separator + prefijoseg + "I" + rastro + ".pdf";
                    break;

                case "Seguros Rastro Maquinaria":
                    path = HOME + File.separator + dirPrincipal + File.separator + documentosunicos + File.separator + seguros + File.separator
                            + maquinariayequipo + File.separator + prefijoseg + "MyE" + rastro + ".pdf";
                    break;

                case "Seguros Rastro transporte":
                    path = HOME + File.separator + dirPrincipal + File.separator + documentosunicos + File.separator + seguros + File.separator
                            + equipotransporte + File.separator + prefijoseg + "EdT" + rastro + ".pdf";
                    break;

                case "Seguros Rastro Mobiliario":
                    path = HOME + File.separator + dirPrincipal + File.separator + documentosunicos + File.separator + seguros + File.separator
                            + mobiliario + File.separator + prefijoseg + "Mo" + rastro + ".pdf";
                    break;

                case "Seguros Rastro Computo":
                    path = HOME + File.separator + dirPrincipal + File.separator + documentosunicos + File.separator + seguros + File.separator
                            + equipocomputo + File.separator + prefijoseg + "EdC" + rastro + ".pdf";
                    break;

                case "Seguros Cortes Inmuebles":
                    path = HOME + File.separator + dirPrincipal + File.separator + documentosunicos + File.separator + seguros + File.separator
                            + inmuebles + File.separator + prefijoseg + "I" + cortes + ".pdf";
                    break;

                case "Seguros Cortes Maquinaria":
                    path = HOME + File.separator + dirPrincipal + File.separator + documentosunicos + File.separator + seguros + File.separator
                            + maquinariayequipo + File.separator + prefijoseg + "MyE" + cortes + ".pdf";
                    break;

                case "Seguros Cortes transporte":
                    path = HOME + File.separator + dirPrincipal + File.separator + documentosunicos + File.separator + seguros + File.separator
                            + equipotransporte + File.separator + prefijoseg + "EdT" + cortes + ".pdf";
                    break;

                case "Seguros Cortes Mobiliario":
                    path = HOME + File.separator + dirPrincipal + File.separator + documentosunicos + File.separator + seguros + File.separator
                            + mobiliario + File.separator + prefijoseg + "Mo" + cortes + ".pdf";
                    break;

                case "Seguros Cortes Computo":
                    path = HOME + File.separator + dirPrincipal + File.separator + documentosunicos + File.separator + seguros + File.separator
                            + equipocomputo + File.separator + prefijoseg + "EdC" + cortes + ".pdf";
                    break;

                case "Seguros Corporativo Inmuebles":
                    path = HOME + File.separator + dirPrincipal + File.separator + documentosunicos + File.separator + seguros + File.separator
                            + inmuebles + File.separator + prefijoseg + "I" + corporativo + ".pdf";
                    break;

                case "Seguros Corporativo Maquinaria":
                    path = HOME + File.separator + dirPrincipal + File.separator + documentosunicos + File.separator + seguros + File.separator
                            + maquinariayequipo + File.separator + prefijoseg + "MyE" + corporativo + ".pdf";
                    break;

                case "Seguros Corporativo transporte":
                    path = HOME + File.separator + dirPrincipal + File.separator + documentosunicos + File.separator + seguros + File.separator
                            + equipotransporte + File.separator + prefijoseg + "EdT" + corporativo + ".pdf";
                    break;

                case "Seguros Corporativo Mobiliario":
                    path = HOME + File.separator + dirPrincipal + File.separator + documentosunicos + File.separator + seguros + File.separator
                            + mobiliario + File.separator + prefijoseg + "Mo" + corporativo + ".pdf";
                    break;

                case "Seguros Corporativo Computo":
                    path = HOME + File.separator + dirPrincipal + File.separator + documentosunicos + File.separator + seguros + File.separator
                            + equipocomputo + File.separator + prefijoseg + "EdC" + corporativo + ".pdf";
                    break;

                case "Avaluo Engorda Inmuebles":
                    path = HOME + File.separator + dirPrincipal + File.separator + documentosunicos + File.separator + avaluosactivos + File.separator
                            + inmuebles + File.separator + prefijoavaluo + "I" + engorda + ".pdf";
                    break;

                case "Avaluo Engorda Maquinaria":
                    path = HOME + File.separator + dirPrincipal + File.separator + documentosunicos + File.separator + avaluosactivos + File.separator
                            + maquinariayequipo + File.separator + prefijoavaluo + "MyE" + engorda + ".pdf";
                    break;

                case "Avaluo Engorda transporte":
                    path = HOME + File.separator + dirPrincipal + File.separator + documentosunicos + File.separator + avaluosactivos + File.separator
                            + equipotransporte + File.separator + prefijoavaluo + "EdT" + engorda + ".pdf";
                    break;

                case "Avaluo Engorda Mobiliario":
                    path = HOME + File.separator + dirPrincipal + File.separator + documentosunicos + File.separator + avaluosactivos + File.separator
                            + mobiliario + File.separator + prefijoavaluo + "Mo" + engorda + ".pdf";
                    break;

                case "Avaluo Engorda Computo":
                    path = HOME + File.separator + dirPrincipal + File.separator + documentosunicos + File.separator + avaluosactivos + File.separator
                            + equipocomputo + File.separator + prefijoavaluo + "EdC" + engorda + ".pdf";
                    break;

                case "Avaluo Rastro Inmuebles":
                    path = HOME + File.separator + dirPrincipal + File.separator + documentosunicos + File.separator + avaluosactivos + File.separator
                            + inmuebles + File.separator + prefijoavaluo + "I" + rastro + ".pdf";
                    break;

                case "Avaluo Rastro Maquinaria":
                    path = HOME + File.separator + dirPrincipal + File.separator + documentosunicos + File.separator + avaluosactivos + File.separator
                            + maquinariayequipo + File.separator + prefijoavaluo + "MyE" + rastro + ".pdf";
                    break;

                case "Avaluo Rastro transporte":
                    path = HOME + File.separator + dirPrincipal + File.separator + documentosunicos + File.separator + avaluosactivos + File.separator
                            + equipotransporte + File.separator + prefijoavaluo + "EdT" + rastro + ".pdf";
                    break;

                case "Avaluo Rastro Mobiliario":
                    path = HOME + File.separator + dirPrincipal + File.separator + documentosunicos + File.separator + avaluosactivos + File.separator
                            + mobiliario + File.separator + prefijoavaluo + "Mo" + rastro + ".pdf";
                    break;

                case "Avaluo Rastro Computo":
                    path = HOME + File.separator + dirPrincipal + File.separator + documentosunicos + File.separator + avaluosactivos + File.separator
                            + equipocomputo + File.separator + prefijoavaluo + "EdC" + rastro + ".pdf";
                    break;

                case "Avaluo Cortes Inmuebles":
                    path = HOME + File.separator + dirPrincipal + File.separator + documentosunicos + File.separator + avaluosactivos + File.separator
                            + inmuebles + File.separator + prefijoavaluo + "I" + cortes + ".pdf";
                    break;

                case "Avaluo Cortes Maquinaria":
                    path = HOME + File.separator + dirPrincipal + File.separator + documentosunicos + File.separator + avaluosactivos + File.separator
                            + maquinariayequipo + File.separator + prefijoavaluo + "MyE" + cortes + ".pdf";
                    break;

                case "Avaluo Cortes transporte":
                    path = HOME + File.separator + dirPrincipal + File.separator + documentosunicos + File.separator + avaluosactivos + File.separator
                            + equipotransporte + File.separator + prefijoavaluo + "EdT" + cortes + ".pdf";
                    break;

                case "Avaluo Cortes Mobiliario":
                    path = HOME + File.separator + dirPrincipal + File.separator + documentosunicos + File.separator + avaluosactivos + File.separator
                            + mobiliario + File.separator + prefijoavaluo + "Mo" + cortes + ".pdf";
                    break;

                case "Avaluo Cortes Computo":
                    path = HOME + File.separator + dirPrincipal + File.separator + documentosunicos + File.separator + avaluosactivos + File.separator
                            + equipocomputo + File.separator + prefijoavaluo + "EdC" + cortes + ".pdf";
                    break;

                case "Avaluo Corporativo Inmuebles":
                    path = HOME + File.separator + dirPrincipal + File.separator + documentosunicos + File.separator + avaluosactivos + File.separator
                            + inmuebles + File.separator + prefijoavaluo + "I" + corporativo + ".pdf";
                    break;

                case "Avaluo Corporativo Maquinaria":
                    path = HOME + File.separator + dirPrincipal + File.separator + documentosunicos + File.separator + avaluosactivos + File.separator
                            + maquinariayequipo + File.separator + prefijoavaluo + "MyE" + corporativo + ".pdf";
                    break;

                case "Avaluo Corporativo transporte":
                    path = HOME + File.separator + dirPrincipal + File.separator + documentosunicos + File.separator + avaluosactivos + File.separator
                            + equipotransporte + File.separator + prefijoavaluo + "EdT" + corporativo + ".pdf";
                    break;

                case "Avaluo Corporativo Mobiliario":
                    path = HOME + File.separator + dirPrincipal + File.separator + documentosunicos + File.separator + avaluosactivos + File.separator
                            + mobiliario + File.separator + prefijoavaluo + "Mo" + corporativo + ".pdf";
                    break;

                case "Avaluo Corporativo Computo":
                    path = HOME + File.separator + dirPrincipal + File.separator + documentosunicos + File.separator + avaluosactivos + File.separator
                            + equipocomputo + File.separator + prefijoavaluo + "EdC" + corporativo + ".pdf";
                    break;

                case "Organigrama":
                    path = HOME + File.separator + dirPrincipal + File.separator + documentosunicos + File.separator + organigramafolder + File.separator
                            + organigramafile + ".pdf";
                    break;

                case "Estudios Economicos":
                    path = HOME + File.separator + dirPrincipal + File.separator + documentosunicos + File.separator + informacionIndusfolder + File.separator 
                            + estudioseconofolder + File.separator + estudioseconofile ;
                    break;

                case "Precio Ganado en Pie":
                    path = HOME + File.separator + dirPrincipal + File.separator + documentosunicos + File.separator + informacionIndusfolder + File.separator 
                            + precioganadofolder + File.separator + precioganadofile;
                    break;

                case "Precio Carne en Canal":
                    path = HOME + File.separator + dirPrincipal + File.separator + documentosunicos + File.separator + informacionIndusfolder + File.separator 
                            + preciocarnecanfolder + File.separator + preciocarnecanfile;
                    break;

                case "Precio Alimento":
                    path = HOME + File.separator + dirPrincipal + File.separator + documentosunicos + File.separator + informacionIndusfolder + File.separator 
                            + precioalimentofolder + File.separator + precioalimentofile;
                    break;

            }

            String message = "";
            FileManagerDTO fileManagerDTO = new FileManagerDTO();
            fileManagerDTO.setFile(file);
            fileManagerDTO.setName(path);

            int index = path.lastIndexOf("\\");
            String fileName = path.substring(index + 1);
            File ruta = new File(path);
            String route = ruta.getParent();

            File pathencript = new File(route + File.separator + fileName);

            fileManager.uploading(fileManagerDTO);

            File fileEnc = cryptoFiles.processFileEncrypt(pathencript, false);

            if (fileEnc != null) {

                DocumentosActivosEntity doctoExiste = repository.findByRutaAndNombre(route, fileName);
                DocumentosActivosEntity entity = new DocumentosActivosEntity();

                if (doctoExiste != null) {
                    entity.setDocumento_id(doctoExiste.getDocumento_id());
                    entity.setNombre(doctoExiste.getNombre());
                    entity.setRuta(doctoExiste.getRuta());
                    entity.setUsuario_id(1);
                    entity.setFecha(new Timestamp(System.currentTimeMillis()));
                    repository.save(entity);
                } else {
                    entity.setFecha(new Timestamp(System.currentTimeMillis()));
                    entity.setRuta(route);
                    entity.setNombre(fileName);
                    entity.setUsuario_id(1);
                    repository.save(entity);
                }

            } else {
                message = "Ocurrio un error";
            }

        } catch (IOException ex) {
            Logger.getLogger(VentanaServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
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

            case "Acta Constitutiva":

                break;

            case "Poderes":

                break;

            case "Reforma Estatutos":

                break;

            case "Identificaciones":

                break;

            case "RFC":

                break;

            case "Fiel":

                break;

            case "Sello Digital":

                break;

            case "Aviso Privacidad":

                break;

            case "Cumplimiento de Obligaciones":

                break;

            case "Comprobante de Domicilio":

                break;

            case "Asamblea Ordinaria Aumento de Capital":

                break;

            case "Estados Financieros":
                mv.addObject("lista1", true);
                mv.addObject("lista2", true);
                break;

            case "Contratos Firmados Financieros":

                break;

            case "Contratos Firmados Provedorores":
                break;

            case "Contratos Firmados Clientes":
                break;

            case "Contratos Firmados Personal":
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

            case "Gobierno Corporativo Consejo":

                break;

            case "Gobierno Corporativo Comites":

                break;

            case "Gobierno Corporativo Protocolo de Accionistas":

                break;

            case "Gobierno Corporativo Reglas de Operacion":

                break;

            case "Gobierno Corporativo Plan de Trabajo":

                break;

            case "Gobierno Corporativo Informe de Actividades":

                break;

            case "Activos Engorda Inmuebles":

                break;

            case "Activos Engorda Maquinaria":

                break;

            case "Activos Engorda transporte":

                break;

            case "Activos Engorda Mobiliario":

                break;

            case "Activos Engorda Computo":

                break;

        }
    }

}
