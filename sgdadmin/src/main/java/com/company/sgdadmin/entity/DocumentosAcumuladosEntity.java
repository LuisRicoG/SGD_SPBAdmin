/**
 *
 */
package com.company.sdg.mvc.entity;

import java.sql.Timestamp;
import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author the_d
 *
 */
@Entity
@Table(name = "documentos_acumulados")
public class DocumentosAcumuladosEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "documento_id")
    public Integer documentoId;
    public String descripcion;
    public String ruta;
    public Integer pantalla;
    public Integer usuario_id;
    public Timestamp fecha;
    public Integer estatus;
    public String nombre;

    public Integer getDocumentoId() {
        return documentoId;
    }

    public void setDocumentoId(Integer documentoId) {
        this.documentoId = documentoId;
    }

    /**
     * @return the ruta
     */
    public String getRuta() {
        return ruta;
    }

    /**
     * @param ruta the ruta to set
     */
    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    /**
     * @return the usuario_id
     */
    public Integer getUsuario_id() {
        return usuario_id;
    }

    /**
     * @param usuario_id the usuario_id to set
     */
    public void setUsuario_id(Integer usuario_id) {
        this.usuario_id = usuario_id;
    }

    /**
     * @return the fecha
     */
    public Timestamp getFecha() {
        return fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(Timestamp fecha) {
        this.fecha = fecha;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getPantalla() {
        return pantalla;
    }

    public void setPantalla(Integer pantalla) {
        this.pantalla = pantalla;
    }

    public Integer getEstatus() {
        return estatus;
    }

    public void setEstatus(Integer estatus) {
        this.estatus = estatus;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}
