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
@Table(name="rol")
public class RolEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer rol_id;
	public Integer usuario_id;
	public String nombre;
	/**
	 * @return the rol_id
	 */
	public Integer getRol_id() {
		return rol_id;
	}
	/**
	 * @param rol_id the rol_id to set
	 */
	public void setRol_id(Integer rol_id) {
		this.rol_id = rol_id;
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
	
}
