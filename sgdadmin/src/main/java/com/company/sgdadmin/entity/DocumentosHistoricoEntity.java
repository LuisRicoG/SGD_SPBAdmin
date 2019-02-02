/**
 * 
 */
package com.company.sgdadmin.entity;

import java.sql.Timestamp;

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
@Table(name="documentos_historico")
public class DocumentosHistoricoEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer documento_id;
	public String nombre;
	public String ruta;
	public Integer usuario_id;
	public Timestamp fecha_inicial;
	public Timestamp fecha_cambio;
	/**
	 * @return the documento_id
	 */
	public Integer getDocumento_id() {
		return documento_id;
	}
	/**
	 * @param documento_id the documento_id to set
	 */
	public void setDocumento_id(Integer documento_id) {
		this.documento_id = documento_id;
	}
	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}
	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
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
	 * @return the fecha_inicial
	 */
	public Timestamp getFecha_inicial() {
		return fecha_inicial;
	}
	/**
	 * @param fecha_inicial the fecha_inicial to set
	 */
	public void setFecha_inicial(Timestamp fecha_inicial) {
		this.fecha_inicial = fecha_inicial;
	}
	/**
	 * @return the fecha_cambio
	 */
	public Timestamp getFecha_cambio() {
		return fecha_cambio;
	}
	/**
	 * @param fecha_cambio the fecha_cambio to set
	 */
	public void setFecha_cambio(Timestamp fecha_cambio) {
		this.fecha_cambio = fecha_cambio;
	}
	
}
