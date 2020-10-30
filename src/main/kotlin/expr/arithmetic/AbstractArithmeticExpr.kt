package expr.arithmetic

import expr.AbstractExpr
import java.math.BigDecimal

abstract class AbstractArithmeticExpr : AbstractExpr<BigDecimal>() {
    abstract val left: AbstractExpr<BigDecimal>
    abstract val right: AbstractExpr<BigDecimal>
    abstract val symbol: String
    abstract override fun evaluate(): BigDecimal

    override fun toString(): String = "($left $symbol $right)"
}