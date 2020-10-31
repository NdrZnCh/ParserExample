package parser

import expr.AbstractExpr
import java.util.*

typealias TokenizeFunc = (data: String) -> Queue<String>

abstract class AbstractParser<T>(data: String, tokenizer: TokenizeFunc) {

    protected val tokens: Queue<String> = tokenizer(data)

    /**
     * @return root expression
     */
    abstract fun parse(): AbstractExpr<T>

    protected fun consume(func: () -> AbstractExpr<T>): AbstractExpr<T> {
        tokens.poll()
        return func()
    }
}