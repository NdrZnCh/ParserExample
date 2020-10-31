package parser

import expr.Expr
import java.util.*

typealias TokenizeFunc = (data: String) -> Queue<String>

abstract class AbstractParser<T>(data: String, tokenizer: TokenizeFunc) {

    protected val tokens: Queue<String> = tokenizer(data)

    /**
     * @return root expression
     */
    abstract fun parse(): Expr<T>

    protected fun consume(func: () -> Expr<T>): Expr<T> {
        tokens.poll()
        return func()
    }
}