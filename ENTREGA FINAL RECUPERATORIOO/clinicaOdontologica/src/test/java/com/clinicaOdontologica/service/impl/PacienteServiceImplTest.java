package com.clinicaOdontologica.service.impl;

import com.clinicaOdontologica.dto.PacienteDto;
import com.clinicaOdontologica.exceptions.ResourceInternalServerErrorException;
import com.clinicaOdontologica.exceptions.ResourceNotFoundException;
import com.clinicaOdontologica.model.Domicilio;
import com.clinicaOdontologica.service.IPacienteService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class PacienteServiceImplTest {

    IPacienteService pacienteService;

    @Autowired
    public PacienteServiceImplTest(IPacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    @Test
    void guardarYListarTodosTest() throws ResourceInternalServerErrorException {
        pacienteService.guardar(new PacienteDto("Emilia", "Suárez", "123", LocalDate.of(2023,01,01), new Domicilio("Colón", "111", "Salinas", "Canelones")));
        pacienteService.guardar(new PacienteDto("Valeria", "Suárez", "456", LocalDate.of(2023,02,02), new Domicilio("Minas", "111", "Centro", "Montevideo")));
        pacienteService.guardar(new PacienteDto("Cecilia", "Suárez", "789", LocalDate.of(2023,01,01), new Domicilio("Candelaria", "111", "Malvin", "Montevideo")));

        assertEquals(3, pacienteService.listarTodos().size());
    }

    @Test
    void buscarTest() throws ResourceNotFoundException {
        assertTrue(pacienteService.buscar(2L) != null);
    }

    @Test
    void eliminarTest() throws ResourceNotFoundException {
        pacienteService.eliminar(3L);
        assertEquals(2, pacienteService.listarTodos().size());
    }

    @Test
    void actualizarTest() throws ResourceNotFoundException {
        PacienteDto paciente1 = pacienteService.buscar(1L);
        assertEquals("Emilia", paciente1.getNombre());

        pacienteService.actualizar(new PacienteDto(1L, "Candela", "Suárez", "123", LocalDate.of(2023,01,01), new Domicilio("Colón", "111", "Salinas", "Canelones")));
        paciente1 = pacienteService.buscar(1L);
        assertEquals("Candela", paciente1.getNombre());
    }

    @Test
    void actualizarDomicilioDesdePacienteTest() throws ResourceNotFoundException {
        PacienteDto paciente1 = pacienteService.buscar(2L);
        assertEquals("Minas", paciente1.getDomicilio().getCalle());

        pacienteService.actualizar(new PacienteDto(2L, "Valeria", "Suárez", "456", LocalDate.of(2023,02,02), new Domicilio("Rivera", "111", "Centro", "Montevideo")));
        paciente1 = pacienteService.buscar(2L);
        assertEquals("Rivera", paciente1.getDomicilio().getCalle());
    }
}