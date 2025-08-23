package org.bedu.demo.model;

public class TipoCambio {
    
    private String moneda; // USD, EUR, JPY
    private double valor;
    
    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public TipoCambio(String moneda, double valor) {
        this.moneda = moneda;
        this.valor = valor;
    } 
}
