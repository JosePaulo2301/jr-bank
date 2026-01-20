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
        // Exemplo: valor inicial 1000, taxa 0.05 (5%), período 1 ano
        // Arrange
        double valorInicial = 1000.0;
        double taxa = 0.05;

        // Act - Quando o método ou função está em preparaçãopara o teste
        double esperado = valorInicial * Math.pow(taxa, valorInicial);
        double resultado = rendimentoService.calcular(valorInicial);

        // Assert - verificando se os resultados obtidos batem com os resultados
        // esperados
        assertEquals(esperado, resultado, "O rendimento deve ser calculado corretamente");
    }

    @Test
    void test_Comportamento_Valores_Extremos() {
        // Ex: testando valor alto para ver se é estourado o limite de double
        // Arrange
        double valorInicial = 1_000_000_000;
        double taxa = 0.05;
        rendimentoService.setTaxaRendimento(taxa);

        // act
        double esperado = valorInicial * taxa;
        double resultado = rendimentoService.calcular(valorInicial);

        // assert
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
