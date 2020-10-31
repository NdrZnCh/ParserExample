package expr.arithmetic

import expr.Expr
import java.math.BigDecimal

class ArithmeticExpr(
    val left: Expr<BigDecimal>,
    val right: Expr<BigDecimal>,
    private val symbol: String,
    private val evalFunc: (ArithmeticExpr) -> BigDecimal
) : Expr<BigDecimal> {
    override fun eval(): BigDecimal = evalFunc(this)

    override fun toString(): String = "($left $symbol $right)"
}