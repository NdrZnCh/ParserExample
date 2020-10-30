package expr

import expr.arithmetic.MultiplicationExpr
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

internal class MultiplicationExprTest {

    @Test
    fun evaluate() {
        val expr = MultiplicationExpr(NumberExpr(1.1), NumberExpr(2.2))

        assertEquals(expr.evaluate().toDouble(), 2.42)
    }
}