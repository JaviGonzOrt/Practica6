package com.uma.example.springuma.medicamento;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.uma.example.springuma.model.Medicamento;
import com.uma.example.springuma.model.MedicamentoService;
import com.uma.example.springuma.model.RepositoryMedicamento;

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
    
    @Test
    @DisplayName("Comprueba que devuelve mas de un medicamento")
    public void getAllMedicamentos_ListaNoVacia() {
        // Arrage
        Medicamento medicamento1 = new Medicamento();
        Medicamento medicamento2 = new Medicamento();
        when(repositoryMedicamento.findAll()).thenReturn(Arrays.asList(medicamento1, medicamento2));
        // Act
        List<Medicamento> result = medicamentoService.getAllMedicamentos();
        int sizeObtained = result.size();
        // Assert
        assertEquals(2, sizeObtained);
        verify(repositoryMedicamento).findAll();
    }

    

    
}
