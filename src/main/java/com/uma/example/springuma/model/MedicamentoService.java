package com.uma.example.springuma.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MedicamentoService {

    @Autowired
    RepositoryMedicamento RepositoryMedicamento;

    // Devuelve todas los medicamentos
    public List<Medicamento> getAllMedicamentos(){
        return RepositoryMedicamento.findAll();
    }

    // Devuelve una persona por ID
    public Medicamento getMedicamento(Long id){
        return RepositoryMedicamento.getReferenceById(id);
    }

    // Crea una persona
    public Medicamento addMedicamento(Medicamento c){
        return RepositoryMedicamento.saveAndFlush(c);
    }

    //  Actualiza una persona
    public void updateMedicamento(Medicamento m){
        Medicamento medicamento = RepositoryMedicamento.getReferenceById(m.getId());
		medicamento.

        medicamento.setNombre(m.getNombre());
        repositoryMedicamento.save(Medicamento);
    }

    // Elimina una persona
    public void removePersona(Persona c){
        repositoryPersona.delete(c);
    }

    // Elimina una persona por ID
    public void removePersonaID(Long id){
        repositoryPersona.deleteById(id);
    }
}




    
}
