package com.example.proyectos.model;


import lombok.AllArgsConstructor;

import lombok.Data;


@Data

@AllArgsConstructor

public class ResumenProyecto {

    private Long id_proyecto;

    private Long tareas_completadas;

    private Long tareas_totales;

}

