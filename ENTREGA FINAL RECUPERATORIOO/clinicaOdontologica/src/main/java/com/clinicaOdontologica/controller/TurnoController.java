package com.clinicaOdontologica.controller;

import com.clinicaOdontologica.dto.TurnoDto;
import com.clinicaOdontologica.exceptions.ResourceInternalServerErrorException;
import com.clinicaOdontologica.exceptions.ResourceNotFoundException;
import com.clinicaOdontologica.service.impl.OdontologoServiceImpl;
import com.clinicaOdontologica.service.impl.PacienteServiceImpl;
import com.clinicaOdontologica.service.impl.TurnoServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Set;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/turnos")
public class TurnoController {

    TurnoServiceImpl turnoService;
    PacienteServiceImpl pacienteService;
    OdontologoServiceImpl odontologoService;

    @Autowired
    public TurnoController(TurnoServiceImpl turnoService, PacienteServiceImpl pacienteService, OdontologoServiceImpl odontologoService) {
        this.turnoService = turnoService;
        this.pacienteService = pacienteService;
        this.odontologoService = odontologoService;
    }

    @Operation(summary = "Save turn")
    @PostMapping("/new")
    public ResponseEntity<?> guardar(@RequestBody TurnoDto turnoDto) throws ResourceInternalServerErrorException{
        turnoService.guardar(turnoDto);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @Operation(summary = "Search turn by id")
    @GetMapping("/{id}")
    public ResponseEntity<?> buscar(@PathVariable Long id) throws ResourceNotFoundException{
        TurnoDto turnoDto = turnoService.buscar(id);
        return new ResponseEntity<>(turnoDto, HttpStatus.OK);
    }

    @Operation(summary = "Update turn")
    @PutMapping("/update")
    public ResponseEntity<?> actualizar(@RequestBody TurnoDto turnoDto) throws ResourceNotFoundException{
        turnoService.actualizar(turnoDto);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @Operation(summary = "List all turn")
    @GetMapping("/listarTodos")
    public List<TurnoDto> listarTodos(){
        return turnoService.listarTodos();
    }

    @Operation(summary = "Delete turn")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) throws ResourceNotFoundException{
        turnoService.eliminar(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

}
