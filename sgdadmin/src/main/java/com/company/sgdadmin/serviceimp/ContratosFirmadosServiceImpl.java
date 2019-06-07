/*
 * 
 */
package com.company.sgdadmin.serviceimp;

import com.company.sdg.mvc.exceptions.AJAXException;
import com.company.sgdadmin.entity.DocumentosActivosEntity;
import com.company.sgdadmin.entity.NombresEntity;
import com.company.sgdadmin.repository.DocumentosActivosRepository;
import com.company.sgdadmin.repository.NombresRepository;
import com.company.sgdadmin.service.ContratosFirmadosService;
import com.company.sgdadmin.service.FileManager;
import com.company.sgdadmin.util.ConstantsSGD;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 *
 *
 */
@Service
public class ContratosFirmadosServiceImpl implements ContratosFirmadosService {

    @Value("${directorio}")
    private String dirPrincipal;
    @Value("${documentosunicos}")
    private String documentosunicos;
    @Autowired
    DocumentosActivosRepository repository;
    @Value("${contratosfirmadosfolder}")
    private String dirContratosFirmados;
    @Value("${contratosfirmadosclientesfolder}")
    private String dirCliente;
    @Value("${contratosfirmadosprovedoresfolder}")
    private String dirProvedores;
    @Value("${contratosfirmadospersonalfolder}")
    private String dirPersonal;
    @Value("${contratosfirmadosfinancierosfolder}")
    private String dirFinancieros;
    @Value("${cffinancierosfile}")
    private String fileFinancieros;
    @Value("${cfclientesfile}")
    private String fileClientes;
    @Value("${cfprovedoresfile}")
    private String fileProvedores;
    @Value("${cfpersonalfile}")
    private String filePersonal;
    @Value("${informacionIndusfolder}")
    private String dirInformacionIndustria;
    @Value("${estudioseconofolder}")
    private String dirEstudiosEconomicos;
    @Value("${precioganadofolder}")
    private String dirPrecioGanado;
    @Value("${preciocarnecanfolder}")
    private String dirPrecioCarne;
    @Value("${precioalimentofolder}")
    private String dirPrecioAlimento;
    @Value("${estudioseconofile}")
    private String fileEstudiosEconomicos;
    @Value("${precioganadofile}")
    private String filePrecioGanado;
    @Value("${preciocarnecanfile}")
    private String filePrecioCarne;
    @Value("${precioalimentofile}")
    private String filePrecioAlimento;
    @Value("${gobiernocorporativofolder}")
    private String dirGobiernoCorporativo;
    @Value("${comitesfolder}")
    private String dirComites;
    @Value("${consejofolder}")
    private String dirConsejo;
    @Value("${informaactivifolder}")
    private String dirInformaActividades;
    @Value("${plantrabajofolder}")
    private String dirPlanTrabajo;
    @Value("${protocoloacciofolder}")
    private String dirProtocoloAccion;
    @Value("${reglasoperacionfolder}")
    private String dirReglasOperacion;
    @Value("${comitesfile}")
    private String fileComites;
    @Value("${asambleaacciofolder}")
    private String dirAsambleaAccionistas;
    @Value("${asambleaacciofile}")
    private String fileAsambleaAccionistas;
    @Value("${consejofile}")
    private String fileConsejo;
    @Value("${informaactivifile}")
    private String fileInformaActividades;
    @Value("${plantrabajofile}")
    private String filePlanTrabajo;
    @Value("${protocoloacciofile}")
    private String fileProtocoloAccion;
    @Value("${reglasoperacionfile}")
    private String fileReglasOperacion;
    @Value("${presentacionescorpfolder}")
    private String dirPresentacionesCorp;
    @Value("${presentacionescorpfile}")
    private String filePresentacionesCorp;
    @Value("${sagarpafolder}")
    private String dirSagarpa;
    @Value("${comprobantepagosfolder}")
    private String dirComprobatePagos;
    @Value("${depositofolder}")
    private String dirDeposito;
    @Value("${documentosolifolder}")
    private String dirDocumentoSoli;
    @Value("${comprobantepagosfile}")
    private String fileComprobatePagos;
    @Value("${depositofile}")
    private String fileDeposito;
    @Value("${documentosolifile}")
    private String fileDocumentoSoli;
    @Value("${doclegalfolder}")
    private String dirDocLegal;
    @Value("${rfcfoder}")
    private String dirRFC;
    @Value("${fielfolder}")
    private String dirFiel;
    @Value("${sellodigitalfolder}")
    private String dirSelloDigital;
    @Value("${avisoprivacidadfolder}")
    private String dirAvisoPrivacidad;
    @Value("${cumplimientooblifolder}")
    private String dirCumplimientoObliga;
    @Value("${asambleaordinariafolder}")
    private String dirAsambleaOrdinaria;
    @Value("${rfcfile}")
    private String fileRFC;
    @Value("${fielfile}")
    private String fileFiel;
    @Value("${sellodigitalfile}")
    private String fileSelloDigital;
    @Value("${avisoprivacidadfile}")
    private String fileAvisoPrivacidad;
    @Value("${cumplimientooblifile}")
    private String fileCumplimientoObliga;
    @Value("${asambleaordinariafile}")
    private String fileAsambleaOrdinaria;
    @Value("${actaconstitutivafolder}")
    private String dirActaConstitutiva;
    @Value("${poderesfolder}")
    private String dirPoderes;
    @Value("${reformaestatutosfolder}")
    private String dirReformaEstatutos;
    @Value("${escriturasfolder}")
    private String dirEscrituras;
    @Value("${poderesfile}")
    private String filePoderes;
    @Value("${reformaestatutosfile}")
    private String fileReformaEstatutos;
    @Value("${actaconstitutivafile}")
    private String fileActaConstitutiva;
    @Value("${codigoeticafolder}")
    private String dirCodigoEtica;
    @Value("${codigoeticafile}")
    private String fileCodigoEtica;
    @Value("${comiteengordafolder}")
    private String fileComiteEngorda;
    @Value("${comiteengordafolder}")
    private String dirComiteEngorda;
    @Value("${comitecomercialfile}")
    private String fileComiteComercial;
    @Value("${comitecomercialfolder}")
    private String dirComiteComercial;
    @Value("${enlacescorporativosfolder}")
    private String dirEnlacesCorporativos;
    @Value("${enlacescorporativosfile}")
    private String fileEnlacesCorporativos;
    @Value("${convocatoriafolder}")
    private String dirConvocatoria;
    @Value("${convocatoriafile}")
    private String fileConvocatoria;
    @Value("${minutafolder}")
    private String dirMinuta;
    @Value("${minutafile}")
    private String fileMinuta;
    @Value("${ordendiafolder}")
    private String dirOrdenDia;
    @Value("${ordendiafile}")
    private String fileOrdenDia;
    @Value("${presentacioninformacionfolder}")
    private String dirPresentacionInformacion;
    @Value("${presentacioninformacionfile}")
    private String filePresentacionInformacion;
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
    @Value("${convocatoriainversionesfolder}")
    private String dirConvocatoriaInversiones;
    @Value("${convocatoriainversionesfile}")
    private String fileConvocatoriaInversiones;
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
    @Value("${identificacionesfolder}")
    private String diridentificaciones;
    @Value("${identificacionesfile}")
    private String fileIdentificaciones;
    @Value("${comprobantedomiciliofolder}")
    private String dirComprobanteDomicilio;
    @Value("${comprobantefile}")
    private String filecomprobante;
    @Value("${reglasconvocatoriaengordafolder}")
    private String dirConvocatoriaEngordaReglas;
    @Value("${reglasconvocatoriaengordafile}")
    private String fileConvocatoriaEngordaReglas;
    @Value("${reglasminutaengordafolder}")
    private String dirMinutaEngordaReglas;
    @Value("${reglasminutaengordafile}")
    private String fileMinutaEngordaReglas;
    @Value("${reglasordendiaengordafolder}")
    private String dirOrdenDiaEngordaReglas;
    @Value("${reglasordendiaengordafile}")
    private String fileOrdenDiaEngordaReglas;
    @Value("${reglaspresentacioninformacionengordafolder}")
    private String dirPresentacionInformacionEngordaReglas;
    @Value("${reglaspresentacioninformacionengordafile}")
    private String filePresentacionInformacionEngordaReglas;
    @Value("${reglasreglasoperacionengordafolder}")
    private String dirReglasOperacionesEngordaReglas;
    @Value("${reglasreglasoperacionengordafile}")
    private String fileReglasOperacionesEngordaReglas;
    @Value("${reglascurriculummiembrosengordafolder}")
    private String dirCurriculumMiembrosEngordaReglas;
    @Value("${reglascurriculummiembrosengordafile}")
    private String fileCurriculumMiembrosEngordaReglas;
    @Value("${reglascartasconfidengordafolder}")
    private String dirCartasConfidencialidadEngordaReglas;
    @Value("${reglascartasconfidengordafile}")
    private String fileCartasConfidencialidadEngordaReglas;
    @Value("${reglasplananualsesengordafolder}")
    private String dirPlanAnualSesionesEngordaReglas;
    @Value("${reglasplananualsesengordafile}")
    private String filePlanAnualSesionesEngordaReglas;
    @Value("${reglasreportetriconsejofolder}")
    private String dirReporteTrimestralEngordaReglas;
    @Value("${reglasreportetriconsejofile}")
    private String fileReporteTrimestralEngordaReglas;
    @Value("${reglasconvocatoriainversionesfolder}")
    private String dirConvocatoriaInversionesReglas;
    @Value("${reglasconvocatoriainversionesfile}")
    private String fileConvocatoriaInversionesReglas;
    @Value("${reglasminutainversionesfolder}")
    private String dirMinutaInversionesReglas;
    @Value("${reglasminutainversionesfile}")
    private String fileMinutaInversionesReglas;
    @Value("${reglasordendiainversionesfolder}")
    private String dirOrdenDiaInversionesReglas;
    @Value("${reglasordendiainversionesfile}")
    private String fileOrdenDiaInversionesReglas;
    @Value("${reglaspresentacioninformacioninversionesfolder}")
    private String dirPresentacionInformacionInversionesReglas;
    @Value("${reglaspresentacioninformacioninversionesfile}")
    private String filePresentacionInformacionInversionesReglas;
    @Value("${reglasreglasoperacioninversionesfolder}")
    private String dirReglasOperacionesInversionesReglas;
    @Value("${reglasreglasoperacioninversionesfile}")
    private String fileReglasOperacionesInversionesReglas;
    @Value("${reglascurriculummiembrosinversionesfolder}")
    private String dirCurriculumMiembrosInversionesReglas;
    @Value("${reglascurriculummiembrosinversionesfile}")
    private String fileCurriculumMiembrosInversionesReglas;
    @Value("${reglascartasconfidinversionesfolder}")
    private String dirCartasConfidencialidadInversionesReglas;
    @Value("${reglascartasconfidinversionesfile}")
    private String fileCartasConfidencialidadInversionesReglas;
    @Value("${reglasplananualsesinversionesfolder}")
    private String dirPlanAnualSesionesInversionesReglas;
    @Value("${reglasplananualsesinversionesfile}")
    private String filePlanAnualSesionesInversionesReglas;
    @Value("${reglasreportetriconsejoinversionesfolder}")
    private String dirReporteTrimestralInversionesReglas;
    @Value("${reglasreportetriconsejoinversionesfile}")
    private String fileReporteTrimestralInversionesReglas;
    @Value("${reglasconvocatoriacomercialfolder}")
    private String dirConvocatoriaComercialReglas;
    @Value("${reglasconvocatoriacomercialfile}")
    private String fileConvocatoriaComercialReglas;
    @Value("${reglasminutacomercialfolder}")
    private String dirMinutaComercialReglas;
    @Value("${reglasminutacomercialfile}")
    private String fileMinutaComercialReglas;
    @Value("${reglasordendiacomercialfolder}")
    private String dirOrdenDiaComercialReglas;
    @Value("${reglasordendiacomercialfile}")
    private String fileOrdenDiaComercialReglas;
    @Value("${reglaspresentacioninformacioncomercialfolder}")
    private String dirPresentacionInformacionComercialReglas;
    @Value("${reglaspresentacioninformacioncomercialfile}")
    private String filePresentacionInformacionComercialReglas;
    @Value("${reglasreglasoperacioncomercialfolder}")
    private String dirReglasOperacionesComercialReglas;
    @Value("${reglasreglasoperacioncomercialfile}")
    private String fileReglasOperacionesComercialReglas;
    @Value("${reglascurriculummiembroscomercialfolder}")
    private String dirCurriculumMiembrosComercialReglas;
    @Value("${reglascurriculummiembroscomercialfile}")
    private String fileCurriculumMiembrosComercialReglas;
    @Value("${reglascartasconfidcomercialfolder}")
    private String dirCartasConfidencialidadComercialReglas;
    @Value("${reglascartasconfidcomercialfile}")
    private String fileCartasConfidencialidadComercialReglas;
    @Value("${reglasplananualsescomercialfolder}")
    private String dirPlanAnualSesionesComercialReglas;
    @Value("${reglasplananualsescomercialfile}")
    private String filePlanAnualSesionesComercialReglas;
    @Value("${reglasreportetriconsejocomercialfolder}")
    private String dirReporteTrimestralComercialReglas;
    @Value("${reglasreportetriconsejocomercialfile}")
    private String fileReporteTrimestralComercialReglas;
    @Value("${comiteinversionesreglasfolder}")
    private String dirComiteInversionesReglas;
    @Value("${comitecomercialreglasfolder}")
    private String dirComiteComercialReglas;
    @Value("${comiteengordareglasfolder}")
    private String dirComiteEngordaReglas;
    @Value("${reglasconvocatoriafolder}")
    private String dirConvocatoriaReglas;
    @Value("${reglasconvocatoriafile}")
    private String fileConvocatoriaReglas;
    @Value("${reglasminutafolder}")
    private String dirMinutaReglas;
    @Value("${reglasminutafile}")
    private String fileMinutaReglas;
    @Value("${reglasordendiafolder}")
    private String dirOrdenDiaReglas;
    @Value("${reglasordendiafile}")
    private String fileOrdenDiaReglas;
    @Value("${reglaspresentacioninformacionfolder}")
    private String dirPresentacionInformacionReglas;
    @Value("${reglaspresentacioninformacionfile}")
    private String filePresentacionInformacionReglas;
    @Value("${reglasasambleaacciofolder}")
    private String dirAsambleaAccionistasReglas;
    @Value("${reglasasambleaacciofile}")
    private String fileAsambleaAccionistasReglas;
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
    
