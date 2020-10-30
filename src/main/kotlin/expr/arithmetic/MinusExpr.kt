package expr.arithmetic

import expr.AbstractExpr
import java.math.BigDecimal

class MinusExpr(
    override val left: AbstractExpr<BigDecimal>,
    override val right: AbstractExpr<BigDecimal>
) : AbstractArithmeticExpr() {

    override val symbol: String = "-"

    override fun evaluate(): BigDecimal = this.left.evaluate() - this.right.evaluate()
}