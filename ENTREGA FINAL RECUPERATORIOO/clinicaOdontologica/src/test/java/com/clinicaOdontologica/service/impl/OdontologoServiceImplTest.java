package com.clinicaOdontologica.service.impl;

import com.clinicaOdontologica.dto.OdontologoDto;
import com.clinicaOdontologica.exceptions.ResourceInternalServerErrorException;
import com.clinicaOdontologica.exceptions.ResourceNotFoundException;
import com.clinicaOdontologica.service.IOdontologoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class OdontologoServiceImplTest {
    IOdontologoService odontologoService;

    @Autowired
    public OdontologoServiceImplTest(IOdontologoService odontologoService) {
        this.odontologoService = odontologoService;
    }


    @Test
    public void guardarYListarTodosTest() throws ResourceInternalServerErrorException {
        odontologoService.guardar(new OdontologoDto(123, "Joaquín", "Suarez"));
        odontologoService.guardar(new OdontologoDto(456, "Candela", "Suarez"));
        odontologoService.guardar(new OdontologoDto(789, "Augusto", "Rodriguez"));

        assertEquals(3, odontologoService.listarTodos().size());
    }

    @Test
    void buscarTest() throws ResourceNotFoundException {
        assertTrue(odontologoService.buscar(2L) != null);
    }

    @Test
    void eliminarTest() throws ResourceNotFoundException {
        odontologoService.eliminar(3L);
        assertEquals(2, odontologoService.listarTodos().size());
    }

    @Test
    void actualizarTest() throws ResourceNotFoundException {
        OdontologoDto odontologo1 = odontologoService.buscar(1L);
        assertEquals("Joaquín", odontologo1.getNombre());

        odontologoService.actualizar(new OdontologoDto(1L, 123, "Cecilia", "Suarez"));
        odontologo1 = odontologoService.buscar(1L);
        assertEquals("Cecilia", odontologo1.getNombre());
    }
}