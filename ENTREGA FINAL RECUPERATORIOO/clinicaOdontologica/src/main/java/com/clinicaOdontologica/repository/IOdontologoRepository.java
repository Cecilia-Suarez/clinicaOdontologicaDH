package com.clinicaOdontologica.repository;

import com.clinicaOdontologica.model.Odontologo;
import com.clinicaOdontologica.model.Turno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IOdontologoRepository extends JpaRepository<Odontologo, Long> {
    @Query("SELECT o from Odontologo o order by o.id desc")
    List<Odontologo> findAllOdontologoOrdered();
}
