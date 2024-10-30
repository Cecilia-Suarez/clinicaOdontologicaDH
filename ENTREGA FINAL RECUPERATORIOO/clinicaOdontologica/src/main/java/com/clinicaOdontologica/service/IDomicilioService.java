package com.clinicaOdontologica.service;

import com.clinicaOdontologica.model.Domicilio;

import java.util.List;
import java.util.Optional;

public interface IDomicilioService {
    public void guardar(Domicilio domicilio);
    public Optional<Domicilio> buscar(Long id);
    public List<Domicilio> listarTodos();
    public void eliminar(Long id);
    public Domicilio actualizar(Domicilio domicilio);

}
