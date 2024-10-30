package com.clinicaOdontologica.service;

import com.clinicaOdontologica.dto.OdontologoDto;
import com.clinicaOdontologica.exceptions.ResourceInternalServerErrorException;
import com.clinicaOdontologica.exceptions.ResourceNotFoundException;

import java.util.List;
import java.util.Set;

public interface IOdontologoService {
    public void guardar(OdontologoDto OdontologoDto) throws ResourceInternalServerErrorException;
    public List<OdontologoDto> listarTodos();
    public OdontologoDto buscar(Long id) throws ResourceNotFoundException;
    public void eliminar (Long id) throws ResourceNotFoundException;
    public void actualizar(OdontologoDto OdontologoDto) throws ResourceNotFoundException;

}
