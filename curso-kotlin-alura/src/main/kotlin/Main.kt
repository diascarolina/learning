@file:Suppress("SpellCheckingInspection")

fun main() {
    println("Bem-vindo ao Bytebank")

    val contaAlex = Conta(titular = "Alex", numero = 1000)
    contaAlex.deposita(200.0)

    println(contaAlex.titular)
    println(contaAlex.numero)
    println(contaAlex.saldo)

    val contaFran = Conta("Fran", 1001)
    contaFran.deposita(300.0)

    println(contaFran.titular)
    println(contaFran.numero)
    println(contaFran.saldo)

    println("Depositando na conta do Alex")
    contaAlex.deposita(50.0)
    println(contaAlex.saldo)

    println("Depositando na conta da Fran")
    contaFran.deposita(70.0)
    println(contaFran.saldo)

    println("Sacando da conta do Alex")
    contaAlex.saca(70.0)
    println(contaAlex.saldo)

    println("Transferindo...")
    contaFran.transfere(valor = 50.0, destino = contaAlex)
    println("Saldo da Fran: ${contaFran.saldo}")
    println("Saldo do Alex: ${contaAlex.saldo}")
}

class Conta(var titular: String,
            val numero: Int) {
    var saldo = 0.0
        private set

    fun deposita(valor: Double) {
        if (valor > 0) {
            saldo += valor
        }
    }

    fun saca(valor: Double) {
        if (saldo >= valor) {
            saldo -= valor
        } else {
            println("Valor insuficiente para saque.")
        }
    }

    fun transfere(valor: Double, destino: Conta): Boolean {
        if (saldo >= valor) {
            saldo -= valor
            destino.deposita(valor)
            return true
        }
        return false
    }
}

fun testaCopiasEReferencias() {
    val numeroX = 10
    var numeroY = numeroX
    numeroY++

    println("Número X = $numeroX \t Número Y = $numeroY")

    val contaJoao = Conta("João", 1002)
    val contaMaria = contaJoao
    contaMaria.titular = "Maria"

    println("Titular conta joao: ${contaJoao.titular}")
    println("Titular conta maria: ${contaMaria.titular}")

    println(contaJoao)
    println(contaMaria)
}

fun testaLacos() {
    var i = 0
    while (i < 5) {
        val titular: String = "Alex $i"
        val numeroConta: Int = 1000 + i
        val saldo: Double = i + 10.0

        println("Titular: $titular")
        println("Número da Conta: $numeroConta")
        println("Saldo: $saldo")
        println()
        i++
    }

    for (j: Int in 5 downTo 1) {

        if (j == 4) {
            continue
        }
        val titular: String = "Alex $j"
        val numeroConta: Int = 1000 + j
        val saldo: Double = j + 10.0

        println("Titular: $titular")
        println("Número da Conta: $numeroConta")
        println("Saldo: $saldo")
        println()
    }
}

fun testaCondicoes(saldo: Double) {
    if (saldo > 0.0) {
        println("Conta é positiva.")
    } else if (saldo == 0.0) {
        println("Conta é neutra.")
    } else {
        println("Conta é negativa.")
    }

    // o if acima é equivalente ao else abaixo

    when {
        saldo > 0.0 -> println("Conta é positiva.")
        saldo == 0.0 -> println("Conta é neutra.")
        else -> println("Conta é negativa.")
    }
}
