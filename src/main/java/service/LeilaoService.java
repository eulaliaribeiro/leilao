package service;

import model.Leilao;
import repository.LeilaoRepository;

import java.time.LocalDate;

public class LeilaoService {
    private LeilaoRepository leilaoRepository;

    public LeilaoService(LeilaoRepository leilaoRepository) {
        this.leilaoRepository = leilaoRepository;
    }

    public Leilao criarLeilao(Leilao leilao) {
        if (leilao.getDataAbertura().isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("A data de abertura do leilão deve ser no futuro!");
        }
        return leilaoRepository.salvar(leilao);
    }

    public void encerrarLeilao(Leilao leilao) {
        leilao.setDataFinal(LocalDate.now());
        leilaoRepository.salvar(leilao);
    }

}
