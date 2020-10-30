package expr

import expr.arithmetic.MinusExpr
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class MinusExprTest {

    @Test
    fun evaluate() {
        val expr = MinusExpr(NumberExpr(2.2), NumberExpr(1.1))

        assertEquals(expr.evaluate().toDouble(), 1.1)
    }
}