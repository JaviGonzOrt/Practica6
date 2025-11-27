package com.uma.example.springuma.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CuentaService {

    @Autowired
    RepositoryCuenta repositoryCuenta;

    // Devuelve todas las cuentas
    public List<Cuenta> getAllCuentas(){
        return repositoryCuenta.findAll();
    }

    // Devuelve una cuenta por ID
    public Cuenta getCuenta(Long id){
        return repositoryCuenta.getReferenceById(id);
    }

    // Crea una cuenta
    public Cuenta addCuenta(Cuenta c){
        return repositoryCuenta.saveAndFlush(c);
    }

    //  Actualiza una cuenta
    public void updateCuenta(Cuenta c){
        Cuenta cuenta = repositoryCuenta.getReferenceById(c.getId());
		cuenta.setBalance(c.getBalance());
		cuenta.setCcc(c.getCcc());
        cuenta.setTitular(c.getTitular());
        repositoryCuenta.save(cuenta);
    }

    // Elimina una cuenta
    public void removeCuenta(Cuenta c){
        repositoryCuenta.delete(c);
    }

    // Elimina una cuenta por ID
    public void removeCuentaID(Long id){
        repositoryCuenta.deleteById(id);
    }
}