    @Value("${dirreportesmensualess}")
    private String dirreportesmensualess;
    @Value("${direscritosenviados}")
    private String direscritosenviados;
    @Value("${dirrespuestassagarpa}")
    private String dirrespuestassagarpa;
    @Value("${dirreportesmensualess}")
    private String filereportesmensualess;
    @Value("${fileescritosenviados}")
    private String fileescritosenviados;
    @Value("${filerespuestassagarpa}")
    private String filerespuestassagarpa;
    @Value("${filenuestrogobierno}")
    private String filenuestrogobierno;
    
    
    
    
    

    @Autowired
    FileManager fileManager;
    String downloadDoc;
    String dir;

    @Override
    public void getContratosFirmados(String documento) {
        dir = "";
        String HOME = ConstantsSGD.HOME;
        String directorio = File.separator + dirPrincipal + File.separator + documentosunicos + File.separator;
        dir += directorio;

        switch (documento) {
            case "Financieros":
                dir += dirContratosFirmados + File.separator + dirFinancieros + File.separator;
                downloadDoc = fileFinancieros;
                break;
            case "Provedores":
                dir += dirContratosFirmados + File.separator + dirProvedores + File.separator;
                downloadDoc = fileProvedores;
                break;
            case "Clientes":
                dir += dirContratosFirmados + File.separator + dirCliente + File.separator;
                downloadDoc = fileClientes;
                break;
            case "Personal":
                dir += dirContratosFirmados + File.separator + dirPersonal + File.separator;
                downloadDoc = filePersonal;
                break;
            case "EstudiosEconomicos":
                dir += dirInformacionIndustria + File.separator + dirEstudiosEconomicos + File.separator;
                downloadDoc = fileEstudiosEconomicos;
                break;
            case "PrecioGanadoPie":
                dir += dirInformacionIndustria + File.separator + dirPrecioGanado + File.separator;
                downloadDoc = filePrecioGanado;
                break;
            case "PrecioCarneCanal":
                dir += dirInformacionIndustria + File.separator + dirPrecioCarne + File.separator;
                downloadDoc = filePrecioCarne;
                break;
            case "PrecioAlimento":
                dir += dirInformacionIndustria + File.separator + dirPrecioAlimento + File.separator;
                downloadDoc = filePrecioAlimento;
                break;
            case "AsambleaAccionistas":
                dir += dirGobiernoCorporativo + File.separator + dirAsambleaAccionistas + File.separator;
                downloadDoc = fileAsambleaAccionistas;
                break;
            case "ComiteInversiones":
                dir += dirGobiernoCorporativo + File.separator + dirComites + File.separator;
                downloadDoc = fileComites;
                break;
            case "Consejo":
                dir += dirGobiernoCorporativo + File.separator + dirConsejo + File.separator;
                downloadDoc = fileConsejo;
                break;
            case "ProtocoloAccionistas":
                dir += dirGobiernoCorporativo + File.separator + dirProtocoloAccion + File.separator;
                downloadDoc = fileProtocoloAccion;
                break;
            case "ReglasOperacion":
                dir += dirGobiernoCorporativo + File.separator + dirReglasOperacion + File.separator;
                downloadDoc = fileReglasOperacion;
                break;
            case "PlanTrabajoAnual":
                dir += dirGobiernoCorporativo + File.separator + dirPlanTrabajo + File.separator;
                downloadDoc = filePlanTrabajo;
                break;
            case "InformeActividades":
                dir += dirGobiernoCorporativo + File.separator + dirInformaActividades + File.separator;
                downloadDoc = fileInformaActividades;
                break;
                
                case "NuestroModelo":
                dir += dirGobiernoCorporativo + File.separator + File.separator;
                downloadDoc = filenuestrogobierno;
                break;
            case "RFC":
                dir += dirDocLegal + File.separator + dirRFC + File.separator;
                downloadDoc = fileRFC;
                break;
            case "Fiel":
                dir += dirDocLegal + File.separator + dirFiel + File.separator;
                downloadDoc = fileFiel;
                break;
            case "SelloDigital":
                dir += dirDocLegal + File.separator + dirSelloDigital + File.separator;
                downloadDoc = fileSelloDigital;
                break;
            case "AvisoPrivacidad":
                dir += dirDocLegal + File.separator + dirAvisoPrivacidad + File.separator;
                downloadDoc = fileAvisoPrivacidad;
                break;
            case "CumplimientoObli":
                dir += dirDocLegal + File.separator + dirCumplimientoObliga + File.separator;
                downloadDoc = fileCumplimientoObliga;
                break;
            case "AsambleaOrdinaria":
                dir += dirDocLegal + File.separator + dirAsambleaOrdinaria + File.separator;
                downloadDoc = fileAsambleaOrdinaria;
                break;
            case "ActaConstitutiva":
                dir += dirDocLegal + File.separator + dirEscrituras + File.separator + dirActaConstitutiva + File.separator;
                downloadDoc = fileActaConstitutiva;
                break;
            case "Poderes":
                dir += dirDocLegal + File.separator + dirEscrituras + File.separator + dirPoderes + File.separator;
                downloadDoc = filePoderes;
                break;
            case "CodigoEtica":
                dir += dirGobiernoCorporativo + File.separator + dirCodigoEtica + File.separator;
                downloadDoc = fileCodigoEtica;
                break;
            case "ComiteEngorda":
                dir += dirGobiernoCorporativo + File.separator + dirComiteEngorda + File.separator;
                downloadDoc = fileComiteEngorda;
                break;
            case "ComiteComercial":
                dir += dirGobiernoCorporativo + File.separator + dirComiteComercial + File.separator;
                downloadDoc = fileComiteComercial;
                break;
            case "EnlacesCorpo":
                dir += dirGobiernoCorporativo + File.separator + dirEnlacesCorporativos + File.separator;
                downloadDoc = fileEnlacesCorporativos;
                break;
            case "Identificaciones":
                dir += dirDocLegal + File.separator + diridentificaciones + File.separator;
                downloadDoc = fileIdentificaciones;
                break;
            case "ComprobanteDomicilio":
                dir += dirDocLegal + File.separator + dirComprobanteDomicilio + File.separator;
                downloadDoc = filecomprobante;
                break;
            case "estructuraterreno":
                dir += proyectoejecutivofolder + File.separator + terrenofolder + File.separator;
                downloadDoc = estructuraterrenofile;
                break;
                
                case "avaluoterreno":
                dir += proyectoejecutivofolder + File.separator + terrenofolder + File.separator;
                downloadDoc = avaluoterrenofile;
                break;
                
                 case "proyectoentregado":
                dir += proyectoejecutivofolder + File.separator + proyectofolder + File.separator;
                downloadDoc = proyectoentregadofile;
                break;
                
                case "programanecesidades":
                dir += proyectoejecutivofolder + File.separator + proyectofolder + File.separator;
                downloadDoc = programanecesidadesfile;
                break;
                
                 case "programaparticularnecesidades":
                dir += proyectoejecutivofolder + File.separator + proyectofolder + File.separator;
                downloadDoc = programaparticularfile;
                break;
                
                case "justificacionproyecto":
                dir += proyectoejecutivofolder + File.separator + proyectofolder + File.separator;
                downloadDoc = justificacionproyectofile;
                break;
                   
                case "impactosuelos":
                dir += proyectoejecutivofolder + File.separator + estudiosfolder + File.separator;
                downloadDoc = impactomecanicadesuelosfile;
                break;
                
                 case "impactoambiental":
                dir += proyectoejecutivofolder + File.separator + estudiosfolder + File.separator;
                downloadDoc = impactoambientalfile;
                break;
                
                case "impactovial":
                dir += proyectoejecutivofolder + File.separator + estudiosfolder + File.separator;
                downloadDoc = impactovialfile;
                break;
                   
                case "energiaelectrica":
                dir += proyectoejecutivofolder + File.separator + estudiosfolder + File.separator;
                downloadDoc = energiaelectricafile;
                break;
                
                 case "estudiohidrologico":
                dir += proyectoejecutivofolder + File.separator + estudiosfolder + File.separator;
                downloadDoc = estudiohidrologicofile;
                break;
                
                case "usodesuelo":
                dir += proyectoejecutivofolder + File.separator + estudiosfolder + File.separator;
                downloadDoc = usodesuelofile;
                break;
                
                  case "aguasnacionales":
                dir += proyectoejecutivofolder + File.separator + estudiosfolder + File.separator;
                downloadDoc = concesionaguasfile;
                break;
                
                
                        case "planoregional":
                dir += proyectoejecutivofolder + File.separator + planosfolder + File.separator;
                downloadDoc = planoregionalfile;
                break;
                   
                case "planorural":
                dir += proyectoejecutivofolder + File.separator + planosfolder + File.separator;
                downloadDoc = planoruralfile;
                break;
                
                 case "planotopografico":
                dir += proyectoejecutivofolder + File.separator + planosfolder + File.separator;
                downloadDoc = planotopograficofile;
                break;
                
                case "ubicacionterreno":
                dir += proyectoejecutivofolder + File.separator + planosfolder + File.separator;
                downloadDoc = ubicacionterrenofile;
                break;
                
                  case "planosinfraestructura":
                dir += proyectoejecutivofolder + File.separator + planosfolder + File.separator;
                downloadDoc = planosinfraestructurafile;
                break;
                
                   case "tarjetaejecutiva":
                dir += presentacionesfolder + File.separator + tarjetaejecutivafolder + File.separator;
                downloadDoc = tarjetaejecutivafile;
                break;
                
                   case "proyectoespanol":
                dir += presentacionesfolder + File.separator + justificacionespanolfolder + File.separator;
                downloadDoc = justificacionespanolfile;
                break;
                
                   case "proyectoingles":
                dir += presentacionesfolder + File.separator + justificacioninglesfolder + File.separator;
                downloadDoc = justificacioninglesfile;
                break;
                
                
        }
        String path = HOME + File.separator + dirPrincipal + File.separator + documentosunicos + File.separator + dir + File.separator;
        System.out.println("PATH " + dir + "  " + downloadDoc);

        try {
            DocumentosActivosEntity activosEntity = repository.findByRutaAndNombre(dir, downloadDoc);
            if (activosEntity != null) {
                fileManager.downloadFile(activosEntity, true);
            } else {
                throw new AJAXException("Archivo no Encontrado en db");
            }
        } catch (IOException  e) {
            Logger.getLogger(ContratosFirmadosServiceImpl.class.getName()).log(Level.SEVERE, null, e);
            throw new AJAXException(e.getMessage());
        }
    }

