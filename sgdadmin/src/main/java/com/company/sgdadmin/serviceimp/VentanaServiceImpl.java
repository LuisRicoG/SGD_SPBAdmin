/*
 *  ing_farias@live.com
 */
package com.company.sgdadmin.serviceimp;

import com.company.sgdadmin.beans.Login;
import com.company.sgdadmin.dto.filemanager.FileManagerDTO;
import com.company.sgdadmin.entity.DocumentosActivosEntity;
import com.company.sgdadmin.entity.DocumentosAcumuladosEntity;
import com.company.sgdadmin.exceptions.DownloadException;
import com.company.sgdadmin.repository.DocumentosActivosRepository;
import com.company.sgdadmin.repository.DocumentosAcumuladosRepository;
import com.company.sgdadmin.service.FileManager;
import com.company.sgdadmin.service.VentanaServices;
import com.company.sgdadmin.util.ConstantsSGD;
import com.company.sgdadmin.util.CryptoFiles;
import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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

    @Value("${enlacescorporativosfolder}")
    private String dirEnlacesCorporativos;
    @Value("${enlacescorporativosfile}")
    private String fileEnlacesCorporativos;

    @Value("${convocatoriainversionesfolder}")
    private String dirConvocatoriaInversiones;
    @Value("${convocatoriainversionesfile}")
    private String fileConvocatoriaInversiones;

    @Value("${comitesfolder}")
    private String dirComites;

    @Value("${minutainversionesfolder}")
    private String dirMinutaInversiones;
    @Value("${minutainversionesfile}")
    private String fileMinutaInversiones;
    @Value("${ordendiainversionesfolder}")
    private String dirOrdenDiaInversiones;
    @Value("${ordendiainversionesfile}")
    private String fileOrdenDiaInversiones;
    @Value("${presentacioninformacioninversionesfolder}")
    private String dirPresentacionInformacionInversiones;
    @Value("${presentacioninformacioninversionesfile}")
    private String filePresentacionInformacionInversiones;
    @Value("${reglasoperacioninversionesfolder}")
    private String dirReglasOperacionesInversiones;
    @Value("${reglasoperacioninversionesfile}")
    private String fileReglasOperacionesInversiones;
    @Value("${curriculummiembrosinversionesfolder}")
    private String dirCurriculumMiembrosInversiones;
    @Value("${curriculummiembrosinversionesfile}")
    private String fileCurriculumMiembrosInversiones;
    @Value("${cartasconfidinversionesfolder}")
    private String dirCartasConfidencialidadInversiones;
    @Value("${cartasconfidinversionesfile}")
    private String fileCartasConfidencialidadInversiones;
    @Value("${plananualsesinversionesfolder}")
    private String dirPlanAnualSesionesInversiones;
    @Value("${plananualsesinversionesfile}")
    private String filePlanAnualSesionesInversiones;
    @Value("${reportetriconsejoinversionesfolder}")
    private String dirReporteTrimestralInversiones;
    @Value("${reportetriconsejoinversionesfile}")
    private String fileReporteTrimestralInversiones;
    @Value("${convocatoriacomercialfolder}")
    private String dirConvocatoriaComercial;
    @Value("${convocatoriacomercialfile}")
    private String fileConvocatoriaComercial;
    @Value("${minutacomercialfolder}")
    private String dirMinutaComercial;
    @Value("${minutacomercialfile}")
    private String fileMinutaComercial;
    @Value("${ordendiacomercialfolder}")
    private String dirOrdenDiaComercial;
    @Value("${ordendiacomercialfile}")
    private String fileOrdenDiaComercial;
    @Value("${presentacioninformacioncomercialfolder}")
    private String dirPresentacionInformacionComercial;
    @Value("${presentacioninformacioncomercialfile}")
    private String filePresentacionInformacionComercial;
    @Value("${reglasoperacioncomercialfolder}")
    private String dirReglasOperacionesComercial;
    @Value("${reglasoperacioncomercialfile}")
    private String fileReglasOperacionesComercial;
    @Value("${curriculummiembroscomercialfolder}")
    private String dirCurriculumMiembrosComercial;
    @Value("${curriculummiembroscomercialfile}")
    private String fileCurriculumMiembrosComercial;
    @Value("${cartasconfidcomercialfolder}")
    private String dirCartasConfidencialidadComercial;
    @Value("${cartasconfidcomercialfile}")
    private String fileCartasConfidencialidadComercial;
    @Value("${plananualsescomercialfolder}")
    private String dirPlanAnualSesionesComercial;
    @Value("${plananualsescomercialfile}")
    private String filePlanAnualSesionesComercial;
    @Value("${reportetriconsejocomercialfolder}")
    private String dirReporteTrimestralComercial;
    @Value("${reportetriconsejocomercialfile}")
    private String fileReporteTrimestralComercial;

    @Value("${comiteengordafolder}")
    private String dirComiteEngorda;
    @Value("${convocatoriaengordafolder}")
    private String dirConvocatoriaEngorda;
    @Value("${convocatoriaengordafile}")
    private String fileConvocatoriaEngorda;
    @Value("${minutaengordafolder}")
    private String dirMinutaEngorda;
    @Value("${minutaengordafile}")
    private String fileMinutaEngorda;
    @Value("${ordendiaengordafolder}")
    private String dirOrdenDiaEngorda;
    @Value("${ordendiaengordafile}")
    private String fileOrdenDiaEngorda;
    @Value("${presentacioninformacionengordafolder}")
    private String dirPresentacionInformacionEngorda;
    @Value("${presentacioninformacionengordafile}")
    private String filePresentacionInformacionEngorda;
    @Value("${reglasoperacionengordafolder}")
    private String dirReglasOperacionesEngorda;
    @Value("${reglasoperacionengordafile}")
    private String fileReglasOperacionesEngorda;
    @Value("${curriculummiembrosengordafolder}")
    private String dirCurriculumMiembrosEngorda;
    @Value("${curriculummiembrosengordafile}")
    private String fileCurriculumMiembrosEngorda;
    @Value("${cartasconfidengordafolder}")
    private String dirCartasConfidencialidadEngorda;
    @Value("${cartasconfidengordafile}")
    private String fileCartasConfidencialidadEngorda;
    @Value("${plananualsesengordafolder}")
    private String dirPlanAnualSesionesEngorda;
    @Value("${plananualsesengordafile}")
    private String filePlanAnualSesionesEngorda;
    @Value("${reportetriconsejofolder}")
    private String dirReporteTrimestralEngorda;
    @Value("${reportetriconsejofile}")
    private String fileReporteTrimestralEngorda;
    @Value("${documentosacumulados}")
    private String documentosacumulados;

    @Value("${proyectoejecutivofolder}")
    private String proyectoejecutivofolder;
    @Value("${terrenofolder}")
    private String terrenofolder;
    @Value("${proyectofolder}")
    private String proyectofolder;
    @Value("${estudiosfolder}")
    private String estudiosfolder;
    @Value("${planosfolder}")
    private String planosfolder;
    @Value("${estructuraterrenofile}")
    private String estructuraterrenofile;
    @Value("${avaluoterrenofile}")
    private String avaluoterrenofile;
    @Value("${proyectoentregadofile}")
    private String proyectoentregadofile;
    @Value("${programanecesidadesfile}")
    private String programanecesidadesfile;
    @Value("${programaparticularfile}")
    private String programaparticularfile;
    @Value("${justificacionproyectofile}")
    private String justificacionproyectofile;
    @Value("${impactomecanicadesuelosfile}")
    private String impactomecanicadesuelosfile;
    @Value("${impactoambientalfile}")
    private String impactoambientalfile;
    @Value("${impactovialfile}")
    private String impactovialfile;
    @Value("${energiaelectricafile}")
    private String energiaelectricafile;
    @Value("${estudiohidrologicofile}")
    private String estudiohidrologicofile;
    @Value("${usodesuelofile}")
    private String usodesuelofile;
    @Value("${concesionaguasfile}")
    private String concesionaguasfile;
    @Value("${planoregionalfile}")
    private String planoregionalfile;
    @Value("${planoruralfile}")
    private String planoruralfile;
    @Value("${planotopograficofile}")
    private String planotopograficofile;
    @Value("${ubicacionterrenofile}")
    private String ubicacionterrenofile;
    @Value("${planosinfraestructurafile}")
    private String planosinfraestructurafile;

    @Value("${dirreportesmensualess}")
    private String dirreportesmensualess;

    @Value("${direscritosenviados}")
    private String direscritosenviados;

    @Value("${dirrespuestassagarpa}")
    private String dirrespuestassagarpa;

    @Value("${filereportesmensualess}")
    private String filereportesmensualess;

    @Value("${fileescritosenviados}")
    private String fileescritosenviados;

    @Value("${filerespuestassagarpa}")
    private String filerespuestassagarpa;

    @Value("${presentacionesfolder}")
    private String presentacionesfolder;

    @Value("${tarjetaejecutivafolder}")
    private String tarjetaejecutivafolder;

    @Value("${justificacionespanolfolder}")
    private String justificacionespanolfolder;

    @Value("${justificacioninglesfolder}")
    private String justificacioninglesfolder;

    @Value("${tarjetaejecutivafile}")
    private String tarjetaejecutivafile;

    @Value("${justificacionespanolfile}")
    private String justificacionespanolfile;

    @Value("${justificacioninglesfile}")
    private String justificacioninglesfile;

    @Value("${reglasreglasoperacioncomercialfolder}")
    private String reglasreglasoperacioncomercialfolder;

    @Value("${reglasreglasoperacioncomercialfile}")
    private String reglasreglasoperacioncomercialfile;

    @Value("${reglascurriculummiembroscomercialfolder}")
    private String reglascurriculummiembroscomercialfolder;

    @Value("${reglascurriculummiembroscomercialfile}")
    private String reglascurriculummiembroscomercialfile;

    @Value("${reglascartasconfidcomercialfolder}")
    private String reglascartasconfidcomercialfolder;

    @Value("${reglascartasconfidcomercialfile}")
    private String reglascartasconfidcomercialfile;

    @Value("${reglasplananualsescomercialfolder}")
    private String reglasplananualsescomercialfolder;

    @Value("${reglasplananualsescomercialfile}")
    private String reglasplananualsescomercialfile;

    @Value("${reglasreportetriconsejocomercialfolder}")
    private String reglasreportetriconsejocomercialfolder;

    @Value("${reglasreportetriconsejocomercialfile}")
    private String reglasreportetriconsejocomercialfile;

    @Value("${reglasoperacioncomercialfolder}")
    private String reglasoperacioncomercialfolder;

    @Value("${reglasoperacioncomercialfile}")
    private String reglasoperacioncomercialfile;

    @Value("${filenuestrogobierno}")
    private String filenuestrogobierno;

    @Value("${comitecomercialfolder}")
    private String comitecomercialfolder;

    @Value("${curriculummiembroscomercialfolder}")
    private String curriculummiembroscomercialfolder;

    @Value("${curriculummiembroscomercialfile}")
    private String curriculummiembroscomercialfile;

    @Value("${cartasconfidcomercialfolder}")
    private String cartasconfidcomercialfolder;

    @Value("${cartasconfidcomercialfile}")
    private String cartasconfidcomercialfile;

    @Value("${plananualsescomercialfolder}")
    private String plananualsescomercialfolder;

    @Value("${plananualsescomercialfile}")
    private String plananualsescomercialfile;

    @Value("${reportetriconsejocomercialfolder}")
    private String reportetriconsejocomercialfolder;

    @Value("${reportetriconsejocomercialfile}")
    private String reportetriconsejocomercialfile;

    @Value("${codigoeticafolder}")
    private String codigoeticafolder;
    @Value("${codigoeticafile}")
    private String codigoeticafile;
    @Value("${informeactividadesfile}")
    private String informeactividadesfile;
    @Value("${layoutfile}")
    private String layoutfile;
    @Value("${dircensoganadero}")
    private String dircensoganadero;
    @Value("${censoganaderofile}")
    private String censoganaderofile;

    @Autowired
    DocumentosActivosRepository activosRepository;
    @Autowired
    DocumentosAcumuladosRepository acumuladosRepository;

    @Autowired
    FileManager fileManager;

    @Autowired
    CryptoFiles cryptoFiles;

    @Override
    public String getVentanas(MultipartFile file, String year, String month, String date, String direccion, String descripcion) {

        try {
            String HOME = ConstantsSGD.HOME;
            int pantalla = 0;
            String path = File.separator + dirPrincipal;
            String pathDocUnicos = path + File.separator + documentosunicos + File.separator;
            String pathDocAcumulados = path + File.separator + documentosacumulados + File.separator;
            String fileName = "";

            switch (direccion) {

                case "Acta Constitutiva":
                    pathDocUnicos += documentacionlegal + File.separator + escrituras + File.separator + actaconstitutiva + File.separator;
                    fileName = actaconstitutivafile;
                    break;

                case "Poderes":
                    pathDocUnicos += documentacionlegal + File.separator + escrituras + File.separator + poderes + File.separator;
                    fileName = poderesfile;
                    break;

                case "Reforma Estatutos":
                    pathDocUnicos += documentacionlegal + File.separator + escrituras + File.separator + reformaestatutos + File.separator;
                    fileName = reformaestatutosfile;
                    break;

                case "RFC":
                    pathDocUnicos += documentacionlegal + File.separator + rfc + File.separator;
                    fileName = rfcfile;
                    break;

                case "Fiel":
                    pathDocUnicos += documentacionlegal + File.separator + fiel + File.separator;
                    fileName = fielfile;
                    break;

                case "Sello Digital":
                    pathDocUnicos += documentacionlegal + File.separator + sellodigital + File.separator;
                    fileName = sellodigitalfile;
                    break;

                case "Aviso Privacidad":
                    pathDocUnicos += documentacionlegal + File.separator + avisoprivacidadfolder + File.separator;
                    fileName = avisoprivacidadfile;
                    break;

                case "Cumplimiento de Obligaciones":
                    pathDocUnicos += documentacionlegal + File.separator + cumplimientooblifolder + File.separator;
                    fileName = cumplimientooblifile;
                    break;

                case "Comprobante de Domicilio":
                    pathDocUnicos += documentacionlegal + File.separator + comprobantedomicilio + File.separator;
                    fileName = comprobantedomiciliofile;
                    break;

                case "Asamblea Ordinaria Aumento de Capital":
                    pathDocUnicos += documentacionlegal + File.separator + asambleaordinariafolder + File.separator;
                    fileName = asambleaordinariafile;
                    break;

                case "Estados Financieros":
                    pathDocUnicos += estadosfinancieros + File.separator + year + File.separator;
                    fileName = prefijoestadosfinancieros + month + "-" + year + ".pdf";
                    break;

                case "Contratos Firmados Financieros":
                    pathDocUnicos += contratosfirmadosfolder + File.separator + contratosfirmadosfinancierosfolder + File.separator;
                    fileName = cffinancierosfile;
                    break;

                case "Contratos Firmados Proveedores":
                    pathDocUnicos += contratosfirmadosfolder + File.separator + contratosfirmadosprovedoresfolder + File.separator;
                    fileName = cfprovedoresfile;
                    break;

                case "Contratos Firmados Clientes":
                    pathDocUnicos += contratosfirmadosfolder + File.separator + contratosfirmadosclientesfolder + File.separator;
                    fileName = cfclientesfile;
                    break;

                case "Contratos Firmados Personal":
                    pathDocUnicos += contratosfirmadosfolder + File.separator + contratosfirmadospersonalfolder + File.separator;
                    fileName = cfpersonalfile;
                    break;

                case "Reporte de Ventas":
                    pathDocUnicos += reporteventasfolder + File.separator + year + File.separator;
                    fileName = prefijoreporteventas + month + "-" + year + ".pdf";
                    break;

                case "Sagarpa Documentos de Solicitud":
                    pathDocUnicos += sagarpafolder + File.separator + documentosolifolder + File.separator;
                    fileName = documentosolifile + year + ".pdf";
                    break;

                case "Sagarpa Deposito":
                    pathDocUnicos += sagarpafolder + File.separator + depositofolder + File.separator;
                    fileName = depositofile + year + ".pdf";
                    break;

                case "Sagarpa Comprobante de Pagos":
                    pathDocUnicos += sagarpafolder + File.separator + comprobantepagosfolder + File.separator;
                    fileName = comprobantepagosfile + year + ".pdf";
                    break;

                case "Sagarpa Reportes Mensuales":
                    pathDocUnicos += sagarpafolder + File.separator + dirreportesmensualess + File.separator;
                    fileName = filereportesmensualess + "-" + year + ".pdf";
                    break;

                case "Sagarpa Escritos Enviados":
                    pathDocUnicos += sagarpafolder + File.separator + direscritosenviados + File.separator;
                    fileName = fileescritosenviados + "-" + year + ".pdf";
                    break;

                case "Sagarpa Respuestas Sagarpa":
                    pathDocUnicos += sagarpafolder + File.separator + dirrespuestassagarpa + File.separator;
                    fileName = filerespuestassagarpa + "-" + year + ".pdf";
                    break;

                case "Presentaciones Tarjeta Ejecutiva":
                    pathDocUnicos += presentacionesfolder + File.separator + tarjetaejecutivafolder + File.separator;
                    fileName = tarjetaejecutivafile;
                    break;

                case "Presentaciones Justificacion(Espanol)":
                    pathDocUnicos += presentacionesfolder + File.separator + justificacionespanolfolder + File.separator;
                    fileName = justificacionespanolfile;
                    break;

                case "Presentaciones Justificacion(Ingles)":
                    pathDocUnicos += presentacionesfolder + File.separator + justificacioninglesfolder + File.separator;
                    fileName = justificacioninglesfile;
                    break;

                case "Activos Engorda Inmuebles":
                    pathDocUnicos += facturasactivos + File.separator + inmuebles + File.separator;
                    fileName = prefijofact + "I" + engorda + ".pdf";
                    break;

                case "Activos Engorda Maquinaria":
                    pathDocUnicos += facturasactivos + File.separator + maquinariayequipo + File.separator;
                    fileName = prefijofact + "MyE" + engorda + ".pdf";
                    break;

                case "Activos Engorda transporte":
                    pathDocUnicos += facturasactivos + File.separator + equipotransporte + File.separator;
                    fileName = prefijofact + "EdT" + engorda + ".pdf";
                    break;

                case "Activos Engorda Mobiliario":
                    pathDocUnicos += facturasactivos + File.separator + mobiliario + File.separator;
                    fileName = prefijofact + "Mo" + engorda + ".pdf";
                    break;

                case "Activos Engorda Computo":
                    pathDocUnicos += facturasactivos + File.separator + equipocomputo + File.separator;
                    fileName = prefijofact + "EdC" + engorda + ".pdf";
                    break;

                case "Activos Rastro Inmuebles":
                    pathDocUnicos += facturasactivos + File.separator + inmuebles + File.separator;
                    fileName = prefijofact + "I" + rastro + ".pdf";
                    break;

                case "Activos Rastro Maquinaria":
                    pathDocUnicos += facturasactivos + File.separator + maquinariayequipo + File.separator;
                    fileName = prefijofact + "MyE" + rastro + ".pdf";
                    break;

                case "Activos Rastro transporte":
                    pathDocUnicos += facturasactivos + File.separator + equipotransporte + File.separator;
                    fileName = prefijofact + "EdT" + rastro + ".pdf";
                    break;

                case "Activos Rastro Mobiliario":
                    pathDocUnicos += facturasactivos + File.separator
                            + mobiliario + File.separator;
                    fileName = prefijofact + "Mo" + rastro + ".pdf";
                    break;

                case "Activos Rastro Computo":
                    pathDocUnicos += facturasactivos + File.separator + equipocomputo + File.separator;
                    fileName = prefijofact + "EdC" + rastro + ".pdf";
                    break;

                case "Activos Cortes Inmuebles":
                    pathDocUnicos += facturasactivos + File.separator + inmuebles + File.separator;
                    fileName = prefijofact + "I" + cortes + ".pdf";
                    break;

                case "Activos Cortes Maquinaria":
                    pathDocUnicos += facturasactivos + File.separator + maquinariayequipo + File.separator;
                    fileName = prefijofact + "MyE" + cortes + ".pdf";
                    break;

                case "Activos Cortes transporte":
                    pathDocUnicos += facturasactivos + File.separator + equipotransporte + File.separator;
                    fileName = prefijofact + "EdT" + cortes + ".pdf";
                    break;

                case "Activos Cortes Mobiliario":
                    pathDocUnicos += facturasactivos + File.separator + mobiliario + File.separator;
                    fileName = prefijofact + "Mo" + cortes + ".pdf";
                    break;

                case "Activos Cortes Computo":
                    pathDocUnicos += facturasactivos + File.separator + equipocomputo + File.separator;
                    fileName = prefijofact + "EdC" + cortes + ".pdf";
                    break;

                case "Activos Corporativo Inmuebles":
                    pathDocUnicos += facturasactivos + File.separator + inmuebles + File.separator;
                    fileName = prefijofact + "I" + corporativo + ".pdf";
                    break;

                case "Activos Corporativo Maquinaria":
                    pathDocUnicos += facturasactivos + File.separator + maquinariayequipo + File.separator;
                    fileName = prefijofact + "MyE" + corporativo + ".pdf";
                    break;

                case "Activos Corporativo transporte":
                    pathDocUnicos += facturasactivos + File.separator + equipotransporte + File.separator;
                    fileName = prefijofact + "EdT" + corporativo + ".pdf";
                    break;

                case "Activos Corporativo Mobiliario":
                    pathDocUnicos += facturasactivos + File.separator + mobiliario + File.separator;
                    fileName = prefijofact + "Mo" + corporativo + ".pdf";
                    break;

                case "Activos Corporativo Computo":
                    pathDocUnicos += facturasactivos + File.separator + equipocomputo + File.separator;
                    fileName = prefijofact + "EdC" + corporativo + ".pdf";
                    break;

                case "Seguros Engorda Inmuebles":
                    pathDocUnicos += seguros + File.separator + inmuebles + File.separator;
                    fileName = prefijoseg + "I" + engorda + ".pdf";
                    break;

                case "Seguros Engorda Maquinaria":
                    pathDocUnicos += seguros + File.separator + maquinariayequipo + File.separator;
                    fileName = prefijoseg + "MyE" + engorda + ".pdf";
                    break;

                case "Seguros Engorda transporte":
                    pathDocUnicos += seguros + File.separator + equipotransporte + File.separator;
                    fileName = prefijoseg + "EdT" + engorda + ".pdf";
                    break;

                case "Seguros Engorda Mobiliario":
                    pathDocUnicos += seguros + File.separator + mobiliario + File.separator;
                    fileName = prefijoseg + "Mo" + engorda + ".pdf";
                    break;

                case "Seguros Engorda Computo":
                    pathDocUnicos += seguros + File.separator + equipocomputo + File.separator;
                    fileName = prefijoseg + "EdC" + engorda + ".pdf";
                    break;

                case "Seguros Rastro Inmuebles":
                    pathDocUnicos += seguros + File.separator + inmuebles + File.separator;
                    fileName = prefijoseg + "I" + rastro + ".pdf";
                    break;

                case "Seguros Rastro Maquinaria":
                    pathDocUnicos += seguros + File.separator + maquinariayequipo + File.separator;
                    fileName = prefijoseg + "MyE" + rastro + ".pdf";
                    break;

                case "Seguros Rastro transporte":
                    pathDocUnicos += seguros + File.separator
                            + equipotransporte + File.separator;
                    fileName = prefijoseg + "EdT" + rastro + ".pdf";
                    break;

                case "Seguros Rastro Mobiliario":
                    pathDocUnicos += seguros + File.separator + mobiliario + File.separator;
                    fileName = prefijoseg + "Mo" + rastro + ".pdf";
                    break;

                case "Seguros Rastro Computo":
                    pathDocUnicos += seguros + File.separator + equipocomputo + File.separator;
                    fileName = prefijoseg + "EdC" + rastro + ".pdf";
                    break;

                case "Seguros Cortes Inmuebles":
                    pathDocUnicos += seguros + File.separator + inmuebles + File.separator;
                    fileName = prefijoseg + "I" + cortes + ".pdf";
                    break;

                case "Seguros Cortes Maquinaria":
                    pathDocUnicos += seguros + File.separator + maquinariayequipo + File.separator;
                    fileName = prefijoseg + "MyE" + cortes + ".pdf";
                    break;

                case "Seguros Cortes transporte":
                    pathDocUnicos += seguros + File.separator + equipotransporte + File.separator;
                    fileName = prefijoseg + "EdT" + cortes + ".pdf";
                    break;

                case "Seguros Cortes Mobiliario":
                    pathDocUnicos += seguros + File.separator + mobiliario + File.separator;
                    fileName = prefijoseg + "Mo" + cortes + ".pdf";
                    break;

                case "Seguros Cortes Computo":
                    pathDocUnicos += seguros + File.separator + equipocomputo + File.separator;
                    fileName = prefijoseg + "EdC" + cortes + ".pdf";
                    break;

                case "Seguros Corporativo Inmuebles":
                    pathDocUnicos += seguros + File.separator + inmuebles + File.separator;
                    fileName = prefijoseg + "I" + corporativo + ".pdf";
                    break;

                case "Seguros Corporativo Maquinaria":
                    pathDocUnicos += seguros + File.separator + maquinariayequipo + File.separator;
                    fileName = prefijoseg + "MyE" + corporativo + ".pdf";
                    break;

                case "Seguros Corporativo transporte":
                    pathDocUnicos += seguros + File.separator + equipotransporte + File.separator;
                    fileName = prefijoseg + "EdT" + corporativo + ".pdf";
                    break;

                case "Seguros Corporativo Mobiliario":
                    pathDocUnicos += seguros + File.separator + mobiliario + File.separator;
                    fileName = prefijoseg + "Mo" + corporativo + ".pdf";
                    break;

                case "Seguros Corporativo Computo":
                    pathDocUnicos += seguros + File.separator + equipocomputo + File.separator;
                    fileName = prefijoseg + "EdC" + corporativo + ".pdf";
                    break;

                case "Avaluo Engorda Inmuebles":
                    pathDocUnicos += avaluosactivos + File.separator + inmuebles + File.separator;
                    fileName = prefijoavaluo + "I" + engorda + ".pdf";
                    break;

                case "Avaluo Engorda Maquinaria":
                    pathDocUnicos += avaluosactivos + File.separator + maquinariayequipo + File.separator;
                    fileName = prefijoavaluo + "MyE" + engorda + ".pdf";
                    break;

                case "Avaluo Engorda transporte":
                    pathDocUnicos += avaluosactivos + File.separator + equipotransporte + File.separator;
                    fileName = prefijoavaluo + "EdT" + engorda + ".pdf";
                    break;

                case "Avaluo Engorda Mobiliario":
                    pathDocUnicos += avaluosactivos + File.separator + mobiliario + File.separator;
                    fileName = prefijoavaluo + "Mo" + engorda + ".pdf";
                    break;

                case "Avaluo Engorda Computo":
                    pathDocUnicos += avaluosactivos + File.separator + equipocomputo + File.separator;
                    fileName = prefijoavaluo + "EdC" + engorda + ".pdf";
                    break;

                case "Avaluo Rastro Inmuebles":
                    pathDocUnicos += avaluosactivos + File.separator + inmuebles + File.separator;
                    fileName = prefijoavaluo + "I" + rastro + ".pdf";
                    break;

                case "Avaluo Rastro Maquinaria":
                    pathDocUnicos += avaluosactivos + File.separator + maquinariayequipo + File.separator;
                    fileName = prefijoavaluo + "MyE" + rastro + ".pdf";
                    break;

                case "Avaluo Rastro transporte":
                    pathDocUnicos += avaluosactivos + File.separator + equipotransporte + File.separator;
                    fileName = prefijoavaluo + "EdT" + rastro + ".pdf";
                    break;

                case "Avaluo Rastro Mobiliario":
                    pathDocUnicos += avaluosactivos + File.separator + mobiliario + File.separator;
                    fileName = prefijoavaluo + "Mo" + rastro + ".pdf";
                    break;

                case "Avaluo Rastro Computo":
                    pathDocUnicos += avaluosactivos + File.separator + equipocomputo + File.separator;
                    fileName = prefijoavaluo + "EdC" + rastro + ".pdf";
                    break;

                case "Avaluo Cortes Inmuebles":
                    pathDocUnicos += avaluosactivos + File.separator + inmuebles + File.separator;
                    fileName = prefijoavaluo + "I" + cortes + ".pdf";
                    break;

                case "Avaluo Cortes Maquinaria":
                    pathDocUnicos += avaluosactivos + File.separator + maquinariayequipo + File.separator;
                    fileName = prefijoavaluo + "MyE" + cortes + ".pdf";
                    break;

                case "Avaluo Cortes transporte":
                    pathDocUnicos += avaluosactivos + File.separator + equipotransporte + File.separator;
                    fileName = prefijoavaluo + "EdT" + cortes + ".pdf";
                    break;

                case "Avaluo Cortes Mobiliario":
                    pathDocUnicos += avaluosactivos + File.separator + mobiliario + File.separator;
                    fileName = prefijoavaluo + "Mo" + cortes + ".pdf";
                    break;

                case "Avaluo Cortes Computo":
                    pathDocUnicos += avaluosactivos + File.separator + equipocomputo + File.separator;
                    fileName = prefijoavaluo + "EdC" + cortes + ".pdf";
                    break;

                case "Avaluo Corporativo Inmuebles":
                    pathDocUnicos += avaluosactivos + File.separator + inmuebles + File.separator;
                    fileName = prefijoavaluo + "I" + corporativo + ".pdf";
                    break;

                case "Avaluo Corporativo Maquinaria":
                    pathDocUnicos += avaluosactivos + File.separator + maquinariayequipo + File.separator;
                    fileName = prefijoavaluo + "MyE" + corporativo + ".pdf";
                    break;

                case "Avaluo Corporativo transporte":
                    pathDocUnicos += avaluosactivos + File.separator + equipotransporte + File.separator;
                    fileName = prefijoavaluo + "EdT" + corporativo + ".pdf";
                    break;

                case "Avaluo Corporativo Mobiliario":
                    pathDocUnicos += avaluosactivos + File.separator + mobiliario + File.separator;
                    fileName = prefijoavaluo + "Mo" + corporativo + ".pdf";
                    break;

                case "Avaluo Corporativo Computo":
                    pathDocUnicos += avaluosactivos + File.separator + equipocomputo + File.separator;
                    fileName = prefijoavaluo + "EdC" + corporativo + ".pdf";
                    break;

                case "Organigrama":
                    pathDocUnicos += organigramafolder + File.separator;
                    fileName = organigramafile + ".pdf";
                    break;

                case "Estudios Economicos":
                    pathDocUnicos += informacionIndusfolder + File.separator + estudioseconofolder + File.separator;
                    fileName = estudioseconofile;
                    break;

                case "Precio Ganado en Pie":
                    pathDocUnicos += informacionIndusfolder + File.separator + precioganadofolder + File.separator;
                    fileName = precioganadofile;
                    break;

                case "Precio Carne en Canal":
                    pathDocUnicos += informacionIndusfolder + File.separator + preciocarnecanfolder + File.separator;
                    fileName = preciocarnecanfile;
                    break;

                case "Precio Alimento":
                    pathDocUnicos += informacionIndusfolder + File.separator + precioalimentofolder + File.separator;
                    fileName = precioalimentofile;
                    break;
                case "Censo Ganadero Agssa":
                    pathDocUnicos += informacionIndusfolder + File.separator + dircensoganadero + File.separator;
                    fileName = censoganaderofile;
                    break;

                case "Consejo de Administracion Reglas de Operacion":
                    pathDocUnicos += gobiernocorporativofolder + File.separator + consejofolder + File.separator + reglasreglasoperacioncomercialfolder + File.separator;
                    fileName = fileReglasOperacionesComercial;
                    break;

                case "Consejo de Administracion Curriculum Miembros":
                    pathDocUnicos += gobiernocorporativofolder + File.separator + consejofolder + File.separator + reglascurriculummiembroscomercialfolder + File.separator;
                    fileName = reglascurriculummiembroscomercialfile;
                    break;

                case "Consejo de Administracion Cartas de Confidencialidad":
                    pathDocUnicos += gobiernocorporativofolder + File.separator + consejofolder + File.separator + reglascartasconfidcomercialfolder + File.separator;
                    fileName = reglascartasconfidcomercialfile;
                    break;

                case "Consejo de Administracion Plan Anual Sesiones":
                    pathDocUnicos += gobiernocorporativofolder + File.separator + consejofolder + File.separator + reglasplananualsescomercialfolder + File.separator;
                    fileName = reglasplananualsescomercialfile;
                    break;

                case "Consejo de Administracion Reportes Trimestrales":
                    pathDocUnicos += gobiernocorporativofolder + File.separator + consejofolder + File.separator + reglasreportetriconsejocomercialfolder + File.separator;
                    fileName = reglasreportetriconsejocomercialfile;
                    break;

                case "Comite Inversiones Sesiones":
                    pantalla = 9;
                    fileName = file.getOriginalFilename();
                    break;

                case "Comite Engorda Sesiones":
                    pantalla = 8;
                    fileName = file.getOriginalFilename();
                    break;

                case "Gobierno Nuestro Gobierno":
                    pathDocUnicos += gobiernocorporativofolder + File.separator;
                    fileName = filenuestrogobierno;
                    break;

                case "Gobierno Asamblea de Accionistas":
                    pantalla = 5;
                    fileName = file.getOriginalFilename();
                    break;

                case "Consejo de Administracion Sesiones":
                    pantalla = 6;
                    fileName = file.getOriginalFilename();
                    break;

                case "Asamblea de Accionistas Orden del Dia":
                    pantalla = 22;
                    fileName = file.getOriginalFilename();
                    break;

                case "Asamblea de Accionistas Presentacion de Informacion":
                    pantalla = 23;
                    fileName = file.getOriginalFilename();
                    break;

                case "Asamblea de Accionistas Minuta":
                    pantalla = 24;
                    fileName = file.getOriginalFilename();
                    break;

                case "Gobierno Corporativo Consejo":
                    pathDocUnicos += gobiernocorporativofolder + File.separator + consejofolder + File.separator;
                    fileName = consejofile;
                    break;

                case "Gobierno Corporativo Enlaces Corporativo":
                    pathDocUnicos += gobiernocorporativofolder + File.separator + dirEnlacesCorporativos + File.separator;
                    fileName = fileEnlacesCorporativos;
                    break;

                case "Comite de Inversiones Convocatoria":
                    pantalla = 9;
                    fileName = file.getOriginalFilename();
                    break;

                case "Comite de Inversiones Orden del Dia":
                    pantalla = 10;
                    fileName = file.getOriginalFilename();
                    break;

                case "Comite de Inversiones Presentacion de Informacion":
                    pantalla = 11;
                    fileName = file.getOriginalFilename();
                    break;

                case "Comite de Inversiones Minuta":
                    pantalla = 12;
                    fileName = file.getOriginalFilename();
                    break;

                case "Comite de Inversiones Reglas de Operacion":
                    pathDocUnicos += gobiernocorporativofolder + File.separator + dirComites + File.separator + dirReglasOperacionesInversiones + File.separator;
                    fileName = fileReglasOperacionesInversiones;
                    break;

                case "Comite de Inversiones Curriculum Miembros":
                    pathDocUnicos += gobiernocorporativofolder + File.separator + dirComites + File.separator + dirCurriculumMiembrosInversiones + File.separator;
                    fileName = fileCurriculumMiembrosInversiones;
                    break;

                case "Comite de Inversiones Cartas de Confidencialidad":
                    pathDocUnicos += gobiernocorporativofolder + File.separator + dirComites + File.separator + dirCartasConfidencialidadInversiones + File.separator;
                    fileName = fileCartasConfidencialidadInversiones;
                    break;

                case "Comite de Inversiones Plan Anual Sesiones":
                    pathDocUnicos += gobiernocorporativofolder + File.separator + dirComites + File.separator + dirPlanAnualSesionesInversiones + File.separator;
                    fileName = filePlanAnualSesionesInversiones;
                    break;

                case "Comite de Inversiones Reportes Trimestrales":
                    pathDocUnicos += gobiernocorporativofolder + File.separator + dirComites + File.separator + dirReporteTrimestralInversiones + File.separator;
                    fileName = fileReporteTrimestralInversiones;
                    break;

                case "Comite Comercial Sesiones":
                    pantalla = 7;
                    fileName = file.getOriginalFilename();
                    break;

                case "Comite Engorda Reglas de Operacion":
                    pathDocUnicos += gobiernocorporativofolder + File.separator + dirComiteEngorda + File.separator + dirReglasOperacionesEngorda + File.separator;
                    fileName = fileReglasOperacionesEngorda;
                    break;

                case "Comite Engorda Curriculum Miembros":
                    pathDocUnicos += gobiernocorporativofolder + File.separator + dirComiteEngorda + File.separator + dirCurriculumMiembrosEngorda + File.separator;
                    fileName = fileCurriculumMiembrosEngorda;
                    break;

                case "Comite Engorda Cartas de Confidencialidad":
                    pathDocUnicos += gobiernocorporativofolder + File.separator + dirComiteEngorda + File.separator + dirCartasConfidencialidadEngorda + File.separator;
                    fileName = fileCartasConfidencialidadEngorda;
                    break;

                case "Comite Engorda Plan Anual Sesiones":
                    pathDocUnicos += gobiernocorporativofolder + File.separator + dirComiteEngorda + File.separator + dirPlanAnualSesionesEngorda + File.separator;
                    fileName = filePlanAnualSesionesEngorda;
                    break;

                case "Comite Engorda Reportes Trimestrales":
                    pathDocUnicos += gobiernocorporativofolder + File.separator + dirComiteEngorda + File.separator + dirReporteTrimestralEngorda + File.separator;
                    fileName = fileReporteTrimestralEngorda;
                    break;

                case "Comite Comercial Reglas de Operacion":
                    pathDocUnicos += gobiernocorporativofolder + File.separator + comitecomercialfolder + File.separator + reglasoperacioncomercialfolder + File.separator;
                    fileName = reglasoperacioncomercialfile;
                    break;

                case "Comite Comercial Curriculum Miembros":
                    pathDocUnicos += gobiernocorporativofolder + File.separator + comitecomercialfolder + File.separator + curriculummiembroscomercialfolder + File.separator;
                    fileName = curriculummiembroscomercialfile;
                    break;

                case "Comite Comercial Cartas de Confidencialidad":
                    pathDocUnicos += gobiernocorporativofolder + File.separator + comitecomercialfolder + File.separator + cartasconfidcomercialfolder + File.separator;
                    fileName = cartasconfidcomercialfile;
                    break;

                case "Comite Comercial Plan Anual Sesiones":
                    pathDocUnicos += gobiernocorporativofolder + File.separator + comitecomercialfolder + File.separator + plananualsescomercialfolder + File.separator;
                    fileName = plananualsescomercialfile;
                    break;

                case "Comite Comercial Reportes Trimestrales":
                    pathDocUnicos += gobiernocorporativofolder + File.separator + comitecomercialfolder + File.separator + reportetriconsejocomercialfolder + File.separator;
                    fileName = reportetriconsejocomercialfile;
                    break;

                case "Gobierno Corporativo Protocolo de Accionistas":
                    pathDocUnicos += gobiernocorporativofolder + File.separator + protocoloacciofolder + File.separator;
                    fileName = protocoloacciofile;
                    break;

                case "Gobierno Corporativo Codigo de Etica":
                    pathDocUnicos += gobiernocorporativofolder + File.separator + codigoeticafolder + File.separator;
                    fileName = codigoeticafile;
                    break;

                case "Gobierno Corporativo Informe de Actividades":
                    pathDocUnicos += gobiernocorporativofolder + File.separator + informaactivifolder + File.separator + year + File.separator;
                    fileName = informeactividadesfile + month + "-" + year + ".pdf";
                    break;

                case "Acta Asamblea":
                    pantalla = 1;
                    fileName = file.getOriginalFilename();
                    break;
                case "Identificaciones":
                    pantalla = 2;
                    fileName = file.getOriginalFilename();
                    break;
                case "Aumentos Capital":
                    pantalla = 3;
                    fileName = file.getOriginalFilename();
                    break;
                case "Presentaciones Corporativas":
                    pantalla = 4;
                    fileName = file.getOriginalFilename();
                    break;

                case "Estructura del Terreno":
                    pathDocUnicos += proyectoejecutivofolder + File.separator + terrenofolder + File.separator;
                    fileName = estructuraterrenofile;
                    break;

                case "Avaluo del Terreno":
                    pathDocUnicos += proyectoejecutivofolder + File.separator + terrenofolder + File.separator;
                    fileName = avaluoterrenofile;
                    break;

                case "Proyecto Entregado TIF 2000":
                    pathDocUnicos += proyectoejecutivofolder + File.separator + proyectofolder + File.separator;
                    fileName = proyectoentregadofile;
                    break;

                case "Programa General de Necesidades":
                    pathDocUnicos += proyectoejecutivofolder + File.separator + proyectofolder + File.separator;
                    fileName = programanecesidadesfile;
                    break;

                case "Programa Particular de Necesidades":
                    pathDocUnicos += proyectoejecutivofolder + File.separator + proyectofolder + File.separator;
                    fileName = programaparticularfile;
                    break;

                case "Justificacion del Proyecto":
                    pathDocUnicos += proyectoejecutivofolder + File.separator + proyectofolder + File.separator;
                    fileName = justificacionproyectofile;
                    break;

                case "Estudios Mecanica de Suelos":
                    pathDocUnicos += proyectoejecutivofolder + File.separator + estudiosfolder + File.separator;
                    fileName = impactomecanicadesuelosfile;
                    break;

                case "Estudio Impacto Ambiental":
                    pathDocUnicos += proyectoejecutivofolder + File.separator + estudiosfolder + File.separator;
                    fileName = impactoambientalfile;
                    break;

                case "Estudio Impacto Vial":
                    pathDocUnicos += proyectoejecutivofolder + File.separator + estudiosfolder + File.separator;
                    fileName = impactovialfile;
                    break;

                case "Factibilidad Servicio Energia Electrica":
                    pathDocUnicos += proyectoejecutivofolder + File.separator + estudiosfolder + File.separator;
                    fileName = energiaelectricafile;
                    break;

                case "Estudio Hidrologico":
                    pathDocUnicos += proyectoejecutivofolder + File.separator + estudiosfolder + File.separator;
                    fileName = estudiohidrologicofile;
                    break;

                case "Factibilidad de Uso de Suelo":
                    pathDocUnicos += proyectoejecutivofolder + File.separator + estudiosfolder + File.separator;
                    fileName = usodesuelofile;
                    break;

                case "Concesion de Aguas Nacionales":
                    pathDocUnicos += proyectoejecutivofolder + File.separator + estudiosfolder + File.separator;
                    fileName = concesionaguasfile;
                    break;

                case "Plano de Terreno Regional":
                    pathDocUnicos += proyectoejecutivofolder + File.separator + planosfolder + File.separator;
                    fileName = planoregionalfile;
                    break;

                case "Plano de Terreno Rural":
                    pathDocUnicos += proyectoejecutivofolder + File.separator + planosfolder + File.separator;
                    fileName = planoruralfile;
                    break;

                case "Plano Topografico":
                    pathDocUnicos += proyectoejecutivofolder + File.separator + planosfolder + File.separator;
                    fileName = planotopograficofile;
                    break;

                case "Justificacion del Terreno":
                    pathDocUnicos += proyectoejecutivofolder + File.separator + planosfolder + File.separator;
                    fileName = ubicacionterrenofile;
                    break;

                case "Planos Infraestructura":
                    pathDocUnicos += proyectoejecutivofolder + File.separator + planosfolder + File.separator;
                    fileName = planosinfraestructurafile;
                    break;

                case "Layout":
                    pathDocUnicos += proyectoejecutivofolder + File.separator + planosfolder + File.separator;
                    fileName = layoutfile;
                    break;

                default:
                    throw new DownloadException();

            }
            FileManagerDTO fileManagerDTO = new FileManagerDTO();
            fileManagerDTO.setFile(file);
            fileManagerDTO.setPath(pantalla == 0 ? HOME + pathDocUnicos : HOME + pathDocAcumulados);
            fileManagerDTO.setName(fileName);

            File pathencript = new File(fileManagerDTO.getPath() + fileName);

            fileManager.uploading(fileManagerDTO);

            File fileEnc = cryptoFiles.processFileEncrypt(pathencript, false);

            if (fileEnc != null && pantalla == 0) {

                DocumentosActivosEntity doctoExiste = activosRepository.findByRutaAndNombre(pathDocUnicos, fileName);
                DocumentosActivosEntity entity = new DocumentosActivosEntity();

                if (doctoExiste != null) {
                    doctoExiste.setFecha(new Timestamp(System.currentTimeMillis()));
                    activosRepository.save(doctoExiste);
                } else {
                    entity.setFecha(new Timestamp(System.currentTimeMillis()));
                    entity.setRuta(pathDocUnicos);
                    entity.setNombre(fileName);
                    entity.setUsuario_id(1);
                    activosRepository.save(entity);
                }
            } else if (fileEnc != null && pantalla != 0) {

                DocumentosAcumuladosEntity doctoExiste = acumuladosRepository.findByRutaAndDescripcionAndPantalla(pathDocAcumulados, descripcion, pantalla);
                DocumentosAcumuladosEntity entity = new DocumentosAcumuladosEntity();

                if (doctoExiste != null) {
                    return "El documento existe";
                } else {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                    Date fecha = dateFormat.parse(date);
                    entity.setFecha(new Timestamp(fecha.getTime()));
                    entity.setRuta(pathDocAcumulados);
                    entity.setNombre(fileName);
                    entity.setUsuario_id(1);
                    entity.setPantalla(pantalla);
                    entity.setEstatus(1);
                    entity.setDescripcion(descripcion);
                    acumuladosRepository.save(entity);
                }
            } else {
                throw new DownloadException();
            }
        } catch (IOException ex) {
            Logger.getLogger(VentanaServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new DownloadException();
        } catch (ParseException ex) {
            Logger.getLogger(VentanaServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        return "Documento salvado";
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
                mv.addObject("descripcion", false);
                break;

            case "Gobierno Corporativo Informe de Actividades":
                mv.addObject("lista1", true);
                mv.addObject("lista2", true);
                mv.addObject("descripcion", false);
                break;
            case "Reporte de Ventas":
                mv.addObject("lista1", true);
                mv.addObject("lista2", true);
                mv.addObject("descripcion", false);
                break;

            case "Sagarpa Documentos de Solicitud":
                mv.addObject("lista1", true);
                mv.addObject("descripcion", false);
                break;

            case "Sagarpa Deposito":
                mv.addObject("lista1", true);
                mv.addObject("descripcion", false);
                break;

            case "Sagarpa Comprobante de Pagos":
                mv.addObject("lista1", true);
                mv.addObject("descripcion", false);
                break;

            case "Gobierno Asamblea de Accionistas":
                mv.addObject("descripcion", true);
                mv.addObject("calendario", true);
                break;

            case "Comite Engorda Sesiones":
                mv.addObject("descripcion", true);
                mv.addObject("calendario", true);
                break;

            case "Comite Comercial Sesiones":
                mv.addObject("descripcion", true);
                mv.addObject("calendario", true);
                break;

            case "Consejo de Administracion Sesiones":
                mv.addObject("descripcion", true);
                mv.addObject("calendario", true);
                break;

            case "Comite Inversiones Sesiones":
                mv.addObject("descripcion", true);
                mv.addObject("calendario", true);
                break;

            case "Sagarpa Reportes Mensuales":
                mv.addObject("lista1", true);
                mv.addObject("descripcion", false);
                break;

            case "Sagarpa Escritos Enviados":
                mv.addObject("lista1", true);
                mv.addObject("descripcion", false);
                break;

            case "Sagarpa Respuestas Sagarpa":
                mv.addObject("lista1", true);
                mv.addObject("descripcion", false);
                break;

            case "Asamblea de Accionistas Convocatoria":
                mv.addObject("lista1", true);
                mv.addObject("descripcion", false);
                break;

            case "Asamblea de Accionistas Minuta":
                mv.addObject("lista1", true);
                mv.addObject("descripcion", false);
                break;

            case "Asamblea de Accionistas Orden del Dia":
                mv.addObject("lista1", true);
                mv.addObject("descripcion", false);
                break;

            case "Asamblea de Accionistas Presentacion de Informacion":
                mv.addObject("lista1", true);
                mv.addObject("descripcion", false);
                break;

            case "Comite de Inversiones Convocatoria":
                mv.addObject("lista1", true);
                mv.addObject("descripcion", false);
                break;

            case "Comite de Inversiones Orden del Dia":
                mv.addObject("descripcion", false);
                mv.addObject("lista1", true);
                break;

            case "Comite de Inversiones Presentacion de Informacion":
                mv.addObject("descripcion", false);
                mv.addObject("lista1", true);
                break;

            case "Comite de Inversiones Minuta":
                mv.addObject("descripcion", false);
                mv.addObject("lista1", true);
                break;

            case "Comite Engorda Convocatoria":
                mv.addObject("lista1", true);
                mv.addObject("descripcion", false);
                break;

            case "Comite Engorda Orden del Dia":
                mv.addObject("lista1", true);
                mv.addObject("descripcion", false);
                break;

            case "Comite Engorda Presentacion de Informacion":
                mv.addObject("lista1", true);
                mv.addObject("descripcion", false);
                break;

            case "Comite Engorda Minuta":
                mv.addObject("lista1", true);
                mv.addObject("descripcion", false);
                break;
            case "Acta Asamblea":
                mv.addObject("descripcion", true);
                mv.addObject("calendario", true);
                break;
            case "Identificaciones":
                mv.addObject("descripcion", true);
                mv.addObject("calendario", true);
                break;
            case "Aumentos Capital":
                mv.addObject("descripcion", true);
                mv.addObject("calendario", true);
                break;
            case "Presentaciones Corporativas":
                mv.addObject("descripcion", true);
                mv.addObject("calendario", true);
                break;
        }
    }

}
