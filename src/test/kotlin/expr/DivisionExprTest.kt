package expr

import expr.arithmetic.DivisionExpr
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class DivisionExprTest {

    @Test
    fun evaluate() {
        val expr = DivisionExpr(NumberExpr(10.0), NumberExpr(5.0))

        assertEquals(expr.evaluate().toDouble(), 2.0)
    }
}