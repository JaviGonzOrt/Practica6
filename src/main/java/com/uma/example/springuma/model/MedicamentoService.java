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

    // Devuelve un medicamento por ID
    public Medicamento getMedicamento(Long id){
        return RepositoryMedicamento.getReferenceById(id);
    }

    // Crea un medicamento
    public Medicamento addMedicamento(Medicamento c){
        return RepositoryMedicamento.saveAndFlush(c);
    }

    //  Actualiza un medicamento
    public void updateMedicamento(Medicamento m){
        Medicamento medicamento = RepositoryMedicamento.getReferenceById(m.getId());
		medicamento.setId(m.getId());
        medicamento.setFrecuencia(m.getFrecuencia());
        medicamento.setDosis(m.getDosis());
        medicamento.setNombre(m.getNombre());
        medicamento.setFechaPrimeraToma(m.getFechaPrimeraToma());
        RepositoryMedicamento.save(medicamento);
    }

    // Elimina una medicamento
    public void removeMedicamento(Medicamento m){
        RepositoryMedicamento.delete(m);
    }

    // Elimina un medicamento por ID
    public void removeMedicamentoID(Long id){
        RepositoryMedicamento.deleteById(id);
    }
}
