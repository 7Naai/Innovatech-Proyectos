package com.example.proyectos;

import com.example.proyectos.controller.TareaController;
import com.example.proyectos.model.Tarea;
import com.example.proyectos.service.TareaService;
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

@WebMvcTest(TareaController.class)
public class TestTareaController {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TareaService service;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testGetAll() throws Exception {

        Tarea tarea = new Tarea();
        tarea.setIdTarea(1L);
        tarea.setNombre("Tarea Test");

        Mockito.when(service.listarTareas())
                .thenReturn(List.of(tarea));

        mockMvc.perform(get("/api/proyectos/tareas"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nombre").value("Tarea Test"));
    }

    @Test
    void testCreate() throws Exception {

        Tarea tarea = new Tarea();
        tarea.setIdTarea(1L);
        tarea.setNombre("Nueva Tarea");

        Mockito.when(service.crearTarea(Mockito.any(Tarea.class)))
                .thenReturn(tarea);

        mockMvc.perform(post("/api/proyectos/tareas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(tarea)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Nueva Tarea"));
    }
}