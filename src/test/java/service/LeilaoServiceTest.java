package service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import model.Leilao;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import repository.LeilaoRepository;

import java.time.LocalDate;

public class LeilaoServiceTest {

    Leilao leilao;
    static LeilaoRepository leilaoRepository;
    LeilaoService leilaoService;

    @BeforeAll
    public static void init() {
        leilaoRepository = mock(LeilaoRepository.class);
    }

    @BeforeEach
    public void setup() {
        leilaoService = new LeilaoService(leilaoRepository);
        leilao = new Leilao();
    }

    @Test
    @DisplayName("teste deve criar leilão com data no futuro")
    public void testCriarLeilaoComDataNoFuturo() {
        LocalDate dataAbertura = LocalDate.now().plusDays(1);
        leilao.setDataAbertura(dataAbertura);

        when(leilaoRepository.salvar(leilao)).thenReturn(leilao);

        Leilao leilaoSalvo = leilaoService.criarLeilao(leilao);

        verify(leilaoRepository).salvar(leilao);
        assertEquals(dataAbertura, leilaoSalvo.getDataAbertura());
    }

    @Test
    @DisplayName("teste de exceção quando cria leilão com data no passado")
    public void testCriarLeilao_DataPassado_DeveLancarExcecao() {

        leilao.setDataAbertura(LocalDate.now().minusDays(1));

        assertThrows(IllegalArgumentException.class, () -> {
            leilaoService.criarLeilao(leilao);
        });

    }

    @Test
    @DisplayName("teste de encerrar o leilão no momento que o método é chamado")
    public void testEncerrarLeilaoNoMomentoQueOMetodoEhChamado() {

        LocalDate dataFinal = LocalDate.now();
        leilaoService.encerrarLeilao(leilao);

        when(leilaoRepository.salvar(leilao)).thenReturn(leilao);

        verify(leilaoRepository).salvar(leilao);
        assertEquals(dataFinal, leilao.getDataFinal());

    }

}
