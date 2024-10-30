package com.clinicaOdontologica.service;

import com.clinicaOdontologica.dto.TurnoDto;
import com.clinicaOdontologica.exceptions.ResourceInternalServerErrorException;
import com.clinicaOdontologica.exceptions.ResourceNotFoundException;

import java.util.List;
import java.util.Set;

public interface ITurnoService {
    public void guardar(TurnoDto TurnoDto) throws ResourceInternalServerErrorException;

    public List<TurnoDto> listarTodos();

    public TurnoDto buscar(Long id) throws ResourceNotFoundException;

    public void eliminar(Long id) throws ResourceNotFoundException;

    public void actualizar(TurnoDto TurnoDto) throws ResourceNotFoundException;

}
