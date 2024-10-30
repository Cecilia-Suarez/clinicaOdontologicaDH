package com.clinicaOdontologica.service.impl;

import com.clinicaOdontologica.dto.TurnoDto;
import com.clinicaOdontologica.exceptions.ResourceInternalServerErrorException;
import com.clinicaOdontologica.exceptions.ResourceNotFoundException;
import com.clinicaOdontologica.model.Turno;
import com.clinicaOdontologica.repository.IOdontologoRepository;
import com.clinicaOdontologica.repository.IPacienteRepository;
import com.clinicaOdontologica.repository.ITurnoRepository;
import com.clinicaOdontologica.service.ITurnoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TurnoServiceImpl implements ITurnoService {
    private ITurnoRepository repository;

    private IPacienteRepository pacienteRepository;
    private IOdontologoRepository odontologoRepository;

    private static final Logger LOGGER =  Logger.getLogger(OdontologoServiceImpl.class);


    @Autowired
    public TurnoServiceImpl(ITurnoRepository repository, IPacienteRepository pacienteRepository,
                            IOdontologoRepository odontologoRepository) {
        this.repository = repository;
        this.pacienteRepository = pacienteRepository;
        this.odontologoRepository = odontologoRepository;
    }

    @Autowired
    ObjectMapper mapper;

    @Override
    public void guardar(TurnoDto turnoDto) throws ResourceInternalServerErrorException {
        try {
            Turno turno = mapper.convertValue(turnoDto, Turno.class);
            LOGGER.info("Guardando Turno con id: " + turnoDto.getId());
            repository.save(turno);
            LOGGER.info("Turno guardado con éxito.");
        } catch (Exception e){
            throw new ResourceInternalServerErrorException("Error al guardar Turno");
        }
    }

    @Override
    public List<TurnoDto> listarTodos() {
        List<Turno> turnos = repository.findAllTurnoOrdered();
        List<TurnoDto> turnoDtos = new ArrayList<>();
        for (Turno turno: turnos){
            turnoDtos.add(mapper.convertValue(turno, TurnoDto.class));
        }
        return turnoDtos;
    }

    @Override
    public void eliminar(Long id) throws ResourceNotFoundException{
        LOGGER.info("Eliminando al Turno: " + id);
        if (buscar(id) == null){
            throw new ResourceNotFoundException("No existe el Turno que intenta eliminar: " + id);
        }
        repository.deleteById(id);
        LOGGER.info("Turno eliminado con éxito: " + id);
    }

    @Override
    public void actualizar(TurnoDto turnoDto) throws ResourceNotFoundException {
        LOGGER.info("Actualizando Turno con id: " + turnoDto.getId());
        if (!repository.findById(turnoDto.getId()).isPresent()) {
            throw new ResourceNotFoundException("No existe el Turno solicitado: " + turnoDto.getId());
        }
        Turno turnoExistente = repository.findById(turnoDto.getId()).get();
        turnoExistente.setDate(turnoDto.getDate());
        if (pacienteRepository.findById(turnoDto.getPaciente().getId()) == null) {
            throw new ResourceNotFoundException(
                    "No existe el Paciente con ID: " + turnoDto.getPaciente().getId());
        }

        if (odontologoRepository.findById(turnoDto.getOdontologo().getId()) == null) {
            throw new ResourceNotFoundException(
                    "No existe el Odontólogo con ID: " + turnoDto.getOdontologo().getId());
        }
        turnoExistente.setPaciente(pacienteRepository.findById(turnoDto.getPaciente().getId()).get());
        turnoExistente.setOdontologo(odontologoRepository.findById(turnoDto.getOdontologo().getId()).get());

        repository.save(turnoExistente);

        LOGGER.info("Turno actualizado con éxito.");
    }

    @Override
    public TurnoDto buscar(Long id) throws ResourceNotFoundException {
        LOGGER.info("Buscando Turno con el ID: " + id);
        Optional<Turno> turno = repository.findById(id);
        if (!turno.isPresent()){
            throw new ResourceNotFoundException("No existe el Turno solicitado: " + id);
        }
        LOGGER.info("Turno encontrado.");
        return mapper.convertValue(turno, TurnoDto.class);
    }

}
