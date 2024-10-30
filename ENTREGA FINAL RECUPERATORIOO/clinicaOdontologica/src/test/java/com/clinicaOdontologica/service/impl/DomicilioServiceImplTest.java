package com.clinicaOdontologica.service.impl;

import com.clinicaOdontologica.model.Domicilio;
import com.clinicaOdontologica.service.IDomicilioService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class DomicilioServiceImplTest {

    IDomicilioService domicilioService;

    @Autowired
    public DomicilioServiceImplTest(IDomicilioService domicilioService) {
        this.domicilioService = domicilioService;
    }

    @Test
    public void guardarYListarTodosTest() {
        domicilioService.guardar(new Domicilio("Los Pinos", "1233", "Malvin", "Montevideo"));
        domicilioService.guardar(new Domicilio("Avda. Las Instrucciones del Año 1913", "1234", "Las Piedras", "Canelones"));
        domicilioService.guardar(new Domicilio("Colonia", "1303", "Centro", "Montevideo"));

        assertEquals(3, domicilioService.listarTodos().size());
    }

    @Test
    void buscarTest() {
        assertTrue(domicilioService.buscar(2L) != null);
    }

    @Test
    void eliminarTest() {
        domicilioService.eliminar(3L);
        assertEquals(2, domicilioService.listarTodos().size());
    }

    @Test
    void actualizarTest() {
        Optional<Domicilio> domicilio1 = domicilioService.buscar(1L);
        assertEquals("Los Pinos", domicilio1.get().getCalle());

        domicilioService.actualizar(new Domicilio(1L,"Candelaria", "1233", "Malvin", "Montevideo"));
        domicilio1 = domicilioService.buscar(1L);
        assertEquals("Candelaria", domicilio1.get().getCalle());
    }
}