package model;

import java.time.LocalDate;
import java.util.List;

public class Leilao {
    private int id;
    private String nome;
    private double valorInicial;
    private LocalDate dataAbertura;
    private LocalDate dataFinal;
    private List<Lance> lances;

    public Leilao() {

    }

    public Leilao(int id, double valorInicial, LocalDate dataFinal) {
        this.id = id;
        this.valorInicial = valorInicial;
        this.dataFinal = dataFinal;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getValorInicial() {
        return valorInicial;
    }

    public void setValorInicial(double valorInicial) {
        this.valorInicial = valorInicial;
    }

    public LocalDate getDataAbertura() {
        return dataAbertura;
    }

    public void setDataAbertura(LocalDate dataAbertura) {
        this.dataAbertura = dataAbertura;
    }

    public LocalDate getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(LocalDate dataFinal) {
        this.dataFinal = dataFinal;
    }

    public List<Lance> getLances() {
        return lances;
    }

    public void setLances(List<Lance> lances) {
        this.lances = lances;
    }
}