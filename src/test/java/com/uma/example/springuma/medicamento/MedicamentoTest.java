package com.uma.example.springuma.medicamento;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.uma.example.springuma.model.Medicamento;

public class MedicamentoTest {
    
    Medicamento medicamento;

    @BeforeEach
    void setUp() {
        medicamento = new Medicamento();
    }

    @Test
    @DisplayName("El id de Medicamento es correcto")
    void testIdIsCorrect() {
        // arrange
        Long expectedId = (long) 123;
        // act 
        Medicamento medicamento_con_Id = new Medicamento(expectedId);
        Long obtenedId = medicamento_con_Id.getId();
        // assert
        assertEquals(expectedId, obtenedId);
    }

    @Test
    @DisplayName("El nombre de Medicamento es correcto")    
    void testNombreIsCorrect() {
        // arrange
        String expectedNombre = "Paracetamol";
        // act 
        medicamento.setNombre(expectedNombre);
        String obtenedNombre = medicamento.getNombre();
        // assert
        assertEquals(expectedNombre, obtenedNombre);
    }

    @Test
    @DisplayName("La dosis de Medicamento es correcta") 
    void testDosisIsCorrect() {
        // arrange
        float expectedDosis = 500.0f;
        // act 
        medicamento.setDosis(expectedDosis);
        float obtenedDosis = medicamento.getDosis();
        // assert
        assertEquals(expectedDosis, obtenedDosis);
    }

    @Test
    @DisplayName("La frecuencia de Medicamento es correcta")
    void testFrecuenciaIsCorrect() {
        // arrange
        int expectedFrecuencia = 3;
        // act 
        medicamento.setFrecuencia(expectedFrecuencia);
        int obtenedFrecuencia = medicamento.getFrecuencia();
        // assert
        assertEquals(expectedFrecuencia, obtenedFrecuencia);
    }

    @Test
    @DisplayName("La fecha de primera toma de Medicamento es correcta") 
    void testFechaPrimeraTomaIsCorrect() {
        // arrange
        java.util.Date expectedFecha = new java.util.Date();
        // act 
        medicamento.setFechaPrimeraToma(expectedFecha);
        java.util.Date obtenedFecha = medicamento.getFechaPrimeraToma();
        // assert
        assertEquals(expectedFecha, obtenedFecha);
    }

    @Test
    @DisplayName("Constructor vacío crea un medicamento sin ID")
    void testConstructorVacio() {
        // Arrange & Act
        Medicamento medicamento = new Medicamento();

        // Assert
        assertNull(medicamento.getId());
    }
    
}
