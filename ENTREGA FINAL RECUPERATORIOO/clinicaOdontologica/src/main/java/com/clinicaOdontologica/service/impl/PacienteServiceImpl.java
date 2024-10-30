package com.clinicaOdontologica.service.impl;

import com.clinicaOdontologica.dto.PacienteDto;
import com.clinicaOdontologica.exceptions.ResourceInternalServerErrorException;
import com.clinicaOdontologica.exceptions.ResourceNotFoundException;
import com.clinicaOdontologica.model.Paciente;
import com.clinicaOdontologica.repository.IPacienteRepository;
import com.clinicaOdontologica.service.IPacienteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PacienteServiceImpl implements IPacienteService {

    private IPacienteRepository repository;

    private static final Logger LOGGER =  Logger.getLogger(PacienteServiceImpl.class);

    @Autowired
    public PacienteServiceImpl(IPacienteRepository repository) {
        this.repository = repository;
    }

    @Autowired
    ObjectMapper mapper;


    @Override
    public void guardar(PacienteDto pacienteDto) throws ResourceInternalServerErrorException{
        try {
            Paciente paciente = mapper.convertValue(pacienteDto, Paciente.class);
            LOGGER.info("Guardando Paciente con id: " + pacienteDto.getId());
            repository.save(paciente);
            LOGGER.info("Paciente guardado con éxito.");
        } catch (Exception e){
            throw new ResourceInternalServerErrorException("Error al guardar Paciente");
        }
    }

    @Override
    public PacienteDto buscar(Long id) throws ResourceNotFoundException{
        LOGGER.info("Buscando Paciente con el ID: " + id);
        Optional<Paciente> paciente = repository.findById(id);
        if (!paciente.isPresent()){
            throw new ResourceNotFoundException("No existe el Paciente solicitado: " + id);
        }
        LOGGER.info("Paciente encontrado.");
        return mapper.convertValue(paciente, PacienteDto.class);
    }

    @Override
    public List<PacienteDto> listarTodos(){
        LOGGER.info("Listando todos los Pacientes.");
        List<Paciente> pacientes = repository.findAllPacienteOrdered();
        List<PacienteDto> pacienteDto = new ArrayList<>();
        for (Paciente paciente : pacientes) {
            pacienteDto.add(mapper.convertValue(paciente, PacienteDto.class));
        }
        return pacienteDto;
    }

    @Override
    public void eliminar(Long id) throws ResourceNotFoundException {
        LOGGER.info("Eliminando al Paciente: " + id);
        if (buscar(id) == null){
            throw new ResourceNotFoundException("No existe el Paciente que intenta eliminar: " + id);
        }
        repository.deleteById(id);
        LOGGER.info("Paciente eliminado con éxito: " + id);
    }

    @Override
    public void actualizar(PacienteDto pacienteDto) throws ResourceNotFoundException{
        Paciente paciente = mapper.convertValue(pacienteDto, Paciente.class);
        LOGGER.info("Actualizando Paciente con id: " + pacienteDto.getId());
        if (buscar(pacienteDto.getId()) == null){
            throw new ResourceNotFoundException("No existe el Paciente que intenta actualizar: " + pacienteDto.getId());
        }
        repository.save(paciente);
        LOGGER.info("Paciente actualizado con éxito.");
    }

}
