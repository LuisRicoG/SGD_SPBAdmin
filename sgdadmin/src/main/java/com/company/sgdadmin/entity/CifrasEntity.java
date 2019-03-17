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
@Table(name="cifras")
public class CifrasEntity {
    @Id
    @Column(name="cifra_id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public Integer cifraid;
    public Double inmuebles;
    public Double maqyeq;
    public Double eqtrans;
    public Double mobiliario;
    public Double eqcomputo;

    public Integer getCifraid() {
        return cifraid;
    }

    public void setCifraid(Integer cifraid) {
        this.cifraid = cifraid;
    }

    public Double getInmuebles() {
        return inmuebles;
    }

    public void setInmuebles(Double inmuebles) {
        this.inmuebles = inmuebles;
    }

    public Double getMaqyeq() {
        return maqyeq;
    }

    public void setMaqyeq(Double maqyeq) {
        this.maqyeq = maqyeq;
    }

    public Double getEqtrans() {
        return eqtrans;
    }

    public void setEqtrans(Double eqtrans) {
        this.eqtrans = eqtrans;
    }

    public Double getMobiliario() {
        return mobiliario;
    }

    public void setMobiliario(Double mobiliario) {
        this.mobiliario = mobiliario;
    }

    public Double getEqcomputo() {
        return eqcomputo;
    }

    public void setEqcomputo(Double eqcomputo) {
        this.eqcomputo = eqcomputo;
    }
    
}
