/**
 * 
 */
package com.company.sgdadmin.entity;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;
/**
 * @author the_d
 *
 */
@Entity
@Table(name="usuario")
public class LoginEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer usuario_id;
	public String nombre1;
	public String nombre2;
	public String apellido_paterno;
	public String apellido_materno;
	public String correo_electronico;
	public String telefono;
	public Integer estatus;
	public String usuario;
	public String contrasena;
        public Integer rol_id;
	
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
	 * @return the nombre1
	 */
	public String getNombre1() {
		return nombre1;
	}
	/**
	 * @param nombre1 the nombre1 to set
	 */
	public void setNombre1(String nombre1) {
		this.nombre1 = nombre1;
	}
	/**
	 * @return the nombre2
	 */
	public String getNombre2() {
		return nombre2;
	}
	/**
	 * @param nombre2 the nombre2 to set
	 */
	public void setNombre2(String nombre2) {
		this.nombre2 = nombre2;
	}
	/**
	 * @return the apellido_paterno
	 */
	public String getApellido_paterno() {
		return apellido_paterno;
	}
	/**
	 * @param apellido_paterno the apellido_paterno to set
	 */
	public void setApellido_paterno(String apellido_paterno) {
		this.apellido_paterno = apellido_paterno;
	}
	/**
	 * @return the apellido_materno
	 */
	public String getApellido_materno() {
		return apellido_materno;
	}
	/**
	 * @param apellido_materno the apellido_materno to set
	 */
	public void setApellido_materno(String apellido_materno) {
		this.apellido_materno = apellido_materno;
	}
	/**
	 * @return the correo_electronico
	 */
	public String getCorreo_electronico() {
		return correo_electronico;
	}
	/**
	 * @param correo_electronico the correo_electronico to set
	 */
	public void setCorreo_electronico(String correo_electronico) {
		this.correo_electronico = correo_electronico;
	}
	/**
	 * @return the telefono
	 */
	public String getTelefono() {
		return telefono;
	}
	/**
	 * @param telefono the telefono to set
	 */
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	/**
	 * @return the estatus
	 */
	public Integer getEstatus() {
		return estatus;
	}
	/**
	 * @param estatus the estatus to set
	 */
	public void setEstatus(Integer estatus) {
		this.estatus = estatus;
	}
	/**
	 * @return the usuario
	 */
	public String getUsuario() {
		return usuario;
	}
	/**
	 * @param usuario the usuario to set
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	/**
	 * @return the contrasena
	 */
	public String getContrasena() {
		return contrasena;
	}
	/**
	 * @param contrase�a the contrasena to set
	 */
	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}
        
        public Integer getRol_id() {
            return rol_id;
        }

        public void setRol_id(Integer rol_id) {
            this.rol_id = rol_id;
        }
}
