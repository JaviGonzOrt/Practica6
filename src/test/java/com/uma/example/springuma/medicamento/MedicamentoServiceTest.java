package com.uma.example.springuma.medicamento;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.uma.example.springuma.model.MedicamentoService;
import com.uma.example.springuma.model.RepositoryMedicamento;

import jakarta.inject.Inject;

public class MedicamentoServiceTest {

    @Mock
    RepositoryMedicamento repositoryMedicamento;

    @InjectMocks
    private MedicamentoService medicamentoService;

    @Test
    @DisplayName("Comprueba que se pueden obtener todos los medicamentos")
    void testGetAllMedicamentos() {
        // Arrage
        // Act
        List<Medicamento> medicamentos = medicamentoService.getAllMedicamentos();
        int sizeObtained = medicamentos.size();
        // Assert
        assertEquals(0, sizeObtained);
        verify(repositoryMedicamento).findAll();
    }
    
    
}
