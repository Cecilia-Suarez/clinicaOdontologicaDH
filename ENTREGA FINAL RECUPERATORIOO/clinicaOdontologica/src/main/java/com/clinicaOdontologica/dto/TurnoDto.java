package com.clinicaOdontologica.dto;

import com.clinicaOdontologica.model.Odontologo;
import com.clinicaOdontologica.model.Paciente;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDate;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TurnoDto {

    private Long id;
    private Paciente paciente;
    private Odontologo odontologo;
    private LocalDate date;

    public TurnoDto() {
    }

    public TurnoDto(Paciente paciente, Odontologo odontologo, LocalDate date) {
        this.paciente = paciente;
        this.odontologo = odontologo;
        this.date = date;
    }

    public TurnoDto(Long id, Paciente paciente, Odontologo odontologo, LocalDate date) {
        this.id = id;
        this.paciente = paciente;
        this.odontologo = odontologo;
        this.date = date;
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
}
