package logicFunctions

object Operators {
    fun and(a: Boolean, b: Boolean) = a && b
    fun or(a: Boolean, b: Boolean) = a || b
    fun not(a: Boolean) = !a
    fun than(a: Boolean, b: Boolean) = or(not(a), b)
    fun exThan(a: Boolean, b: Boolean) = and(than(a, b), than(b, a))
    fun exOr(a: Boolean, b: Boolean) = a xor b
}