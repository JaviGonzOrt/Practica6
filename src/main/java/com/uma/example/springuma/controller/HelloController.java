package com.uma.example.springuma.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.uma.example.springuma.model.Cuenta;

@RestController
public class HelloController {  
  
  @GetMapping("/hello")
  public String index() {
    return "Ingenieria del Software, UMA";
  }

  @GetMapping("/get_cuenta")
  public Cuenta cuenta() {
    return new Cuenta(123);
  }

  @GetMapping("/nombre")
  public String hello(@RequestParam(value = "name", defaultValue = "cristian") String name) {
    return String.format("Hola alumno %s!", name);
  }

  @GetMapping("/nombre/{name}")
  public String get(@PathVariable("name") String name) {
    return String.format("Hola alumno has pasado el path %s!", name);
  }

}
