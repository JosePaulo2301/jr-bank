package com.app.jrbank.service;

public class TaxaService {
    private final double taxa;

    public TaxaService(double taxa) {
        this.taxa = taxa;
    }

    public double aplicar(double valor) {
        return valor + taxa;
    }
}
