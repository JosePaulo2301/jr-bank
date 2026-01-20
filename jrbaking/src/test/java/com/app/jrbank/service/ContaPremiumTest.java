package com.app.jrbank.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.app.jrbank.model.Cliente;
import com.app.jrbank.model.impls.ContaBase;
import com.app.jrbank.model.impls.ContaPremium;
import com.app.jrbank.repository.ContaRepository;

public class ContaPremiumTest {

    @Mock
    private ContaRepository contaRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Deve criar ContaPremium corretamente com cliente e saldo inicial")
    void teste_deve_Conta_Premium() {

        Cliente cliente = new Cliente("Ana lucia", "1325666");
        double saldoInicial = 1000.00;

        ContaBase contaBaseMock = mock(ContaBase.class);
        when(contaBaseMock.getTitular()).thenReturn(cliente);
        when(contaBaseMock.getSaldo()).thenReturn(saldoInicial);

        ContaPremium contaPremium = new ContaPremium(contaBaseMock, 500.00);

        assertEquals(cliente, contaPremium.getTitular());
        assertEquals(saldoInicial, contaPremium.getSaldo(), 0.00001);
    }

    @Test
    @DisplayName("Deve depositoar corretamente")
    void testDeposito() {
        double saldoInicial = 1000.00;
        double deposito = 1000.00;
        ContaBase contaBaseMock = mock(ContaBase.class);
        contaBaseMock.depositar(saldoInicial);
        ContaPremium conta = new ContaPremium(contaBaseMock, deposito);

        assertEquals(1000.00, conta.getSaldo(), 0.00001);

    }

}
