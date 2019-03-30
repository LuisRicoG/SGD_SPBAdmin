/**
 * 
 */
package com.company.sgdadmin.entity;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Query;
import javax.persistence.Table;

/**
 * @author the_d
 *
 */
@Entity
@Table(name="documentos_activos")
public class DocumentosActivosEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer documento_id;
	public String nombre;
	public String ruta;
	public Integer usuario_id;
	public Timestamp fecha;
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

}
