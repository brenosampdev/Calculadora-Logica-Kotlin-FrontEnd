package helpers

import data.TokensExpr

fun isEqualNotOperator(exp: TokensExpr?) =  when(exp){
    TokensExpr.OP_NOT -> true
    else -> false
}