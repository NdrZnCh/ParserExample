package expr

import java.math.BigDecimal
import java.math.RoundingMode

class NumberExpr(private val value: Double) : Expr<BigDecimal> {
    override fun eval(): BigDecimal = BigDecimal(value).setScale(2, RoundingMode.HALF_EVEN)

    override fun toString(): String = eval().toString()
}