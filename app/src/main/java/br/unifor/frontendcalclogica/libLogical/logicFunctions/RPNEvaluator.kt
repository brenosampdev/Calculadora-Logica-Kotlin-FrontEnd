package logicFunctions

import data.Term
import data.TokensExpr
import java.util.Stack

object RPNEvaluator {
    fun evaluate(rpn: List<TokensExpr>, assignment: Map<String, Boolean>, terms: List<Term>): Boolean {
        val stack = Stack<Boolean>()
        var termIndex = 0
        for (token in rpn) {
            when {
                token == TokensExpr.TERM -> {
                    val name = terms[termIndex].byName
                    stack.push(assignment[name] ?: false)
                    termIndex++

                    // reinicia o index quando para duplicatas
                    if(termIndex == terms.size){
                        termIndex = 0
                    }
                }
                token == TokensExpr.OP_NOT -> {
                    val a = stack.pop()
                    stack.push(Operators.not(a))
                }
                token == TokensExpr.OP_AND -> {
                    val b = stack.pop()
                    val a = stack.pop()
                    stack.push(Operators.and(a, b))
                }
                token == TokensExpr.OP_OR -> {
                    val b = stack.pop()
                    val a = stack.pop()
                    stack.push(Operators.or(a, b))
                }
                token == TokensExpr.OP_THEN -> {
                    val b = stack.pop()
                    val a = stack.pop()
                    stack.push(Operators.than(a, b))
                }
                token == TokensExpr.OP_EXTHEN -> {
                    val b = stack.pop()
                    val a = stack.pop()
                    stack.push(Operators.exThan(a, b))
                }
                token == TokensExpr.OP_EXOR -> {
                    val b = stack.pop()
                    val a = stack.pop()
                    stack.push(Operators.exOr(a, b))
                }
            }
        }
        return stack.pop()
    }
}