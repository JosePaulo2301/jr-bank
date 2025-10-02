package com.app.jrbank.model;

import com.app.jrbank.model.impls.ContaBase;
import com.app.jrbank.model.impls.ContaNormal;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class ContaNormalTest {
    @Test
    void deveDEpositarCorretamente() {
        Cliente maria = new Cliente("maira","11");
        ContaBase conta = new ContaNormal(1001,maria);

        conta.depositar(200);

        assertEquals(200, conta.getSaldo(), 0.001);
    }
}
