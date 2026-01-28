package com.app.jrbank.service;

import com.app.jrbank.exception.SaldoInsuficienteException;
import com.app.jrbank.model.Cliente;
import com.app.jrbank.model.Conta;
import com.app.jrbank.model.impls.ContaBase;
import com.app.jrbank.model.impls.ContaNormal;
import com.app.jrbank.repository.ContaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ContaNormalTest {

    @Mock
    private ContaRepository contaRepository;

    @InjectMocks
    private ContaService contaService;

    Cliente maria;

    @BeforeEach
     void setUp() {
        MockitoAnnotations.openMocks(this);
        maria = new Cliente("maira", "11");
    }
    /* 
    @AfterAll
    @DisplayName("Clean up Tests")
    @Disabled
    void cleanUp() {

    }
*/
     // test [System Under Test]_[Condition or State Change]_[Expected Result]
    @Test
    @DisplayName("testDeposit_When_BalancesIsInsufficient_ShouldReturnTheDeposit")
    void testDeposit_When_BalancesIsInsufficient_ShouldReturnTheDeposit() {
        // AAA-Arrange-Act-Assert
        // Given - create or difine variables for test
        ContaBase conta = new ContaNormal(1001, maria);

        // When - invoke methods/actions for test
        conta.depositar(200);

        // Then - apply assertions for tests
        assertEquals(200, conta.getSaldo(), 0.001, "Deve depositar corretamente");
        assertNotEquals(300, conta.getSaldo(), 0.001, "Deve não esperar um valor diferente");
        assertNotNull(conta, "Deve existir objeto");
    }

    @Test
    @DisplayName("testValidated_When_Balance_ShouldReturnBalance")
    void testValidated_When_Balance_ShouldReturnBalance() {
        ContaBase conta = new ContaNormal(1001, maria);

        conta.depositar(200);

        assertEquals(200, conta.getSaldo());
    }

    @Test
    @DisplayName("testException_When_Balance_ShouldReturnSaldoInsuficienteExcpetion")
    void testException_When_Balance_ShouldReturnSaldoInsuficienteExcpetion() throws SaldoInsuficienteException {

        int origem = 1002;
        int destino = 1003;
        double valor = 20.15;

        Conta contaOrigem = new Conta(1002, 10.0);
        Conta contaDestino = new Conta(1003, 40.0);

        Mockito.when(contaRepository.buscarPorNumero(origem)).thenReturn(contaOrigem);
        Mockito.when(contaRepository.buscarPorNumero(destino)).thenReturn(contaDestino);

        assertThrows(SaldoInsuficienteException.class,
                () -> contaService.transferir(
                        contaOrigem.getNumero(),
                        contaDestino.getNumero(),
                        valor), "Deve gerar  execption: SaldoInsuficienteException");

                        
    }

}
