//package com.app.jrbank.service;
//
//import com.app.jrbank.dao.ContaDAO;
//import com.app.jrbank.model.Conta;
//
//public class BancoService {
//    private final ContaDAO contaDAO = new ContaDAO();
//
//    public void criarConta(Conta conta) {
//        contaDAO.salvar(conta);
//        System.out.println("Conta criada: " + conta);
//    }
//
//    public Conta buscarConta(int numero) {
//        return contaDAO.listar()
//            .stream()
//            .filter(conta -> conta.getNumero() == numero)
//            .findFirst()
//            .orElse(null);
//    }
//
//    public void listarContas() {
//        contaDAO.listar().forEach(System.out::println);
//    }
//}
