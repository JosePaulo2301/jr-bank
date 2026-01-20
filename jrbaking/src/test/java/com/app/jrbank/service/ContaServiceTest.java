package com.app.jrbank.service;

import com.app.jrbank.exception.SaldoInsuficienteException;
import com.app.jrbank.model.Conta;
import com.app.jrbank.repository.ContaRepository;
import com.app.jrbank.service.ContaService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.context.annotation.Description;

import static org.junit.jupiter.api.Assertions.*;

public class ContaServiceTest {

    @Mock
    private ContaRepository contaRepository;

    private ContaService contaService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        contaService = new ContaService(contaRepository);
    }

    @Test
    void transferir_DeveLancarExcecao_QuandoContaOrigemNaoExiste() {
        Mockito.when(contaRepository.buscarPorNumero(1)).thenReturn(null);
        Mockito.when(contaRepository.buscarPorNumero(2)).thenReturn(new Conta(2, 100.0));

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            contaService.transferir(1, 2, 50.0);
        });

        assertEquals("Conta inexistente", exception.getMessage());
    }

    @Test
    void transferir_DeveLancarExcecao_QuandoContaDestinoNaoExiste() {
        Mockito.when(contaRepository.buscarPorNumero(1)).thenReturn(new Conta(1, 100.0));
        Mockito.when(contaRepository.buscarPorNumero(2)).thenReturn(null);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            contaService.transferir(1, 2, 50.0);
        });

        assertEquals("Conta inexistente", exception.getMessage());
    }

    @Test
    void transferir_DeveLancarExcecao_QuandoSaldoInsuficiente() {
        Conta contaOrigem = new Conta(1, 30.0);
        Conta contaDestino = new Conta(2, 100.0);

        Mockito.when(contaRepository.buscarPorNumero(1)).thenReturn(contaOrigem);
        Mockito.when(contaRepository.buscarPorNumero(2)).thenReturn(contaDestino);

        Exception exception = assertThrows(SaldoInsuficienteException.class, () -> {
            contaService.transferir(1, 2, 50.0);
        });

        assertEquals("Saldo insuficiente para a transferencia", exception.getMessage());
    }

    @Test
    @Description("asdf")
    
    void transferir_DeveTransferirValor_QuandoSaldoSuficiente() throws SaldoInsuficienteException {
        Conta contaOrigem = new Conta(1, 100.0);
        Conta contaDestino = new Conta(2, 50.0);

        Mockito.when(contaRepository.buscarPorNumero(1)).thenReturn(contaOrigem);
        Mockito.when(contaRepository.buscarPorNumero(2)).thenReturn(contaDestino);

        contaService.transferir(1, 2, 40.0);

        assertEquals(60.0, contaOrigem.getSaldo());
        assertEquals(90.0, contaDestino.getSaldo());
        Mockito.verify(contaRepository).salvar(contaOrigem);
        Mockito.verify(contaRepository).salvar(contaDestino);
    }
}
