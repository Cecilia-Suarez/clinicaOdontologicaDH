package com.clinicaOdontologica.service;

import com.clinicaOdontologica.dto.PacienteDto;
import com.clinicaOdontologica.exceptions.ResourceInternalServerErrorException;
import com.clinicaOdontologica.exceptions.ResourceNotFoundException;

import java.util.List;
import java.util.Set;

public interface IPacienteService {
    public void guardar(PacienteDto PacienteDto) throws ResourceInternalServerErrorException;
    public PacienteDto buscar(Long id) throws ResourceNotFoundException;
    public List<PacienteDto> listarTodos();
    public void eliminar(Long id) throws ResourceNotFoundException;
    public void actualizar(PacienteDto pacienteDto) throws ResourceNotFoundException;

}

