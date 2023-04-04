package repository;

import model.Leilao;

public interface LeilaoRepository {
    Leilao buscarPorId(int id);
    Leilao salvar(Leilao leilao);
}
