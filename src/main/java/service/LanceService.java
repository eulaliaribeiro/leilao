package service;

import model.Lance;
import model.Leilao;
import repository.LanceRepository;
import repository.LeilaoRepository;

import java.time.LocalDate;

public class LanceService {
    private LanceRepository lanceRepository;
    private LeilaoRepository leilaoRepository;

    public LanceService(LanceRepository lanceRepository, LeilaoRepository leilaoRepository) {
        this.lanceRepository = lanceRepository;
        this.leilaoRepository = leilaoRepository;
    }

    public Lance darLance(Lance lance) {
        Leilao leilao = leilaoRepository.buscarPorId(lance.getLeilao().getId());
        if (leilao == null) {
            throw new IllegalArgumentException("Leilão não encontrado!");
        }
        if (lance.getValor() <= leilao.getValorInicial()) {
            throw new IllegalArgumentException("O valor do lance deve ser maior que o valor inicial do leilão!");
        }
        if (!leilao.getDataFinal().isAfter(LocalDate.now())) {
            throw new IllegalStateException("Leilão já encerrado!");
        }
        return lanceRepository.salvar(lance);
    }
}
