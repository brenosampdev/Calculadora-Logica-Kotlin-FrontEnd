package TruthTable

import data.ClassificationTable
import data.ResponseTable
import data.ResponseToken
import logicFunctions.RPNConverter
import logicFunctions.RPNEvaluator

object TruthTableExecutor {
    fun run(response: ResponseToken): ResponseTable {
        val rpn = RPNConverter.toRPN(response.listExpr)
        val assignments = TruthTableGenerator.allCombinations(response.listTermWithoutDuplicate)
        val results = assignments.map { RPNEvaluator.evaluate(rpn, it, response.listTerm) }

        // Imprime a tabela

        // Classificação
        val classify = when {
            results.all { it } -> ClassificationTable.TAUTOLOGY
            results.all { !it } -> ClassificationTable.CONTRADICTION
            else -> ClassificationTable.CONTINGENCY
        }

        return ResponseTable(assignments, results, classify)
    }

    fun printTruthTable(response: ResponseToken) {
        val table = this.run(response)

        if (table.assignments.isEmpty()) return

        // Cabeçalho
        val headers = table.assignments[0].keys.toList()
        println(headers.joinToString(" | ") + " | Result")
        println("-".repeat(headers.size * 6 + 8))

        // Linhas
        for (i in table.assignments.indices) {
            val values = headers.map { if (table.assignments[i][it] == true) "1" else "0" }
            println(values.joinToString(" | ") + " | " + if (table.results[i]) "1" else "0")
        }

        println("Classificado como: ${table.classify.value}")
    }

    fun generateTruthTable(response: ResponseToken): String {
        var truthTable = ""
        val table = this.run(response)

        if (table.assignments.isEmpty()) return truthTable

        // Cabeçalho
        val headers = table.assignments[0].keys.toList()
        truthTable += headers.joinToString(" | ") + " | Result \n"
        truthTable += "-".repeat(headers.size * 6 + 8)
        truthTable += "\n"

        // Linhas
        for (i in table.assignments.indices) {
            val values = headers.map { if (table.assignments[i][it] == true) "1" else "0" }
            truthTable += values.joinToString(" | ") + " | " + if (table.results[i]) "1" else "0"
            truthTable += "\n"
        }

        return truthTable
    }

}