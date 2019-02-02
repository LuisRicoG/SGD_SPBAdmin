/**
 * 
 */
package com.company.sgdadmin.entity;

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
@Table(name="parametros")
public class ParametrosEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer parametro_id;
	public String descripcion;
	public String valor;
	/**
	 * @return the parametro_id
	 */
	public Integer getParametro_id() {
		return parametro_id;
	}
	/**
	 * @param parametro_id the parametro_id to set
	 */
	public void setParametro_id(Integer parametro_id) {
		this.parametro_id = parametro_id;
	}
	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}
	/**
	 * @param descripcion the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	/**
	 * @return the valor
	 */
	public String getValor() {
		return valor;
	}
	/**
	 * @param valor the valor to set
	 */
	public void setValor(String valor) {
		this.valor = valor;
	}
	
}
