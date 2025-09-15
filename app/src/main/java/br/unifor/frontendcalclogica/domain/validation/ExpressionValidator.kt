package br.unifor.frontendcalclogica.domain.validation

/**
 * Mini validação da expressão só para teste da navegação da UI.
 */
object ExpressionValidator {

    private val operators = setOf('→', '↔', '∧', '∨', '↓', '¬')

    fun isExpressionValid(expr: String): Boolean {
        if (expr.isBlank()) return false

        // Não terminar com operador binário/unário
        val last = expr.trim().last()
        if (last in operators) return false

        // Parênteses balanceados (abre e fecha na mesma quantidade e ordem)
        var bal = 0
        for (c in expr) {
            if (c == '(') bal++
            if (c == ')') {
                bal--
                if (bal < 0) return false // fechou mais do que abriu
            }
        }
        if (bal != 0) return false

        return true
    }
}
