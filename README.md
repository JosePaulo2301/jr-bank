Depósito:

1- Deve aumentar o saldo corretamente ao depositar um valor positivo.
Não deve permitir depósito de valor negativo (se houver validação).
Saque:

2- Deve permitir saque até o saldo + limite especial.
Deve lançar SaldoInsuficienteException se tentar sacar acima do saldo + limite especial.
Deve lançar IllegalArgumentException ao tentar sacar valor zero ou negativo.
Deve permitir saque parcial do saldo e usar o limite especial se necessário.
3- Getters:

Deve retornar corretamente o titular, número e saldo.
4- Integração com ContaBase:

Deve funcionar corretamente com diferentes implementações de ContaBase (ex: ContaNormal, ContaPoupanca).
5- Comportamento de limite especial:

Deve permitir saque usando parte do limite especial.
Deve impedir saque se ultrapassar o limite especial.
