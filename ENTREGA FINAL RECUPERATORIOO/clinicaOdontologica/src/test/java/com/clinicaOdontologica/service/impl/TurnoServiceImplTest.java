package com.clinicaOdontologica.service.impl;

import com.clinicaOdontologica.dto.OdontologoDto;
import com.clinicaOdontologica.dto.PacienteDto;
import com.clinicaOdontologica.dto.TurnoDto;
import com.clinicaOdontologica.exceptions.ResourceInternalServerErrorException;
import com.clinicaOdontologica.exceptions.ResourceNotFoundException;
import com.clinicaOdontologica.model.Domicilio;
import com.clinicaOdontologica.model.Odontologo;
import com.clinicaOdontologica.model.Paciente;
import com.clinicaOdontologica.service.IOdontologoService;
import com.clinicaOdontologica.service.IPacienteService;
import com.clinicaOdontologica.service.ITurnoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class TurnoServiceImplTest {

    ITurnoService turnoService;
    IPacienteService pacienteService;

    IOdontologoService odontologoService;

    @Autowired
    public TurnoServiceImplTest(ITurnoService turnoService, IPacienteService pacienteService, IOdontologoService odontologoService) {
        this.turnoService = turnoService;
        this.pacienteService = pacienteService;
        this.odontologoService = odontologoService;
    }

    @Test
    void guardarYListarTodosTest() throws ResourceInternalServerErrorException {
        pacienteService.guardar(new PacienteDto("Emilia", "Suárez", "123", LocalDate.of(2023,01,01), new Domicilio("Colón", "111", "Salinas", "Canelones")));
        pacienteService.guardar(new PacienteDto("Valeria", "Suárez", "456", LocalDate.of(2023,02,02), new Domicilio("Minas", "111", "Centro", "Montevideo")));
        pacienteService.guardar(new PacienteDto("Cecilia", "Suárez", "789", LocalDate.of(2023,01,01), new Domicilio("Candelaria", "111", "Malvin", "Montevideo")));

        odontologoService.guardar(new OdontologoDto(123, "Joaquín", "Suarez"));
        odontologoService.guardar(new OdontologoDto(456, "Candela", "Suarez"));
        odontologoService.guardar(new OdontologoDto(789, "Augusto", "Rodriguez"));


        turnoService.guardar(new TurnoDto(new Paciente(1L, null, null, null, null, null), new Odontologo(2L, 0, null, null), LocalDate.of(2023, 12, 01)));
        turnoService.guardar(new TurnoDto(new Paciente(2L, null, null, null, null, null), new Odontologo(1L, 0, null, null), LocalDate.of(2023, 12, 01)));
        turnoService.guardar(new TurnoDto(new Paciente(3L, null, null, null, null, null), new Odontologo(3L, 0, null, null), LocalDate.of(2023, 12, 01)));


        assertEquals(3, turnoService.listarTodos().size());

    }

    @Test
    void eliminarTest() throws ResourceNotFoundException {
        turnoService.eliminar(3L);
        assertEquals(2, turnoService.listarTodos().size());
    }

    @Test
    void actualizarTest() throws ResourceNotFoundException {
        TurnoDto turno1 = turnoService.buscar(1L);
        assertEquals(LocalDate.of(2023, 12, 01), turno1.getDate());

        turnoService.actualizar(new TurnoDto(1L, new Paciente(1L, null, null, null, null, null), new Odontologo(2L, 0, null, null), LocalDate.of(2023, 10, 31)));
        turno1 = turnoService.buscar(1L);
        assertEquals(LocalDate.of(2023, 10, 31), turno1.getDate());
    }

    @Test
    void buscarTest() throws ResourceNotFoundException {
        assertTrue(turnoService.buscar(2L) != null);
    }
}