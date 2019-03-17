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
@Table(name="permisos")
public class PermisosEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer permiso_id;
	public Integer rol_id;
	public String ruta;
	/**
	 * @return the permiso_id
	 */
	public Integer getPermiso_id() {
		return permiso_id;
	}
	/**
	 * @param permiso_id the permiso_id to set
	 */
	public void setPermiso_id(Integer permiso_id) {
		this.permiso_id = permiso_id;
	}
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
	
}
