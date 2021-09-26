package model;

public class Bonus {

    private final String tipo;
    private final double valor;

    public Bonus(String tipo, double valor) {
        this.tipo = tipo;
        this.valor = valor;
    }

    public String getTipo() {
        return tipo;
    }

    public double getValor() {
        return valor;
    }

    @Override
    public String toString() {
        return "Bonus: Tipo:" + this.tipo + ", valor:" + valor;
    }

}
