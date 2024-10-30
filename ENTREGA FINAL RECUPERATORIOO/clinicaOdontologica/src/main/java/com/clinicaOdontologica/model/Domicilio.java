package com.clinicaOdontologica.model;

import javax.persistence.*;


@Entity
@Table(name = "Domicilios")
public class Domicilio {



    @Id
    @SequenceGenerator(name = "domicilios_seq", sequenceName = "domicilios_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "domicilios_seq")
    private Long id;

    @Column(name = "Calle")
    private String calle;

    @Column(name = "Numero")
    private String numero;

    @Column(name = "Localidad")
    private String localidad;

    @Column(name = "Provincia")
    private String provincia;

    public Domicilio() {
    }

    public Domicilio(Long id, String calle, String numero, String localidad, String provincia) {
        this.id = id;
        this.calle = calle;
        this.numero = numero;
        this.localidad = localidad;
        this.provincia = provincia;
    }
    public Domicilio( String calle, String numero, String localidad, String provincia) {
        this.calle = calle;
        this.numero = numero;
        this.localidad = localidad;
        this.provincia = provincia;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    @Override
    public String toString() {
        return "Domicilio" + "(id=" + id + ") " + calle + " " + numero + ", " + localidad + ", " + provincia + ".";
    }
}
