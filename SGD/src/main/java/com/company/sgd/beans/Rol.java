/**
 * 
 */
package com.company.sdg.mvc.beans;

/**
 * @author the_d
 *
 */
public class Rol {
	private Integer rolid;
	private Integer usuarioid;
	private String nombre;
	/**
	 * @return the rolid
	 */
	public Integer getRolid() {
		return rolid;
	}
	/**
	 * @param rolid the rolid to set
	 */
	public void setRolid(Integer rolid) {
		this.rolid = rolid;
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
