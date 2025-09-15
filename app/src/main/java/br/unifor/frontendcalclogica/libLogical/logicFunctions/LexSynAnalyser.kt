package logicFunctions

import data.ResponseToken
import data.TokensExpr
import helpers.isEqualNotOperator
import helpers.isOperator

object LexSynAnalyser {

    fun run(tokenizer: ResponseToken): Boolean {
        var beforeExp: TokensExpr? = null
        var afterExp: TokensExpr? = null

        if(tokenizer.countOpenParenthese != tokenizer.countCloseParenthese){
            println("Erro de sintaxe parenteses inconsistentes")
            return false
        }

        for (i in 0 until tokenizer.listExpr.size) {
            var token = tokenizer.listExpr[i]
            afterExp = if(tokenizer.listExpr.size != i + 1) tokenizer.listExpr[i + 1] else null

            val isValid = when {
                token == TokensExpr.OPEN_PARENTHESE
                        && (afterExp == TokensExpr.TERM || isEqualNotOperator(afterExp) || afterExp == TokensExpr.OPEN_PARENTHESE)
                        && (beforeExp == null || isOperator(beforeExp) || beforeExp == TokensExpr.OPEN_PARENTHESE) -> true

                token == TokensExpr.TERM
                        && (isOperator(afterExp) || afterExp == TokensExpr.CLOSE_PARENTHESE || afterExp == null)
                        && (beforeExp == null || beforeExp == TokensExpr.OPEN_PARENTHESE || isOperator(beforeExp)) -> true

                isOperator(token)
                        && (afterExp == TokensExpr.TERM || afterExp == TokensExpr.OPEN_PARENTHESE || isEqualNotOperator(afterExp))
                        && (beforeExp == TokensExpr.TERM || beforeExp == TokensExpr.CLOSE_PARENTHESE) -> true

                isEqualNotOperator(token)
                        && (afterExp == TokensExpr.TERM || afterExp == TokensExpr.OPEN_PARENTHESE)
                        && (beforeExp == TokensExpr.OPEN_PARENTHESE || isOperator(beforeExp) ||beforeExp == null)  -> true


                token == TokensExpr.CLOSE_PARENTHESE
                        && (afterExp == null || isOperator(afterExp) || afterExp == TokensExpr.CLOSE_PARENTHESE)
                        && (beforeExp == TokensExpr.TERM || beforeExp == TokensExpr.CLOSE_PARENTHESE) -> true

                else -> false
            }

            if (!isValid) {
                println("Erro de sintaxe em: $token (anterior: $beforeExp)")
                return false
            }

            println("OK: antes de $token vem $beforeExp")
            println("OK: depois de $token vem $afterExp")
            beforeExp = token
        }

        return true
    }
}