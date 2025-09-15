package helpers

import data.TokensExpr

fun isOperator(exp: TokensExpr?) =  when(exp){
    TokensExpr.OP_AND,
    TokensExpr.OP_OR,
    TokensExpr.OP_NOT,
    TokensExpr.OP_THEN,
    TokensExpr.OP_EXTHEN,
    TokensExpr.OP_EXOR -> true
    else -> false
}
