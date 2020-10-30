package expr

import java.math.BigDecimal
import java.math.RoundingMode

class NumberExpr(private val value: Double) : AbstractExpr<BigDecimal>() {
    override fun evaluate(): BigDecimal = BigDecimal(value).setScale(2, RoundingMode.HALF_EVEN)

    override fun toString(): String = evaluate().toString()
}