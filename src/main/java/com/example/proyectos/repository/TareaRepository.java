package com.example.proyectos.repository;

import com.example.proyectos.model.Tarea;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TareaRepository extends JpaRepository<Tarea, Long> {

    List<Tarea> findByProyecto_IdProyecto(Long idProyecto);
}