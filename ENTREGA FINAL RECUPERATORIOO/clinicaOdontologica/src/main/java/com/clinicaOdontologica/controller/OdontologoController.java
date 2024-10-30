package com.clinicaOdontologica.controller;

import com.clinicaOdontologica.dto.OdontologoDto;
import com.clinicaOdontologica.exceptions.ResourceInternalServerErrorException;
import com.clinicaOdontologica.exceptions.ResourceNotFoundException;
import com.clinicaOdontologica.service.impl.OdontologoServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/odontologos")
public class OdontologoController {
    private OdontologoServiceImpl odontologoService;

    @Autowired
    public OdontologoController(OdontologoServiceImpl odontologoService) {
        this.odontologoService = odontologoService;
    }

    @Operation(summary = "Save dentist")
    @PostMapping("/new")
    public ResponseEntity<?> guardar(@RequestBody OdontologoDto odontologoDto) throws ResourceInternalServerErrorException {
        odontologoService.guardar(odontologoDto);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @Operation(summary = "Search dentist by id")
    @GetMapping("/{id}")
    public ResponseEntity<?> buscar(@PathVariable Long id) throws ResourceNotFoundException {
        OdontologoDto odontologoDto = odontologoService.buscar(id);
        return new ResponseEntity<>(odontologoDto, HttpStatus.OK);
    }

    @Operation(summary = "Update dentist")
    @PutMapping("/update")
    public ResponseEntity<?> actualizar(@RequestBody OdontologoDto odontologoDto) throws ResourceNotFoundException{
        odontologoService.actualizar(odontologoDto);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @Operation(summary = "List all dentist")
    @GetMapping("/listarTodos")
    public List<OdontologoDto> listarTodos(){
        return odontologoService.listarTodos();
    }

    @Operation(summary = "Delete dentist")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) throws ResourceNotFoundException {
        odontologoService.eliminar(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
