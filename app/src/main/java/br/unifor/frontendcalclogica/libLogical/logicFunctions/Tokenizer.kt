package logicFunctions

import data.ResponseToken
import data.Term
import data.TokensExpr

object Tokenizer {
    fun generate(exp: String): ResponseToken {
        val listOfExpressionOrder: MutableList<TokensExpr> = mutableListOf()
        val listOfTerm: MutableList<Term> = mutableListOf()
        val listOfTermWithoutDuplicate: MutableList<Term> = mutableListOf()
        var countOpen: Int = 0
        var countClose: Int = 0

        var listTermsString: MutableList<String> = mutableListOf()

        // separa a expressao com uma lista de cada caractere
        val expInArr = exp.replace(" ", "").trim().split("").drop(1).dropLast(1)

        // lista tokenizada
        for(exp in expInArr){
            when(exp){
                "∧" -> listOfExpressionOrder.add(TokensExpr.OP_AND)
                "→" -> listOfExpressionOrder.add(TokensExpr.OP_THEN)
                "∨" -> listOfExpressionOrder.add(TokensExpr.OP_OR)
                "~" -> listOfExpressionOrder.add(TokensExpr.OP_NOT)
                "↔" -> listOfExpressionOrder.add(TokensExpr.OP_EXTHEN)
                "⊻" -> listOfExpressionOrder.add(TokensExpr.OP_EXOR)
                "(" -> {
                    countOpen += 1
                    listOfExpressionOrder.add(TokensExpr.OPEN_PARENTHESE)
                }
                ")" -> {
                    countClose += 1
                    listOfExpressionOrder.add(TokensExpr.CLOSE_PARENTHESE)
                }
                else -> {
                    if (exp.matches(Regex("^[A-Z]$"))) {
                        val term = Term(exp)
                        listOfExpressionOrder.add(TokensExpr.TERM)
                        listOfTerm.add(term)

                        // Nao adiciona duplicatas n
                        if(!listTermsString.contains(exp)){
                            listOfTermWithoutDuplicate.add(term)
                            listTermsString.add(exp)
                        }
                    } else {
                        listOfExpressionOrder.add(TokensExpr.UNDEFINED)
                    }
                }
            }
        }

        return ResponseToken(listOfExpressionOrder, listOfTermWithoutDuplicate, listOfTerm, countOpen, countClose)
    }
}