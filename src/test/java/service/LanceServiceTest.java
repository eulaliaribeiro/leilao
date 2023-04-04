package service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import model.Lance;
import model.Leilao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import repository.LanceRepository;
import repository.LeilaoRepository;

import java.time.LocalDate;

public class LanceServiceTest {

    Leilao leilao1, leilao2;
    Lance lance1, lance2;
    static LeilaoRepository leilaoRepository;
    static LanceRepository lanceRepository;
    LeilaoService leilaoService;
    LanceService lanceService;


    @BeforeEach
    public void setup() {
        leilaoRepository = mock(LeilaoRepository.class);
        lanceRepository = mock(LanceRepository.class);
        leilaoService = new LeilaoService(leilaoRepository);
        lanceService = new LanceService(lanceRepository, leilaoRepository);
        leilao1 = new Leilao();
        leilao2 = new Leilao(1, 1000, LocalDate.now().plusDays(1));
        lance1 = new Lance();
        lance2 = new Lance(1500, leilao2);
    }

    @Test
    @DisplayName("teste dar lance no leilão")
    public void testDeveDarLance() {

        // Given
        when(leilaoRepository.buscarPorId(1)).thenReturn(leilao2);
        when(lanceRepository.salvar(any(Lance.class))).thenAnswer(invocation -> {
            Lance lanceSalvo = invocation.getArgument(0);
            lanceSalvo.setId(1);
            return lanceSalvo;
        });

        // When
        Lance lanceSalvo = lanceService.darLance(lance2);

        // Then
        assertNotNull(lanceSalvo.getId());
        assertEquals(leilao2, lanceSalvo.getLeilao());
        assertEquals(1500.0, lanceSalvo.getValor());

        // Verificação das chamadas aos mocks
        verify(leilaoRepository).buscarPorId(1);
        verify(lanceRepository).salvar(lance2);

    }

    @Test
    @DisplayName("teste dar lance em leilão não encontrado")
    void testDarLance_LeilaoNaoEncontrado_DeveLancarExcecao() {

        // Given
        when(leilaoRepository.buscarPorId(1)).thenReturn(null);

        // When
        lance1.setLeilao(new Leilao());
        lance1.getLeilao().setId(1);

        // Then
        assertThrows(IllegalArgumentException.class, () -> lanceService.darLance(lance1));

        verify(leilaoRepository).buscarPorId(1);
        verify(lanceRepository, never()).salvar(any(Lance.class));
    }

    @Test
    @DisplayName("teste dar lance com valor menor que o inicial")
    void testDarLance_ValorMenorQueValorInicial_DeveLancarExcecao() {

        // Given
        when(leilaoRepository.buscarPorId(1)).thenReturn(leilao2);

        // When
        lance2.setValor(80.0);

        // Then
        assertThrows(IllegalArgumentException.class, () -> lanceService.darLance(lance2));

        verify(leilaoRepository).buscarPorId(1);
        verify(lanceRepository, never()).salvar(any(Lance.class));
    }

}
