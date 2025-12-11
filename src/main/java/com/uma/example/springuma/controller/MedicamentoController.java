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

    // Devuelve todas las medicamentos
    @GetMapping("/medicamentos")
    public List<Medicamento> getMedicamentos(){
        return medicamentoService.getAllMedicamentos();
    }

    // Devuelve un medicamento por ID
    @GetMapping("/medicamento/{id}")
    public Medicamento getMedicamento(@PathVariable("id") Long id) {
        return medicamentoService.getMedicamento(id);
    }

    // Crea un medicamento con el objeto Medicamento recibido en formato JSON
    @PostMapping("/medicamento")
    public Medicamento crearMedicamento(@RequestBody Medicamento medicamento) {
        // La información del medicamento se envía en el cuerpo (body) de la petición HTTP
        return medicamentoService.addMedicamento(medicamento);
    }

    // Actualiza una medicamento con el objeto Medicamento
    @PutMapping("/medicamento")
    public ResponseEntity<?> updateMedicamento (@RequestBody Medicamento medicamento) {
        try{
            medicamentoService.updateMedicamento(medicamento);
            return ResponseEntity.ok().body("El medicamento se ha actualizado");
        }
        catch(Exception e){
            return ResponseEntity.internalServerError().body("Error al actualizar el medicamento");
        }
    }
    
    // Elimina un medicamento pasando el objeto Medicamento
    @DeleteMapping("/medicamento")
    public ResponseEntity<?> deleteMedicamento(@RequestBody Medicamento medicamento){
        try{
            medicamentoService.removeMedicamento(medicamento);
            return ResponseEntity.ok().body("El medicamento se ha eliminado");
        }
        catch(Exception e){
            return ResponseEntity.internalServerError().body("Error al eliminar el medicamento");
        }
    }

    // Otra forma de eliminar un medicamento con el ID
    @DeleteMapping("/medicamento/{id}")
    public ResponseEntity<?> deleteMedicamento(@PathVariable("id") Long id) {
        try{
            medicamentoService.removeMedicamentoID(id);
            return ResponseEntity.ok().body("El medicamento se ha eliminado");
        }
        catch(Exception e){
            return ResponseEntity.internalServerError().body("Error al eliminar el medicamento");
        }
    }

    
}