    @Override
    public void getDocumentosAnio(String documento, String year) {
        dir = "";
        String HOME = ConstantsSGD.HOME;
        String directorio = File.separator + dirPrincipal + File.separator + documentosunicos + File.separator;
        dir += directorio;
        switch (documento) {

            case "PresentacionesCorp":
                dir += dirPresentacionesCorp + File.separator + year + File.separator;
                downloadDoc = filePresentacionesCorp + "-" + year + ".pdf";
                break;
            case "DocumentoSolicitud":
                dir += dirSagarpa + File.separator + dirDocumentoSoli + File.separator;
                downloadDoc = fileDocumentoSoli + "-" + year + ".pdf";
                break;
            case "Deposito":
                dir += dirSagarpa + File.separator + dirDeposito + File.separator;
                downloadDoc = fileDeposito + "-" + year + ".pdf";
                break;
            case "ComprobatePagos":
                dir += dirSagarpa + File.separator + dirComprobatePagos + File.separator;
                downloadDoc = fileComprobatePagos + "-" + year + ".pdf";
                break;
              case "MensualesSagarpa":
                dir += dirSagarpa + File.separator + dirreportesmensualess + File.separator;
                downloadDoc = filereportesmensualess + "-" + year + ".pdf";
                break;
            case "EscritosEnviados":
                dir += dirSagarpa + File.separator + direscritosenviados + File.separator;
                downloadDoc = fileescritosenviados + "-" + year + ".pdf";
                break;
            case "RespuestasSagarpa":
                dir += dirSagarpa + File.separator + dirrespuestassagarpa + File.separator;
                downloadDoc = filerespuestassagarpa + "-" + year + ".pdf";
                break;            
                        
                        
                        
                        
                        
            case "Convocatoria":
                dir += dirGobiernoCorporativo + File.separator + dirAsambleaAccionistas + File.separator + dirConvocatoria + File.separator;
                downloadDoc = fileConvocatoria + "-" + year + ".pdf";
                break;
            case "OrdenDia":
                dir += dirGobiernoCorporativo + File.separator + dirAsambleaAccionistas + File.separator + dirOrdenDia + File.separator;
                downloadDoc = fileOrdenDia + "-" + year + ".pdf";
                break;
            case "PresenInforma":
                dir += dirGobiernoCorporativo + File.separator + dirAsambleaAccionistas + File.separator + dirPresentacionInformacion + File.separator;
                downloadDoc = filePresentacionInformacion + "-" + year + ".pdf";
                break;
            case "Minuta":
                dir += dirGobiernoCorporativo + File.separator + dirAsambleaAccionistas + File.separator + dirMinuta + File.separator;
                downloadDoc = fileMinuta + "-" + year + ".pdf";
                break;
            case "ConvocatoriaEngorda":
                dir += dirGobiernoCorporativo + File.separator + dirComiteEngorda + File.separator + dirConvocatoriaEngorda + File.separator;
                downloadDoc = fileConvocatoriaEngorda + "-" + year + ".pdf";
                break;
            case "OrdenDiaEngorda":
                dir += dirGobiernoCorporativo + File.separator + dirComiteEngorda + File.separator + dirOrdenDiaEngorda + File.separator;
                downloadDoc = fileOrdenDiaEngorda + "-" + year + ".pdf";
                break;
            case "PresenInforEngorda":
                dir += dirGobiernoCorporativo + File.separator + dirComiteEngorda + File.separator + dirPresentacionInformacionEngorda + File.separator;
                downloadDoc = filePresentacionInformacionEngorda + "-" + year + ".pdf";
                break;
            case "MinutaEngorda":
                dir += dirGobiernoCorporativo + File.separator + dirComiteEngorda + File.separator + dirMinutaEngorda + File.separator;
                downloadDoc = fileMinutaEngorda + "-" + year + ".pdf";
                break;
            case "ReglasOpEngorda":
                dir += dirGobiernoCorporativo + File.separator + dirComiteEngorda + File.separator + dirReglasOperacionesEngorda + File.separator;
                downloadDoc = fileReglasOperacionesEngorda;
                break;
            case "CurriMiemborsEngorda":
                dir += dirGobiernoCorporativo + File.separator + dirComiteEngorda + File.separator + dirCurriculumMiembrosEngorda + File.separator;
                downloadDoc = fileCurriculumMiembrosEngorda;
                break;
            case "CartasConfiEngorda":
                dir += dirGobiernoCorporativo + File.separator + dirComiteEngorda + File.separator + dirCartasConfidencialidadEngorda + File.separator;
                downloadDoc = fileCartasConfidencialidadEngorda;
                break;
            case "PlanAnualSesEngorda":
                dir += dirGobiernoCorporativo + File.separator + dirComiteEngorda + File.separator + dirPlanAnualSesionesEngorda + File.separator;
                downloadDoc = filePlanAnualSesionesEngorda;
                break;
            case "ReportesTriEngorda":
                dir += dirGobiernoCorporativo + File.separator + dirComiteEngorda + File.separator + dirReporteTrimestralEngorda + File.separator;
                downloadDoc = fileReporteTrimestralEngorda;
                break;
            case "ConvocatoriaInversiones":
                dir += dirGobiernoCorporativo + File.separator + dirComites + File.separator + dirConvocatoriaInversiones + File.separator;
                downloadDoc = fileConvocatoriaInversiones + "-" + year + ".pdf";
                break;
            case "OrdenDiaInversiones":
                dir += dirGobiernoCorporativo + File.separator + dirComites + File.separator + dirOrdenDiaInversiones + File.separator;
                downloadDoc = fileOrdenDiaInversiones + "-" + year + ".pdf";
                break;
            case "PresenInforInversiones":
                dir += dirGobiernoCorporativo + File.separator + dirComites + File.separator + dirPresentacionInformacionInversiones + File.separator;
                downloadDoc = filePresentacionInformacionInversiones + "-" + year + ".pdf";
                break;
            case "MinutaInversiones":
                dir += dirGobiernoCorporativo + File.separator + dirComites + File.separator + dirMinutaInversiones + File.separator;
                downloadDoc = fileMinutaInversiones + "-" + year + ".pdf";
                break;
            case "ReglasOpInversiones":
                dir += dirGobiernoCorporativo + File.separator + dirComites + File.separator + dirReglasOperacionesInversiones + File.separator;
                downloadDoc = fileReglasOperacionesInversiones;
                break;
            case "CurriMiemborsInversiones":
                dir += dirGobiernoCorporativo + File.separator + dirComites + File.separator + dirCurriculumMiembrosInversiones + File.separator;
                downloadDoc = fileCurriculumMiembrosInversiones;
                break;
            case "CartasConfiInversiones":
                dir += dirGobiernoCorporativo + File.separator + dirComites + File.separator + dirCartasConfidencialidadInversiones + File.separator;
                downloadDoc = fileCartasConfidencialidadInversiones;
                break;
            case "PlanAnualSesInversiones":
                dir += dirGobiernoCorporativo + File.separator + dirComites + File.separator + dirPlanAnualSesionesInversiones + File.separator;
                downloadDoc = filePlanAnualSesionesInversiones;
                break;
            case "ReportesTriInversiones":
                dir += dirGobiernoCorporativo + File.separator + dirComites + File.separator + dirReporteTrimestralInversiones + File.separator;
                downloadDoc = fileReporteTrimestralInversiones;
                break;

            case "ConvocatoriaComercial":
                dir += dirGobiernoCorporativo + File.separator + dirComiteComercial + File.separator + dirConvocatoriaComercial + File.separator;
                downloadDoc = fileConvocatoriaComercial + "-" + year + ".pdf";
                break;
            case "OrdenDiaComercial":
                dir += dirGobiernoCorporativo + File.separator + dirComiteComercial + File.separator + dirOrdenDiaComercial + File.separator;
                downloadDoc = fileOrdenDiaComercial + "-" + year + ".pdf";
                break;
            case "PresenInforComercial":
                dir += dirGobiernoCorporativo + File.separator + dirComiteComercial + File.separator + dirPresentacionInformacionComercial + File.separator;
                downloadDoc = filePresentacionInformacionComercial + "-" + year + ".pdf";
                break;
            case "MinutaComercial":
                dir += dirGobiernoCorporativo + File.separator + dirComiteComercial + File.separator + dirMinutaComercial + File.separator;
                downloadDoc = fileMinutaComercial + "-" + year + ".pdf";
                break;
            case "ReglasOpComercial":
                dir += dirGobiernoCorporativo + File.separator + dirComiteComercial + File.separator + dirReglasOperacionesComercial + File.separator;
                downloadDoc = fileReglasOperacionesComercial;
                break;
            case "CurriMiemborsComercial":
                dir += dirGobiernoCorporativo + File.separator + dirComiteComercial + File.separator + dirCurriculumMiembrosComercial + File.separator;
                downloadDoc = fileCurriculumMiembrosComercial;
                break;
            case "CartasConfiComercial":
                dir += dirGobiernoCorporativo + File.separator + dirComiteComercial + File.separator + dirCartasConfidencialidadComercial + File.separator;
                downloadDoc = fileCartasConfidencialidadComercial;
                break;
            case "PlanAnualSesComercial":
                dir += dirGobiernoCorporativo + File.separator + dirComiteComercial + File.separator + dirPlanAnualSesionesComercial + File.separator;
                downloadDoc = filePlanAnualSesionesComercial;
                break;
            case "ReportesTriComercial":
                dir += dirGobiernoCorporativo + File.separator + dirComiteComercial + File.separator + dirReporteTrimestralComercial + File.separator;
                downloadDoc = fileReporteTrimestralComercial;
                break;
            case "ConvocatoriaEngordaR":
                dir += dirGobiernoCorporativo + File.separator + dirReglasOperacion + File.separator + dirComiteEngordaReglas + File.separator + dirConvocatoriaEngordaReglas + File.separator;
                downloadDoc = fileConvocatoriaEngordaReglas + "-" + year + ".pdf";
                break;
            case "OrdenDiaEngordaR":
                dir += dirGobiernoCorporativo + File.separator + dirReglasOperacion + File.separator + dirComiteEngordaReglas + File.separator + dirOrdenDiaEngordaReglas + File.separator;
                downloadDoc = fileOrdenDiaEngordaReglas + "-" + year + ".pdf";
                break;
            case "PresenInforEngordaR":
                dir += dirGobiernoCorporativo + File.separator + dirReglasOperacion + File.separator + dirComiteEngordaReglas + File.separator + dirPresentacionInformacionEngordaReglas + File.separator;
                downloadDoc = filePresentacionInformacionEngordaReglas + "-" + year + ".pdf";
                break;
            case "MinutaEngordaR":
                dir += dirGobiernoCorporativo + File.separator + dirReglasOperacion + File.separator + dirComiteEngordaReglas + File.separator + dirMinutaEngordaReglas + File.separator;
                downloadDoc = fileMinutaEngordaReglas + "-" + year + ".pdf";
                break;
            case "ReglasOpEngordaR":
                dir += dirGobiernoCorporativo + File.separator + dirReglasOperacion + File.separator + dirComiteEngordaReglas + File.separator + dirReglasOperacionesEngordaReglas + File.separator;
                downloadDoc = fileReglasOperacionesEngordaReglas;
                break;
            case "CurriMiemborsEngordaR":
                dir += dirGobiernoCorporativo + File.separator + dirReglasOperacion + File.separator + dirComiteEngordaReglas + File.separator + dirCurriculumMiembrosEngordaReglas + File.separator;
                downloadDoc = fileCurriculumMiembrosEngordaReglas;
                break;
            case "CartasConfiEngordaR":
                dir += dirGobiernoCorporativo + File.separator + dirReglasOperacion + File.separator + dirComiteEngordaReglas + File.separator + dirCartasConfidencialidadEngordaReglas + File.separator;
                downloadDoc = fileCartasConfidencialidadEngordaReglas;
                break;
            case "PlanAnualSesEngordaR":
                dir += dirGobiernoCorporativo + File.separator + dirReglasOperacion + File.separator + dirComiteEngordaReglas + File.separator + dirPlanAnualSesionesEngordaReglas + File.separator;
                downloadDoc = filePlanAnualSesionesEngordaReglas;
                break;
            case "ReportesTriEngordaR":
                dir += dirGobiernoCorporativo + File.separator + dirReglasOperacion + File.separator + dirComiteEngordaReglas + File.separator + dirReporteTrimestralEngordaReglas + File.separator;
                downloadDoc = fileReporteTrimestralEngordaReglas;
                break;
            case "ConvocatoriaInversionesR":
                dir += dirGobiernoCorporativo + File.separator + dirReglasOperacion + File.separator + dirComiteInversionesReglas + File.separator + dirConvocatoriaInversionesReglas + File.separator;
                downloadDoc = fileConvocatoriaInversionesReglas + "-" + year + ".pdf";
                break;
            case "OrdenDiaInversionesR":
                dir += dirGobiernoCorporativo + File.separator + dirReglasOperacion + File.separator + dirComiteInversionesReglas + File.separator + dirOrdenDiaInversionesReglas + File.separator;
                downloadDoc = fileOrdenDiaInversionesReglas + "-" + year + ".pdf";
                break;
            case "PresenInforInversionesR":
                dir += dirGobiernoCorporativo + File.separator + dirReglasOperacion + File.separator + dirComiteInversionesReglas + File.separator + dirPresentacionInformacionInversionesReglas + File.separator;
                downloadDoc = filePresentacionInformacionInversionesReglas + "-" + year + ".pdf";
                break;
            case "MinutaInversionesR":
                dir += dirGobiernoCorporativo + File.separator + dirReglasOperacion + File.separator + dirComiteInversionesReglas + File.separator + dirMinutaInversionesReglas + File.separator;
                downloadDoc = fileMinutaInversionesReglas + "-" + year + ".pdf";
                break;
            case "ReglasOpInversionesR":
                dir += dirGobiernoCorporativo + File.separator + dirReglasOperacion + File.separator + dirComiteInversionesReglas + File.separator + dirReglasOperacionesInversionesReglas + File.separator;
                downloadDoc = fileReglasOperacionesInversionesReglas;
                break;
            case "CurriMiemborsInversionesR":
                dir += dirGobiernoCorporativo + File.separator + dirReglasOperacion + File.separator + dirComiteInversionesReglas + File.separator + dirCurriculumMiembrosInversionesReglas + File.separator;
                downloadDoc = fileCurriculumMiembrosInversionesReglas;
                break;
            case "CartasConfiInversionesR":
                dir += dirGobiernoCorporativo + File.separator + dirReglasOperacion + File.separator + dirComiteInversionesReglas + File.separator + dirCartasConfidencialidadInversionesReglas + File.separator;
                downloadDoc = fileCartasConfidencialidadInversionesReglas;
                break;
            case "PlanAnualSesInversionesR":
                dir += dirGobiernoCorporativo + File.separator + dirReglasOperacion + File.separator + dirComiteInversionesReglas + File.separator + dirPlanAnualSesionesInversionesReglas + File.separator;
                downloadDoc = filePlanAnualSesionesInversionesReglas;
                break;
            case "ReportesTriInversionesR":
                dir += dirGobiernoCorporativo + File.separator + dirReglasOperacion + File.separator + dirComiteInversionesReglas + File.separator + dirReporteTrimestralInversionesReglas + File.separator;
                downloadDoc = fileReporteTrimestralInversionesReglas;
                break;

            case "ConvocatoriaComercialR":
                dir += dirGobiernoCorporativo + File.separator + dirConsejo + File.separator + dirConvocatoriaComercialReglas + File.separator;
                downloadDoc = fileConvocatoriaComercialReglas + "-" + year + ".pdf";
                break;
            case "OrdenDiaComercialR":
                dir += dirGobiernoCorporativo + File.separator + dirConsejo + File.separator + dirOrdenDiaComercialReglas + File.separator;
                downloadDoc = fileOrdenDiaComercialReglas + "-" + year + ".pdf";
                break;
            case "PresenInforComercialR":
                dir += dirGobiernoCorporativo + File.separator + dirConsejo + File.separator + dirPresentacionInformacionComercialReglas + File.separator;
                downloadDoc = filePresentacionInformacionComercialReglas + "-" + year + ".pdf";
                break;
            case "MinutaComercialR":
                dir += dirGobiernoCorporativo + File.separator + dirConsejo + File.separator + dirMinutaComercialReglas + File.separator;
                downloadDoc = fileMinutaComercialReglas + "-" + year + ".pdf";
                break;
            case "ReglasOpComercialR":
                dir += dirGobiernoCorporativo + File.separator + dirConsejo  + File.separator + dirReglasOperacionesComercialReglas + File.separator;
                downloadDoc = fileReglasOperacionesComercialReglas;
                break;
            case "CurriMiemborsComercialR":
                dir += dirGobiernoCorporativo + File.separator + dirConsejo + File.separator + dirCurriculumMiembrosComercialReglas + File.separator;
                downloadDoc = fileCurriculumMiembrosComercialReglas;
                break;
            case "CartasConfiComercialR":
                dir += dirGobiernoCorporativo + File.separator + dirConsejo + File.separator + dirCartasConfidencialidadComercialReglas + File.separator;
                downloadDoc = fileCartasConfidencialidadComercialReglas;
                break;
            case "PlanAnualSesComercialR":
                dir += dirGobiernoCorporativo + File.separator + dirConsejo + File.separator + dirPlanAnualSesionesComercialReglas + File.separator;
                downloadDoc = filePlanAnualSesionesComercialReglas;
                break;
            case "ReportesTriComercialR":
                dir += dirGobiernoCorporativo + File.separator + dirConsejo + File.separator + dirReporteTrimestralComercialReglas + File.separator;
                downloadDoc = fileReporteTrimestralComercialReglas;
                break;
            case "ConvocatoriaR":
                dir += dirGobiernoCorporativo + File.separator + dirReglasOperacion + File.separator + dirAsambleaAccionistasReglas + File.separator + dirConvocatoriaReglas + File.separator;
                downloadDoc = fileConvocatoriaReglas + "-" + year + ".pdf";
                break;
            case "OrdenDiaR":
                dir += dirGobiernoCorporativo + File.separator + dirReglasOperacion + File.separator + dirAsambleaAccionistasReglas + File.separator + dirOrdenDiaReglas + File.separator;
                downloadDoc = fileOrdenDia + "-" + year + ".pdf";
                break;
            case "PresenInformaR":
                dir += dirGobiernoCorporativo + File.separator + dirReglasOperacion + File.separator + dirAsambleaAccionistasReglas + File.separator + dirPresentacionInformacionReglas + File.separator;
                downloadDoc = filePresentacionInformacion + "-" + year + ".pdf";
                break;
            case "MinutaR":
                dir += dirGobiernoCorporativo + File.separator + dirReglasOperacion + File.separator + dirAsambleaAccionistasReglas + File.separator + dirMinutaReglas + File.separator;
                downloadDoc = fileMinuta + "-" + year + ".pdf";

        }
        String path = HOME + File.separator + dirPrincipal + File.separator + documentosunicos + File.separator + dir;
        System.out.println("PATH " + dir + "  " + downloadDoc);

        try {
            DocumentosActivosEntity activosEntity = repository.findByRutaAndNombre(dir, downloadDoc);
            if (activosEntity != null) {
                fileManager.downloadFile(activosEntity, true);
            } else {
                throw new AJAXException("Archivo no Encontrado en db");
            }
        } catch (IOException  e) {
            Logger.getLogger(ContratosFirmadosServiceImpl.class.getName()).log(Level.SEVERE, null, e);
            throw new AJAXException(e.getMessage());
        }

    }

