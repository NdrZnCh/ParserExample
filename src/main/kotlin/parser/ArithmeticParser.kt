package parser

import expr.Expr
import expr.NumberExpr
import expr.arithmetic.*
import java.math.BigDecimal
import java.util.*

private typealias CreateArithmeticExprFunc = (left: Expr<BigDecimal>, right: Expr<BigDecimal>) -> ArithmeticExpr

private val minus: CreateArithmeticExprFunc = { left, right ->
    ArithmeticExpr(left, right, "-") {
        it.left.eval() - it.right.eval()
    }
}

private val plus: CreateArithmeticExprFunc = { left, right ->
    ArithmeticExpr(left, right, "+") {
        it.left.eval() + it.right.eval()
    }
}

private val obelus: CreateArithmeticExprFunc = { left, right ->
    ArithmeticExpr(left, right, "/") {
        it.left.eval() / it.right.eval()
    }
}

private val times: CreateArithmeticExprFunc = { left, right ->
    ArithmeticExpr(left, right, "*") {
        it.left.eval() * it.right.eval()
    }
}

private val tokenizer: TokenizeFunc = { data ->
    LinkedList(data
        .replace(" ", "")
        .split(Regex("(?<=[-+*/()])|(?=[-+*/()])"))
        .filter { it != "" })
}

/**
 * Simple recursive descent parser for arithmetic expressions
 */
class ArithmeticParser(data: String) : AbstractParser<BigDecimal>(data, tokenizer) {

    override fun parse(): Expr<BigDecimal> = parseExpression()

    /**
     *  expression ::= term | term + expression | term - expression
     */
    private fun parseExpression(): Expr<BigDecimal> {
        val left = parseTerm(parseFactor())

        return when (tokens.peek()) {
            "+" -> consume { plus(left, parseExpression()) }
            "-" -> consume { minus(left, parseExpression()) }
            else -> parseTerm(left)
        }
    }

    /**
     * term ::= factor | term * term | term / term
     */
    private fun parseTerm(left: Expr<BigDecimal>): Expr<BigDecimal> = when (tokens.peek()) {
        "*" -> consume { times(left, parseTerm(parseFactor())) }
        "/" -> consume { obelus(left, parseTerm(parseFactor())) }
        else -> left
    }

    /**
     * factor ::= number | '(' expression ')'
     */
    private fun parseFactor(): Expr<BigDecimal> {
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