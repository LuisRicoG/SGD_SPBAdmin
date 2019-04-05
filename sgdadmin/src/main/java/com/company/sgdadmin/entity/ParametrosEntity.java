package com.company.sgdadmin.entity;

/**
 * 
 */

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
@Table(name="parametros")
public class ParametrosEntity {
	@Id
        @Column(name="parametro_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer parametroid;
	public String descripcion;
	public String valor;
	/**
	 * @return the parametro_id
	 */
	public Integer getParametroid() {
		return parametroid;
	}
	/**
	 * @param parametro_id the parametro_id to set
	 */
	public void setParametroid(Integer parametroid) {
		this.parametroid = parametroid;
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
