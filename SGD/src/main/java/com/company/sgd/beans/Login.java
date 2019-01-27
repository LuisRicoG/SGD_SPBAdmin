/**
 * 
 */
package com.company.sgd.beans;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

/**
 * @author the_d
 *
 */
public class Login {
	
	private Integer usuarioid;
	private String nombre1;
	private String nombre2;
	private String apellidopaterno;
	private String apellidomaterno;
	private String correoelectronico;
	private String telefono;
	private String estatus;
	
	@Email(message="Ingresa tu usuario")
	@NotEmpty(message="Campo requerido")
	@Length(min=20,max=50,message="Longitud de usuario no permitida")
	private String usuario;
	
	@NotEmpty(message="Ingresa tu contraseña")
	@Length(min=8,max=20,message="Longitud de password no permitida")
	@Pattern(regexp="[a-z]{8,20}")
	private String contrasena;

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
	 * @return the apellidopaterno
	 */
	public String getApellidopaterno() {
		return apellidopaterno;
	}

	/**
	 * @param apellidopaterno the apellidopaterno to set
	 */
	public void setApellidopaterno(String apellidopaterno) {
		this.apellidopaterno = apellidopaterno;
	}

	/**
	 * @return the apellidomaterno
	 */
	public String getApellidomaterno() {
		return apellidomaterno;
	}

	/**
	 * @param apellidomaterno the apellidomaterno to set
	 */
	public void setApellidomaterno(String apellidomaterno) {
		this.apellidomaterno = apellidomaterno;
	}

	/**
	 * @return the correoelectronico
	 */
	public String getCorreoelectronico() {
		return correoelectronico;
	}

	/**
	 * @param correoelectronico the correoelectronico to set
	 */
	public void setCorreoelectronico(String correoelectronico) {
		this.correoelectronico = correoelectronico;
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
	public String getEstatus() {
		return estatus;
	}

	/**
	 * @param estatus the estatus to set
	 */
	public void setEstatus(String estatus) {
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
	 * @param contrasena the contrasena to set
	 */
	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}
	
}
