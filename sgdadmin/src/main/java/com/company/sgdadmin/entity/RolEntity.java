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
