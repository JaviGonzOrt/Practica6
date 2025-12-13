package com.uma.example.springuma.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;    
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uma.example.springuma.model.Medicamento;
import com.uma.example.springuma.model.MedicamentoService;

import jakarta.servlet.http.HttpServletResponse;

@RestController
public class  MedicamentoController {
    
    @Autowired
    private MedicamentoService medicamentoService;
    
    /*---Anade un nuevo medicamento al sistema y vuelve a la pantalla de listar medicamentos---*/
    /* Se pone consumes = "application/x-www-form-urlencoded" para que funcione con formularios HTML */
	@PostMapping(value = "/medicamento", consumes = "application/x-www-form-urlencoded")
	public ResponseEntity<?> saveMedicamento(@ModelAttribute("medicamento") Medicamento medicamento, HttpServletResponse response) {
		try {
			medicamentoService.addMedicamento(medicamento);
            response.sendRedirect("/listMedicamento");
			return ResponseEntity.ok().body("Medicamento creado correctamente");
		} catch (Exception er) {
			return ResponseEntity.status(500).body("Error creando el medicamento: " + er.getMessage());
		}
	}

    /*---Actualiza un medicamento del sistema y vuelve a la pantalla de listas medicamentos---*/
    /* Se pone consumes = "application/x-www-form-urlencoded" para que funcione con formularios HTML */
	@PutMapping(value = "/medicamento",  consumes = "application/x-www-form-urlencoded")
    public ResponseEntity<?> updateMedicamento( @ModelAttribute("medicamento")Medicamento medicamento, HttpServletResponse response) {
        try {
            medicamentoService.updateMedicamento(medicamento);
            response.sendRedirect("/listMedicamento");
            return ResponseEntity.ok().body("Medicamento actualizado correctamente");
        } catch (Exception er) {
            return ResponseEntity.status(500).body("Error actualizando el medicamento: " + er.getMessage());
        }
    }


	/*---Elimina una cuenta a partir de su ID y vuelve a la pantalla de listar cuentas---*/
	@DeleteMapping("/medicamento/{id}")
    public ResponseEntity<?> deleteMedicamento(@PathVariable("id") Long id, HttpServletResponse response) {
        try {
            medicamentoService.removeMedicamentoID(id);
            response.sendRedirect("/listMedicamento");
            return ResponseEntity.ok().body("Medicamento eliminado correctamente");
        } catch (Exception er) {
            return ResponseEntity.status(500).body("Error eliminando el medicamento: " + er.getMessage());
        }
    }
}
