package com.company.sgdadmin.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author the_d
 */

@Entity
@Table(name="gobcorpnombres")
public class NombresEntity {
    @Id
    @Column(name="nombre_id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public Integer nombreid;
    public String nombre;
    public String cargo;
    public String vista;

    public Integer getNombreid() {
        return nombreid;
    }

    public void setNombreid(Integer nombreid) {
        this.nombreid = nombreid;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getVista() {
        return vista;
    }

    public void setVista(String vista) {
        this.vista = vista;
    }
    
}
