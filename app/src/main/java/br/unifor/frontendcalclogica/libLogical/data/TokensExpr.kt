package data

enum class TokensExpr(val order: Int) {
    OPEN_PARENTHESE(5),
    CLOSE_PARENTHESE(5),
    OP_NOT(4),
    OP_AND(3),
    OP_OR(2),
    OP_EXOR(2),
    OP_THEN(1),
    OP_EXTHEN(1),
    TERM(-1),
    UNDEFINED(-1)
}