    @Override
    public void getDocumentosMes(String documento, String year) {
        String dir = "";
        String HOME = ConstantsSGD.HOME;
        String directorio = File.separator + dirPrincipal + File.separator + documentosunicos + File.separator;
        dir += directorio;
        dir = dirGobiernoCorporativo + File.separator + dirInformaActividades + File.separator + year;
        downloadDoc = documento + "-" + year + ".pdf";
        String path = HOME + File.separator + dirPrincipal + File.separator + documentosunicos + File.separator + dir;
        System.out.println("PATH " + path + "  " + downloadDoc);

        DocumentosActivosEntity activosEntity = repository.findByRutaAndNombre(path, downloadDoc);
        try {
            if (activosEntity != null) {
                fileManager.downloadFile(activosEntity, true);
            } else {
                throw new AJAXException("Archivo no Encontrado en db");
            }
        } catch (IOException  e) {
            Logger.getLogger(ContratosFirmadosServiceImpl.class.getName()).log(Level.SEVERE, null, e);
            throw new AJAXException(e.getMessage());
        }

    }
    
    @Autowired
    private NombresRepository nombresRepository;
    
    @Override
    public List<NombresEntity> getnombres(String vista){
        List<NombresEntity> nombres = nombresRepository.findByVista(vista);        
        return nombres;
    }
}
