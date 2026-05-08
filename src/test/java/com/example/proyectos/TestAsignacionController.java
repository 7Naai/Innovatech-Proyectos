package com.example.proyectos;

import com.example.proyectos.controller.AsignacionController;
import com.example.proyectos.model.Asignacion;
import com.example.proyectos.service.AsignacionService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AsignacionController.class)
public class TestAsignacionController {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AsignacionService service;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testGetAll() throws Exception {

        Asignacion asignacion = new Asignacion();
        asignacion.setIdAsignacion(1L);

        Mockito.when(service.listarAsignaciones())
                .thenReturn(List.of(asignacion));

        mockMvc.perform(get("/api/proyectos/asignacion"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].idAsignacion").value(1));
    }

    @Test
    void testCreate() throws Exception {

        Asignacion asignacion = new Asignacion();
        asignacion.setIdAsignacion(1L);
        asignacion.setIdUsuario(10L);
        asignacion.setFechaAsignacion(LocalDate.now());

        Mockito.when(service.crearAsignacion(Mockito.any(Asignacion.class)))
                .thenReturn(asignacion);

        mockMvc.perform(post("/api/proyectos/asignacion")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(asignacion)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idUsuario").value(10));
    }
}