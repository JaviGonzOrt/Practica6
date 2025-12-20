package com.uma.example.springuma.medicamento;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

import com.uma.example.springuma.model.Medicamento;
import com.uma.example.springuma.model.MedicamentoService;
import com.uma.example.springuma.model.RepositoryMedicamento;

@ExtendWith(MockitoExtension.class)
class MedicamentoServiceTest {

    @Mock
    private RepositoryMedicamento repositoryMedicamento;

    @InjectMocks
    private MedicamentoService medicamentoService;

    @Test
    @DisplayName("Obtiene todos los medicamentos cuando la lista está vacía")
    void testGetAllMedicamentos_ListaVacia() {
        // Arrange
        when(repositoryMedicamento.findAll()).thenReturn(List.of());
        // Act
        List<Medicamento> medicamentos = medicamentoService.getAllMedicamentos();
        int sizeObtained = medicamentos.size();
        // Assert
        assertEquals(0, sizeObtained);
        verify(repositoryMedicamento).findAll();
    }

    @Test
    @DisplayName("Obtiene todos los medicamentos cuando hay elementos")
    void testGetAllMedicamentos_ListaNoVacia() {
        // Arrange
        Medicamento m1 = new Medicamento();
        Medicamento m2 = new Medicamento();
        when(repositoryMedicamento.findAll()).thenReturn(Arrays.asList(m1, m2));
        // Act
        List<Medicamento> medicamentos = medicamentoService.getAllMedicamentos();
        // Assert
        assertEquals(2, medicamentos.size());
        verify(repositoryMedicamento).findAll();
    }

    @Test
    @DisplayName("Obtiene un medicamento por su ID")
    void testGetMedicamentoById() {
        // Arrange
        Medicamento medicamento = new Medicamento(1L);
        medicamento.setNombre("Paracetamol");
        when(repositoryMedicamento.findById(1L)).thenReturn(java.util.Optional.of(medicamento));
        // Act
        Medicamento resultado = medicamentoService.getMedicamento(1L);
        // Assert
        assertEquals(medicamento, resultado);
        verify(repositoryMedicamento).findById(1L);
    }

    @Test
    @DisplayName("Añade un medicamento correctamente")
    void testAddMedicamento() {
        // Arrange
        Medicamento medicamento = new Medicamento(1L);
        when(repositoryMedicamento.saveAndFlush(medicamento)).thenReturn(medicamento);
        // Act
        Medicamento resultado = medicamentoService.addMedicamento(medicamento);
        // Assert
        assertEquals(medicamento, resultado);
        verify(repositoryMedicamento).saveAndFlush(medicamento);
    }

    @Test
@DisplayName("Actualiza un medicamento correctamente")
void testUpdateMedicamento() {
    // Arrange
    Medicamento medicamento = new Medicamento(1L);
    medicamento.setNombre("Ibuprofeno");
    when(repositoryMedicamento.getReferenceById(1L)).thenReturn(new Medicamento(1L));
    when(repositoryMedicamento.save(org.mockito.ArgumentMatchers.any(Medicamento.class))).thenAnswer(invocation -> invocation.getArgument(0)); // devuelve el mismo objeto
    // Act
    Medicamento resultado = medicamentoService.updateMedicamento(medicamento);
    // Assert
    assertEquals("Ibuprofeno", resultado.getNombre());
    verify(repositoryMedicamento).getReferenceById(1L);
    verify(repositoryMedicamento).save(org.mockito.ArgumentMatchers.any(Medicamento.class));
}


    @Test
    @DisplayName("Elimina un medicamento por objeto")
    void testRemoveMedicamento() {

        // Arrange
        Medicamento medicamento = new Medicamento(1L);

        // Act
        medicamentoService.removeMedicamento(medicamento);

        // Assert
        verify(repositoryMedicamento).delete(medicamento);
    }

    @Test
    @DisplayName("Elimina un medicamento por ID")
    void testRemoveMedicamentoById() {
        // Act
        medicamentoService.removeMedicamentoID(1L);

        // Assert
        verify(repositoryMedicamento).deleteById(1L);
    }

    @Test
    @DisplayName("Añade un medicamento nulo")
    void testAddMedicamento_Nulo() {
        // Act
        Medicamento resultado = medicamentoService.addMedicamento(null);

        // Assert
        assertEquals(null, resultado);
        verify(repositoryMedicamento).saveAndFlush(null);
    }

    @Test
    @DisplayName("Eliminar un medicamento nulo")
    void testRemoveMedicamento_Nulo() {
        // Act
        medicamentoService.removeMedicamento(null);

        // Assert
        verify(repositoryMedicamento).delete(null);
    }

    @Test
    @DisplayName("Elimina un medicamento por ID inexistente")
    void testRemoveMedicamentoById_NoExiste() {
        // Act
        medicamentoService.removeMedicamentoID(999L);

        // Assert
        verify(repositoryMedicamento).deleteById(999L);
    }

    @Test
    @DisplayName("Añade un medicamento con nombre correctamente")
    void testAddMedicamento_ConNombre() {
        // Arrange
        Medicamento medicamento = new Medicamento(1L);
        medicamento.setNombre("Amoxicilina");
        when(repositoryMedicamento.saveAndFlush(medicamento)).thenReturn(medicamento);

        // Act
        Medicamento resultado = medicamentoService.addMedicamento(medicamento);

        // Assert
        assertEquals("Amoxicilina", resultado.getNombre());
    }

    @Test
    @DisplayName("Busca un medicamento con ID negativo")
    void testGetMedicamento_IdNegativo() {
        // Arrange
        when(repositoryMedicamento.findById(-1L)).thenReturn(java.util.Optional.empty());

        // Act
        Medicamento resultado = medicamentoService.getMedicamento(-1L);

        // Assert
        assertEquals(null, resultado);
    }
}