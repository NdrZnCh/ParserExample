package parser

import expr.AbstractExpr
import expr.NumberExpr
import expr.arithmetic.DivisionExpr
import expr.arithmetic.MinusExpr
import expr.arithmetic.MultiplicationExpr
import expr.arithmetic.PlusExpr
import java.math.BigDecimal

/**
 * Simple recursive descent parser for arithmetic expressions
 */
class ArithmeticParser(data: String) : AbstractParser<BigDecimal>(data) {

    override fun parse(): AbstractExpr<BigDecimal> = parseExpression()

    /**
     *  expression ::= term | term + expression | term - expression
     */
    private fun parseExpression(): AbstractExpr<BigDecimal> {
        val left = parseTerm(parseNumber())

        return when (tokens.peek()) {
            "+" -> consume { PlusExpr(left, parseExpression()) }
            "-" -> consume { MinusExpr(left, parseExpression()) }
            else -> parseTerm(left)
        }
    }

    /**
     * term ::= number | term * term | term / term
     */
    private fun parseTerm(left: AbstractExpr<BigDecimal>): AbstractExpr<BigDecimal> = when (tokens.peek()) {
        "*" -> consume { MultiplicationExpr(left, parseTerm(parseNumber())) }
        "/" -> consume { DivisionExpr(left, parseTerm(parseNumber())) }
        else -> left
    }

    private fun parseNumber(): AbstractExpr<BigDecimal> {
        val token = tokens.pollFirst()

        if (token?.toDoubleOrNull() != null) {
            return NumberExpr(token.toDouble())
        } else throw Exception("$token is not a valid token")
    }
}