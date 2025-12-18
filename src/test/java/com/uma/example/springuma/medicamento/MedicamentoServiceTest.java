package com.uma.example.springuma.medicamento;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.never;
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

    @Test
    @DisplayName("Compueba que se puede obtener un medicamento por su id")
    void testGetMedicamentoById() {
        // Arrage
        Long id = 1L;
        Medicamento medicamento = new Medicamento();
        when(repositoryMedicamento.findById(id)).thenReturn(java.util.Optional.of(medicamento));
        // Act
        Medicamento medicamentoObtained = medicamentoService.getMedicamento(id);
        // Assert
        assertEquals(medicamento, medicamentoObtained);
        verify(repositoryMedicamento).findById(id);
    }

    @Test
    @DisplayName("Comprueba que se puede crear un medicamento")
    void testAddMedicamento() {
        // Arrage
        Medicamento medicamento = new Medicamento(1234L);
        when(repositoryMedicamento.save(medicamento)).thenReturn(medicamento);
        // Act
        Medicamento medicamentoCreated = medicamentoService.addMedicamento(medicamento);
        Long idObtained = medicamentoCreated.getId();
        // Assert
        assertEquals(medicamento, medicamentoCreated);
        assertEquals(1234L, idObtained);
        verify(repositoryMedicamento).save(medicamento);
    }

    @Test
    @DisplayName("Comprueba que se puede actualizar un medicamento")
    void testUpdateMedicamento() {
        // Arrage
        Medicamento medicamento = new Medicamento(1L);
        medicamento.setNombre("Paracetamol");
        when(repositoryMedicamento.findById(1L)).thenReturn(java.util.Optional.of(medicamento));
        // Act
        medicamento.setNombre("Ibuprofeno");
        Medicamento medicamentoResult = medicamentoService.updateMedicamento(medicamento);
        // Assert
        assertEquals(medicamento, medicamentoResult);
        verify(repositoryMedicamento).findById(1L);
        verify(repositoryMedicamento).save(medicamento);
    }

    @Test
    @DisplayName("Comprueba que no se puede actualizar un medicamento que no existe")
    void testUpdateMedicamento_NoExisteMedicamento() {
        // Arrage
        Medicamento medicamento = new Medicamento(1L);
        medicamento.setNombre("Paracetamol");
        when(repositoryMedicamento.findById(1L)).thenReturn(java.util.Optional.empty());
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> medicamentoService.updateMedicamento(medicamento));
        
        verify(repositoryMedicamento).findById(1L);
        verify(repositoryMedicamento, never()).save(medicamento);
    }

    @Test
    @DisplayName("Comprueba que no se puede eliminar un medicamento atraves del objeto medicamento que no existe")
    void testRemoveMedicamento_medicamentoNoExiste() {

        // Arrange
        Medicamento medicamento = new Medicamento(1L);
        medicamento.setId(1L);
        when(repositoryMedicamento.existsById(1L)).thenReturn(false);
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> medicamentoService.removeMedicamento(medicamento));

        verify(repositoryMedicamento).existsById(1L);
        verify(repositoryMedicamento, never()).delete(medicamento);
    }

    @Test
    @DisplayName("Comprueba que se puede eliminar un medicamento atraves del objeto medicamento")
    void testRemoveMedicamento() {

        // Arrange
        Medicamento medicamento = new Medicamento(1L);
        medicamento.setId(1L);
        when(repositoryMedicamento.existsById(1L)).thenReturn(true);
        // Act
        medicamentoService.removeMedicamento(medicamento);
        //Assert
        verify(repositoryMedicamento).existsById(1L);
        verify(repositoryMedicamento).delete(medicamento);
    }

    @Test
    @DisplayName("Comprueba que no se puede eliminar un medicamento atraves de su id si el id es null")
    void testRemoveMedicamento_IdNull() {

        // Arrange
        Long id = null;
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> medicamentoService.removeMedicamentoID(id));

        verify(repositoryMedicamento, never()).deleteById(id);
    }

    @Test
    @DisplayName("Compueba que no se puede eliminar un medicamento atraves de su id si no existe")
    void testRemoveMedicamento_IdNoExiste() {

        // Arrange
        Long id = 1L;
        when(repositoryMedicamento.existsById(id)).thenReturn(false);
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> medicamentoService.removeMedicamentoID(id));

        verify(repositoryMedicamento).existsById(id);
        verify(repositoryMedicamento, never()).deleteById(id);
    }

    @Test
    @DisplayName("Compueba que se puede eliminar un medicamento atraves de su id")
    void testRemoveMedicamento_IdExiste() {

        // Arrange
        Long id = 1L;
        when(repositoryMedicamento.existsById(id)).thenReturn(true);
        // Act
        medicamentoService.removeMedicamentoID(id);
        //Assert
        verify(repositoryMedicamento).existsById(id);
        verify(repositoryMedicamento).deleteById(id);
    }

}