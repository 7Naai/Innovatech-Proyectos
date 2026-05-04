package com.example.proyectos.service;

import com.example.proyectos.model.Proyecto;
import com.example.proyectos.repository.ProyectoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProyectoService {

    private final ProyectoRepository repository;

    public List<Proyecto> listarProyectos() {
        return repository.findAll();
    }

    public Proyecto obtenerProyectoPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Proyecto crearProyecto(Proyecto proyecto) {
        return repository.save(proyecto);
    }

    public Proyecto actualizarProyecto(Long id, Proyecto proyecto) {
        Proyecto existente = repository.findById(id).orElse(null);

        if (existente != null) {
            existente.setNombre(proyecto.getNombre());
            existente.setDescripcion(proyecto.getDescripcion());
            existente.setEstado(proyecto.getEstado());
            existente.setPrioridad(proyecto.getPrioridad());
            existente.setFecha_inicio(proyecto.getFecha_inicio());
            existente.setFecha_fin(proyecto.getFecha_fin());
            return repository.save(existente);
        }
        return null;
    }

    public Proyecto actualizarEstado(Long id, String estado) {
        Proyecto proyecto = repository.findById(id).orElse(null);

        if (proyecto != null) {
            proyecto.setEstado(estado);
            return repository.save(proyecto);
        }
        return null;
    }

    public void eliminarProyecto(Long id) {
        repository.deleteById(id);
    }
}