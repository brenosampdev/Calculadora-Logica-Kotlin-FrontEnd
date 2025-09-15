package TruthTable

import data.Term

object TruthTableGenerator {
    fun allCombinations(terms: List<Term>): List<Map<String, Boolean>> {
        val result = mutableListOf<Map<String, Boolean>>()
        val n = terms.size
        val total = Math.pow(2.0, n.toDouble())  // 2^n combinações

        for (i in 0 until total.toInt()) {
            val assignment = mutableMapOf<String, Boolean>()
            for (j in 0 until n) {
                val value = (i shr j) and 1 == 1
                assignment[terms[j].byName] = value
            }
            result.add(assignment)
        }
        return result
    }
}