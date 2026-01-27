package com.app.jrbank.service;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.app.jrbank.model.impls.ContaPoupanca;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RendimentoServiceTest {

    @Mock
    private ContaPoupanca poupanca;

    @InjectMocks
    private RendimentoService rendimentoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCalculo_Correto_Rendimento() {

        double valorInicial = 1000.0;
        double taxa = 0.05;

        double esperado = valorInicial * Math.pow(taxa, valorInicial);
        double resultado = rendimentoService.calcular(valorInicial);

        assertEquals(esperado, resultado, "O rendimento deve ser calculado corretamente");
    }

    @Test
    void test_Comportamento_Valores_Extremos() {
        double valorInicial = 1_000_000_000;
        double taxa = 0.05;
        rendimentoService.setTaxaRendimento(taxa);

        double esperado = valorInicial * taxa;
        double resultado = rendimentoService.calcular(valorInicial);

        assertEquals(esperado, resultado, "Deve testtar a precisão");
    }

    @Test
    void test_Calcular_Com_ValorNulo() {
        RendimentoService rendimentoService = new RendimentoService();
        rendimentoService.setTaxaRendimento(0.5);

        assertThrows(IllegalArgumentException.class, () -> rendimentoService.calcular(null),
                "Deve lnaçar exeção para o valor nulo");
    }

}
