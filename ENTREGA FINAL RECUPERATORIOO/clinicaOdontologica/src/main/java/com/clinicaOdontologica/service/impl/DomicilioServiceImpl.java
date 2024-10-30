package com.clinicaOdontologica.service.impl;

import com.clinicaOdontologica.model.Domicilio;
import com.clinicaOdontologica.repository.IDomicilioRepository;
import com.clinicaOdontologica.service.IDomicilioService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DomicilioServiceImpl implements IDomicilioService {
    private static final Logger LOGGER = Logger.getLogger(DomicilioServiceImpl.class);
    private IDomicilioRepository repository;

    @Autowired
    public DomicilioServiceImpl(IDomicilioRepository repository) {
        this.repository = repository;
    }

    @Override
    public void guardar(Domicilio domicilio) {
        repository.save(domicilio);
        DomicilioServiceImpl.LOGGER.info("Guardando Domicilio");
    }

    @Override
    public Optional<Domicilio> buscar(Long id) {
        DomicilioServiceImpl.LOGGER.info("Buscando Domicilio");
        return repository.findById(id);
    }

    @Override
    public List<Domicilio> listarTodos() {
        DomicilioServiceImpl.LOGGER.info("Listando todos los Domicilios");
        return repository.findAll();
    }

    @Override
    public void eliminar(Long id) {
        repository.deleteById(id);
        DomicilioServiceImpl.LOGGER.info("Eliminando al Domicilio");
    }

    @Override
    public Domicilio actualizar(Domicilio domicilio) {
        DomicilioServiceImpl.LOGGER.info("Actualizando Domicilio");
        return repository.save(domicilio);
    }
}
