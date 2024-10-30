package com.clinicaOdontologica.service.impl;

import com.clinicaOdontologica.dto.OdontologoDto;
import com.clinicaOdontologica.exceptions.ResourceInternalServerErrorException;
import com.clinicaOdontologica.exceptions.ResourceNotFoundException;
import com.clinicaOdontologica.model.Odontologo;
import com.clinicaOdontologica.repository.IOdontologoRepository;
import com.clinicaOdontologica.service.IOdontologoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OdontologoServiceImpl implements IOdontologoService {
    private IOdontologoRepository repository;

    private static final Logger LOGGER =  Logger.getLogger(OdontologoServiceImpl.class);

    @Autowired
    public OdontologoServiceImpl(IOdontologoRepository repository) {
        this.repository = repository;
    }

    @Autowired
    ObjectMapper mapper;

    @Override
    public void guardar(OdontologoDto odontologoDto) throws ResourceInternalServerErrorException{
        try {
            Odontologo odontologo = mapper.convertValue(odontologoDto, Odontologo.class);
            LOGGER.info("Guardando Odontólogo con id: " + odontologoDto.getId());
            repository.save(odontologo);
            LOGGER.info("Odontólogo guardado con éxito.");
        } catch (Exception e){
            throw new ResourceInternalServerErrorException("Error al guardar Odontólogo");
        }
    }

    @Override
    public List<OdontologoDto> listarTodos() {
        LOGGER.info("Listando todos los Odontólogos.");
        List<Odontologo> odontologos = repository.findAll();
        List<OdontologoDto> odontologoDTOS = new ArrayList<>();
        for (Odontologo odontologo : odontologos) {
            odontologoDTOS.add(mapper.convertValue(odontologo, OdontologoDto.class));
        }
        return odontologoDTOS;
    }

    @Override
    public OdontologoDto buscar(Long id) throws ResourceNotFoundException{
        LOGGER.info("Buscando Odontólogo con el ID: " + id);
        Optional<Odontologo> odontologo = repository.findById(id);
        if (!odontologo.isPresent()) {
            throw new ResourceNotFoundException("No existe el Odontólogo solicitado: " + id);
        }
        LOGGER.info("Odontólogo encontrado.");
        return mapper.convertValue(odontologo, OdontologoDto.class);
    }

    @Override
    public void eliminar (Long id) throws ResourceNotFoundException {
        LOGGER.info("Eliminando al Odontólogo: " + id);
        if (buscar(id) == null){
            throw new ResourceNotFoundException("No existe el Odontólogo que intenta eliminar: " + id);
        }
        repository.deleteById(id);
        LOGGER.info("Odontólogo eliminado con éxito: " + id);
    }


    @Override
    public void actualizar(OdontologoDto odontologoDto) throws ResourceNotFoundException {
        Odontologo odontologo = mapper.convertValue(odontologoDto, Odontologo.class);
        LOGGER.info("Actualizando Odontólogo con id: " + odontologoDto.getId());
        if (buscar(odontologoDto.getId()) == null){
            throw new ResourceNotFoundException("No existe el Odontólogo que intenta actualizar: " + odontologoDto.getId());
        }
        repository.save(odontologo);
        LOGGER.info("Odontólogo actualizado con éxito.");
    }
}
