package expr

import expr.arithmetic.PlusExpr
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

internal class PlusExprTest {

    @Test
    fun evaluate() {
        val expr = PlusExpr(NumberExpr(1.1), NumberExpr(2.2))

        assertEquals(expr.evaluate().toDouble(), 3.3)
    }
}