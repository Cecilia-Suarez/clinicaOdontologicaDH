package com.clinicaOdontologica.model;

import javax.persistence.*;


import java.time.LocalDate;

@Entity
@Table(name = "Turnos")
public class Turno {


    @Id
    @SequenceGenerator(name = "turnos_seq", sequenceName = "turnos_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "turnos_seq")
    private Long id;


    @ManyToOne
    @JoinColumn(name = "paciente_id", referencedColumnName = "id", nullable = false)
    private Paciente paciente;


    @ManyToOne
    @JoinColumn(name = "odontologo_id", referencedColumnName = "id", nullable = false)
    private Odontologo odontologo;


    @Column(name = "Fecha", nullable = false)
    private LocalDate date;

    public Turno() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Odontologo getOdontologo() {
        return odontologo;
    }

    public void setOdontologo(Odontologo odontologo) {
        this.odontologo = odontologo;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Turno{" +
                "id=" + id +
                ", paciente=" + paciente +
                ", odontologo=" + odontologo +
                ", date=" + date +
                '}';
    }
}

