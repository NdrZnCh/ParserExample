package parser

import expr.AbstractExpr
import expr.NumberExpr
import expr.arithmetic.DivisionExpr
import expr.arithmetic.MinusExpr
import expr.arithmetic.MultiplicationExpr
import expr.arithmetic.PlusExpr
import java.math.BigDecimal
import java.util.*

private val tokenizer: TokenizeFunc = { data ->
    LinkedList(data
        .replace(" ", "")
        .split(Regex("(?<=[-+*/()])|(?=[-+*/()])"))
        .filter { it != ""})
}

/**
 * Simple recursive descent parser for arithmetic expressions
 */
class ArithmeticParser(data: String) : AbstractParser<BigDecimal>(data, tokenizer) {

    override fun parse(): AbstractExpr<BigDecimal> = parseExpression()

    /**
     *  expression ::= term | term + expression | term - expression
     */
    private fun parseExpression(): AbstractExpr<BigDecimal> {
        val left = parseTerm(parseFactor())

        return when (tokens.peek()) {
            "+" -> consume { PlusExpr(left, parseExpression()) }
            "-" -> consume { MinusExpr(left, parseExpression()) }
            else -> parseTerm(left)
        }
    }

    /**
     * term ::= factor | term * term | term / term
     */
    private fun parseTerm(left: AbstractExpr<BigDecimal>): AbstractExpr<BigDecimal> = when (tokens.peek()) {
        "*" -> consume { MultiplicationExpr(left, parseTerm(parseFactor())) }
        "/" -> consume { DivisionExpr(left, parseTerm(parseFactor())) }
        else -> left
    }

    /**
     * factor ::= number | '(' expression ')'
     */
    private fun parseFactor(): AbstractExpr<BigDecimal> {
        val token = tokens.poll()

        return when {
            token == "(" -> {
                val expr = parseExpression()

                tokens.poll() // consume ')'

                return expr
            }
            token?.toDoubleOrNull() != null -> NumberExpr(token.toDouble())
            else -> throw Exception("$token is not a valid token")
        }
    }
}