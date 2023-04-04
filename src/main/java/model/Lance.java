package model;

public class Lance {
    private int id;
    private double valor;
    private Leilao leilao;

    public Lance() {

    }

    public Lance(double valor, Leilao leilao) {
        this.valor = valor;
        this.leilao = leilao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Leilao getLeilao() {
        return leilao;
    }

    public void setLeilao(Leilao leilao) {
        this.leilao = leilao;
    }


}
