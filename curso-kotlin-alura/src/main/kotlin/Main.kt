fun main() {
    println("Bem-vindo ao Bytebank")

    var i = 0
    while (i < 5){
        val titular: String = "Alex $i"
        val numeroConta: Int = 1000 + i
        var saldo: Double = i + 10.0

        println("Titular: $titular")
        println("Número da Conta: $numeroConta")
        println("Saldo: $saldo")
        println()
        i++
    }

//    for (i in 5 downTo 1) {
//
//        if (i == 4) {
//            continue
//        }
//        val titular: String = "Alex $i"
//        val numeroConta: Int = 1000 + i
//        var saldo: Double = i + 10.0
//
//        println("Titular: $titular")
//        println("Número da Conta: $numeroConta")
//        println("Saldo: $saldo")
//        println()
//
////    testaCondicoes(saldo)
//
//    }
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