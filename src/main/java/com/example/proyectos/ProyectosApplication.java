package com.example.proyectos;

import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ProyectosApplication {
  public static void main(String[] args) {
    SpringApplication.run(ProyectosApplication.class, args);
  }
  @Bean
  public ApplicationRunner initRabbit(RabbitAdmin rabbitAdmin) {
    return args -> rabbitAdmin.initialize();
  }
}
