package com.uma.example.springuma.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonaService {

    @Autowired
    RepositoryPersona repositoryPersona;

    // Devuelve todas las personas
    public List<Persona> getAllPersonas(){
        return repositoryPersona.findAll();
    }

    // Devuelve una persona por ID
    public Persona getPersona(Long id){
        return repositoryPersona.getReferenceById(id);
    }

    // Crea una persona
    public Persona addPersona(Persona c){
        return repositoryPersona.saveAndFlush(c);
    }

    //  Actualiza una persona
    public void updatePersona(Persona p){
        Persona persona = repositoryPersona.getReferenceById(p.getId());
		persona.setDni(p.getDni());
		persona.setEdad(p.getEdad());
        persona.setNombre(p.getNombre());
        repositoryPersona.save(persona);
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
