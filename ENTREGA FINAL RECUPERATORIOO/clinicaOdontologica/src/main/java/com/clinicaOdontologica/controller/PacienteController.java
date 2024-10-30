package com.clinicaOdontologica.controller;

import com.clinicaOdontologica.dto.PacienteDto;
import com.clinicaOdontologica.exceptions.ResourceInternalServerErrorException;
import com.clinicaOdontologica.exceptions.ResourceNotFoundException;
import com.clinicaOdontologica.service.impl.PacienteServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/pacientes")
public class PacienteController {
    private PacienteServiceImpl pacienteService;

    @Autowired
    public PacienteController(PacienteServiceImpl pacienteService) {
        this.pacienteService = pacienteService;
    }

    @Operation(summary = "Save patient")
    @PostMapping("/new")
    public ResponseEntity<?> guardar(@RequestBody PacienteDto pacienteDto) throws ResourceInternalServerErrorException {
        pacienteService.guardar(pacienteDto);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @Operation(summary = "Search patient by id")
    @GetMapping("/{id}")
    public ResponseEntity<?> buscar(@PathVariable Long id) throws ResourceNotFoundException {
        PacienteDto pacienteDto = pacienteService.buscar(id);
        return new ResponseEntity<>(pacienteDto, HttpStatus.OK);
    }

    @Operation(summary = "Update patient")
    @PutMapping("/update")
    public ResponseEntity<?> actualizar(@RequestBody PacienteDto pacienteDto) throws ResourceNotFoundException {
        pacienteService.actualizar(pacienteDto);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @Operation(summary = "List all patient")
    @GetMapping("/listarTodos")
    public List<PacienteDto> listarTodos(){
        return pacienteService.listarTodos();
    }

    @Operation(summary = "Delete patient")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) throws ResourceNotFoundException {
        pacienteService.eliminar(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

}

