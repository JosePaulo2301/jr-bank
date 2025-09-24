package app;

import app.model.Cliente;
import app.model.Conta;
import app.model.ContaCorrente;
import app.model.ContaPoupanca;
import app.service.BancoService;

public class main {
    public static void main(String[] args) {
         BancoService banco = new BancoService();

        Cliente cliente1 = new Cliente("Maria", "11111111111");
        Conta conta1 = new ContaCorrente(1001, cliente1);

        Cliente cliente2 = new Cliente("João", "22222222222");
        Conta conta2 = new ContaPoupanca(1002, cliente2);

        banco.criarConta(conta1);
        banco.criarConta(conta2);

        banco.listarContas();
    }
}
