package com.clinicaOdontologica.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;


import java.util.Set;

@Entity
@Table(name = "Odontologos")
public class Odontologo {
    @Id
    @SequenceGenerator(name = "odontologos_seq", sequenceName = "odontologos_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "odontologos_seq")

    private Long id;


    @Column(name = "Matricula", unique = true, nullable = false)
    private int matricula;

    @Column(name = "Nombre", nullable = false)
    private String nombre;


    @Column(name = "Apellido", nullable = false)
    private String apellido;

    @OneToMany(mappedBy = "odontologo")
    @JsonIgnore
    private Set<Turno> turnos;

    public Odontologo() {
    }

    public Odontologo(Long id, int matricula, String nombre, String apellido) {
        this.id = id;
        this.matricula = matricula;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public Odontologo(int matricula, String nombre, String apellido) {
        this.matricula = matricula;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    @Override
    public String toString() {
        return "Odontólogo (id=" + id + ")" + ", matricula:" + matricula + ", " + nombre + " " + apellido;
    }
}
