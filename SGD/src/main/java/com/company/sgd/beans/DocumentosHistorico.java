/**
 * 
 */
package com.company.sdg.mvc.beans;

import java.sql.Timestamp;

/**
 * @author the_d
 *
 */
public class DocumentosHistorico {
	private Integer documentoid;
	private String nombre;
	private String ruta;
	private Integer usuarioid;
	private Timestamp fechainicial;
	private Timestamp fechacambio;
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
	 * @return the fechainicial
	 */
	public Timestamp getFechainicial() {
		return fechainicial;
	}
	/**
	 * @param fechainicial the fechainicial to set
	 */
	public void setFechainicial(Timestamp fechainicial) {
		this.fechainicial = fechainicial;
	}
	/**
	 * @return the fechacambio
	 */
	public Timestamp getFechacambio() {
		return fechacambio;
	}
	/**
	 * @param fechacambio the fechacambio to set
	 */
	public void setFechacambio(Timestamp fechacambio) {
		this.fechacambio = fechacambio;
	}
	
}
