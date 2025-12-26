package com.app.jrbank.model;

import com.app.jrbank.exception.SaldoInsuficienteException;
import com.app.jrbank.repository.ContaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

public class ContaServiceTest {

    @Mock
    private ContaRepository contaRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testeDebitarValor_SaldoInsuficiente_lancarExcecao() {
        int origem = 1002;
        int destino = 1003;
        double valor = 100.00;

        Conta contaOrigem = new Conta(origem, valor);
        Conta contaDestino = new Conta(destino, valor);

        Mockito.when(contaRepository.buscarPorNumero(origem)).thenReturn(contaDestino);
        Mockito.when(contaRepository.buscarPorNumero(destino)).thenReturn(contaOrigem);

        assertThrows(SaldoInsuficienteException.class,
                () -> contaRepository.buscarPorNumero(destino)

        );

    }
}
