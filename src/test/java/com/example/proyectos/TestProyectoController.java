package com.example.proyectos;

import com.example.proyectos.controller.ProyectoController;
import com.example.proyectos.model.Proyecto;
import com.example.proyectos.service.ProyectoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProyectoController.class)
public class TestProyectoController {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProyectoService service;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testGetAll() throws Exception {

        Proyecto proyecto = new Proyecto();
        proyecto.setIdProyecto(1L);
        proyecto.setNombre("Proyecto Test");

        Mockito.when(service.listarProyectos())
                .thenReturn(List.of(proyecto));

        mockMvc.perform(get("/api/proyectos/proyecto"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nombre").value("Proyecto Test"));
    }

    @Test
    void testCreate() throws Exception {

        Proyecto proyecto = new Proyecto();
        proyecto.setIdProyecto(1L);
        proyecto.setNombre("Nuevo Proyecto");

        Mockito.when(service.crearProyecto(Mockito.any(Proyecto.class)))
                .thenReturn(proyecto);

        mockMvc.perform(post("/api/proyectos/proyecto")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(proyecto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Nuevo Proyecto"));
    }
}