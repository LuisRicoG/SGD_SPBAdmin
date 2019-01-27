/**
 * 
 */
package com.company.sdg.mvc.beans;

import java.sql.Timestamp;

/**
 * @author the_d
 *
 */
public class DocumentosActivos {
	private Integer documentoid;
	private String nombre;
	private String ruta;
	private Integer usuarioid;
	private Timestamp fecha;
	/**
	 * @return the documentoid
	 */
	public Integer getDocumentoid() {
		return documentoid;
	}
	/**
	 * @param documentoid the documentoid to set
	 */
	public void setDocumentoid(Integer documentoid) {
		this.documentoid = documentoid;
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
	 * @return the usuarioid
	 */
	public Integer getUsuarioid() {
		return usuarioid;
	}
	/**
	 * @param usuarioid the usuarioid to set
	 */
	public void setUsuarioid(Integer usuarioid) {
		this.usuarioid = usuarioid;
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
