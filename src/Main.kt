data class Requisito(
    val mensagemErro: String,
    val validacao: (String) -> Boolean
)

fun somaDigitos(texto: String): Int {
    return texto.filter { it.isDigit() }.sumOf { it.digitToInt() }
}

fun ehPrimo(numero: Int): Boolean {
    if (numero < 2) return false
    for (i in 2 until numero) {
        if (numero % i == 0) return false
    }
    return true
}

fun todosNumerosPrimos(senha: String): Boolean {
    return senha.filter { it.isDigit() }.all { ehPrimo(it.digitToInt()) }
}

fun main() {
    println("🔐 ========== THE PASSWORD OVERLORD ==========")
    println("Bem-vindo ao desafio máximo de senhas!")
    println("============================================\n")

    val listaDeRegras = listOf(
        Requisito("❌ Mínimo de 5 caracteres obrigatório!") { it.length >= 5 },
        Requisito("❌ Deve conter pelo menos uma letra MAIÚSCULA!") { it.any { char -> char.isUpperCase() } },
        Requisito("❌ Deve conter pelo menos um número!") { it.any { char -> char.isDigit() } },
        Requisito("❌ Deve conter o ano do Hexa (2026)!") { it.contains("2026") },
        Requisito("❌ Deve conter a bandeira do Brasil 🇧🇷!") { it.contains("🇧🇷") },
        Requisito("❌ Todos os números devem ser primos!") { todosNumerosPrimos(it) },
        Requisito("❌ A soma dos dígitos na senha deve ser >= 10!") { somaDigitos(it) >= 10 }
    )

    var senhaAprovada = false

    do {
        println("\n📝 Digite sua senha:")
        val entrada = readLine() ?: ""

        var erroEncontrado: String? = null

        for (regra in listaDeRegras) {
            if (!regra.validacao(entrada)) {
                erroEncontrado = regra.mensagemErro
                break
            }
        }

        if (erroEncontrado != null) {
            println(erroEncontrado)
        } else {
            println("\n✅ ✅ ✅ SUCESSO! ✅ ✅ ✅")
            println("Sua senha foi ACEITA pelo OVERLORD!")
            println("Parabéns! Você dominou o desafio! 🏆\n")
            senhaAprovada = true
        }
    } while (!senhaAprovada)
}