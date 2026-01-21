package com.app.jrbank.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.app.jrbank.exception.SaldoInsuficienteException;
import com.app.jrbank.model.Cliente;
import com.app.jrbank.model.impls.ContaBase;
import com.app.jrbank.model.impls.ContaPremium;
import com.app.jrbank.repository.ContaRepository;
import com.app.jrbank.service.impls.ContaBaseTestImpl;

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
    void teste_Deve_Depositar_Corretamente() {
        double saldoInicial = 1000.00;
        ContaBase contaBase = new ContaBaseTestImpl(new Cliente("Ana maria", "4633333465"), 0);
        contaBase.depositar(saldoInicial);
        ContaPremium conta = new ContaPremium(contaBase, 500.00);
        assertEquals(1000.00, conta.getSaldo(), 0.00001);

    }
    @Test
    void teste_Deve_AumentarOSaldo() {
        double valorDepositoado = 1000.00;
        ContaBase contaBase = new ContaBaseTestImpl(new Cliente("Ana maria", "4633333465"), 0);
        contaBase.depositar(valorDepositoado);
        contaBase.depositar(50);
        assertEquals(1050.00, contaBase.getSaldo(), 0.00001, "Deve aumentar o saldo");
        
    }
    @Test
    void teste_Deve_SacarOValor() {
        double valorDescontado = 100.00;
        double valorDepositado = 1000.00;
        ContaBase contaBase = new ContaBaseTestImpl(new Cliente("Ana maria", "4633333465"), 0);

        contaBase.depositar(valorDepositado);
        contaBase.sacar(valorDescontado);
        assertEquals(900.00, contaBase.getSaldo(), 0.00001, "Deve debitar corretamente");
    }

    @Test
    void teste_NaoDeveDebitarValorNegativo() {
        double valorDepositado = 1000.00;
        double valorDebitado = 1_000_000_000;
        
        ContaBase contaBase = new ContaBaseTestImpl(new Cliente("Ana maria", "4633333465"), 0);
        
        contaBase.depositar(valorDepositado);
        
        assertThrows(SaldoInsuficienteException.class, () -> contaBase.sacar(valorDebitado), "Não deve sacar com valor negativo");
        
    }


}
