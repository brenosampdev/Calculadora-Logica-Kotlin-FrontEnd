package logicFunctions


import data.Term
import data.TokensExpr
import helpers.isOperator
import java.util.Stack

object RPNConverter {
    fun toRPN(tokens: List<TokensExpr>): List<TokensExpr> {
        val output = mutableListOf<TokensExpr>()
        val stack = Stack<TokensExpr>()

        for (token in tokens) {
            when {
                token == TokensExpr.TERM -> output.add(token)
                isOperator(token) -> {
                    while (stack.isNotEmpty() &&
                        isOperator(stack.peek()) &&
                        stack.peek().order >= token.order) {
                        output.add(stack.pop())
                    }
                    stack.push(token)
                }
                token == TokensExpr.OPEN_PARENTHESE -> stack.push(token)
                token == TokensExpr.CLOSE_PARENTHESE -> {
                    while (stack.isNotEmpty() && stack.peek() != TokensExpr.OPEN_PARENTHESE) {
                        output.add(stack.pop())
                    }
                    if (stack.isNotEmpty() && stack.peek() == TokensExpr.OPEN_PARENTHESE) {
                        stack.pop()
                    }
                }
            }
        }
        while (stack.isNotEmpty()) {
            output.add(stack.pop())
        }
        return output
    }
}
