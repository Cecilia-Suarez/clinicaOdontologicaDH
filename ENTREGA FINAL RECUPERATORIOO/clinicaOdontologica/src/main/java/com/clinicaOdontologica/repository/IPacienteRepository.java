package com.clinicaOdontologica.repository;

import com.clinicaOdontologica.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPacienteRepository extends JpaRepository<Paciente, Long> {
   @Query("SELECT p from Paciente p order by p.id desc")
   List<Paciente> findAllPacienteOrdered();

}

