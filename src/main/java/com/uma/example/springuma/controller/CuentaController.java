package com.uma.example.springuma.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.uma.example.springuma.model.Cuenta;
import com.uma.example.springuma.model.CuentaService;

@RestController
public class CuentaController {
    
    @Autowired
    private CuentaService cuentaService;

    // Devuelve todas las cuentas
    @GetMapping("/cuentas")
    public List<Cuenta> getCuentas(){
        return cuentaService.getAllCuentas();
    }

    // Devuelve una cuenta por ID
    @GetMapping("/cuenta/{id}")
    public Cuenta getCuenta(@PathVariable("id") Long id) {
        return cuentaService.getCuenta(id);
    }

    // Crea una cuenta con el objeto Cuenta recibido en formato JSON
    @PostMapping(value = "/cuenta",     consumes = {MediaType.APPLICATION_JSON_VALUE} )
	public ResponseEntity<?> saveCuenta(@RequestBody Cuenta cuenta) {
        try{
            cuentaService.addCuenta(cuenta);
            return ResponseEntity.ok().body("Una nueva cuenta se ha anyadido");
        }
        catch(Exception e){
            return ResponseEntity.internalServerError().body("La cuenta ya existe");
        }
	}

    // Crea una cuenta con el objeto Cuenta recibido en formato HTML Form
    @PostMapping(value = "/cuenta",   consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE} )
	public ResponseEntity<?> saveHTML  (Cuenta cuenta) {
        try{
            cuentaService.addCuenta(cuenta);
            return ResponseEntity.ok().body("Una nueva cuenta se ha anyadido");
        }
        catch(Exception e){
            return ResponseEntity.internalServerError().body("La cuenta ya existe");
        }
	}

    // Actualiza una cuenta con el objeto Cuenta
    @PutMapping("/cuenta")
    public ResponseEntity<?> updateCuenta (Cuenta cuenta) {
        try{
            cuentaService.updateCuenta(cuenta);
            return ResponseEntity.ok().body("La cuenta se ha actualizado");
        }
        catch(Exception e){
            return ResponseEntity.internalServerError().body("Error al actualizar la cuenta");
        }
    }

    // Elimina una cuenta pasando el objeto Cuenta
    @DeleteMapping("/cuenta")
    public ResponseEntity<?> deleteCuenta(@RequestBody Cuenta cuenta){
        try{
            cuentaService.removeCuenta(cuenta);
            return ResponseEntity.ok().body("La cuenta se ha eliminado");
        }
        catch(Exception e){
            return ResponseEntity.internalServerError().body("Error al eliminar la cuenta");
        }
    }

    // Otra forma de eliminar una cuenta con el ID
    @DeleteMapping("/cuenta/{id}")
    public ResponseEntity<?> deleteCuenta(@PathVariable("id") Long id) {
        try{
            cuentaService.removeCuentaID(id);
            return ResponseEntity.ok().body("La cuenta se ha eliminado");
        }
        catch(Exception e){
            return ResponseEntity.internalServerError().body("Error al eliminar la cuenta");
        }
    }
}
