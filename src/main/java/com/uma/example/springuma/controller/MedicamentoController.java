package com.uma.example.springuma.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.uma.example.springuma.model.Medicamento;
import com.uma.example.springuma.model.MedicamentoService;

@RestController
public class MedicamentoController {

    @Autowired
    private MedicamentoService medicamentoService;

    // Devuelve todas las personas
    @GetMapping("/medicamentos")
    public List<Medicamento> getMedicamentos(){
        return medicamentoService.getAllMedicamentos();
    }

    // Devuelve una persona por ID
    @GetMapping("/medicamento/{id}")
    public Medicamento getMedicamento(@PathVariable("id") Long id) {
        return medicamentoService.getMedicamento(id);
    }

    // Crea una persona con el objeto Persona recibido en formato JSON
    @PostMapping
    public Medicamento crearMedicamento(@RequestBody Medicamento medicamento) {
        // La información del medicamento se envía en el cuerpo (body) de la petición HTTP
        return medicamentoService.addMedicamento(medicamento);
    }

    // Actualiza una persona con el objeto Persona
    @PutMapping("/medicamento")
    public ResponseEntity<?> updateMedicamento (Medicamento medicamento) {
        try{
            medicamentoService.updateMedicamento(medicamento);
            return ResponseEntity.ok().body("El medicamento se ha actualizado");
        }
        catch(Exception e){
            return ResponseEntity.internalServerError().body("Error al actualizar el medicamento");
        }
    }
    
    // Elimina una persona pasando el objeto Persona
    @DeleteMapping("/mediacemento")
    public ResponseEntity<?> deletePersona(@RequestBody Medicamento medicamento){
        try{
            medicamentoService.removeMedicamento(medicamento);
            return ResponseEntity.ok().body("El medicamento se ha eliminado");
        }
        catch(Exception e){
            return ResponseEntity.internalServerError().body("Error al eliminar el medicamento");
        }
    }

    // Otra forma de eliminar una persona con el ID
    @DeleteMapping("/medicamento/{id}")
    public ResponseEntity<?> deletePersona(@PathVariable("id") Long id) {
        try{
            medicamentoService.removeMedicamentoID(id);
            return ResponseEntity.ok().body("El medicamento se ha eliminado");
        }
        catch(Exception e){
            return ResponseEntity.internalServerError().body("Error al eliminar el medicamento");
        }
    }
    
}