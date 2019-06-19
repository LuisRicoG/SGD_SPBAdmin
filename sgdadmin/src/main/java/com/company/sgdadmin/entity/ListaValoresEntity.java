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
@Table(name="lista_de_valores")
public class ListaValoresEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer valor_id;
	public String descripcion;
	public String nombre_lista;
	/**
	 * @return the valor_id
	 */
	public Integer getValor_id() {
		return valor_id;
	}
	/**
	 * @param valor_id the valor_id to set
	 */
	public void setValor_id(Integer valor_id) {
		this.valor_id = valor_id;
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
	 * @return the nombre_lista
	 */
	public String getNombre_lista() {
		return nombre_lista;
	}
	/**
	 * @param nombre_lista the nombre_lista to set
	 */
	public void setNombre_lista(String nombre_lista) {
		this.nombre_lista = nombre_lista;
	}
	
}
