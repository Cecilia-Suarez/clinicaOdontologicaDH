package com.clinicaOdontologica.repository;


import com.clinicaOdontologica.model.Paciente;
import com.clinicaOdontologica.model.Turno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ITurnoRepository extends JpaRepository<Turno, Long> {
    @Query("SELECT t from Turno t order by t.id desc")
        List<Turno> findAllTurnoOrdered();
}
