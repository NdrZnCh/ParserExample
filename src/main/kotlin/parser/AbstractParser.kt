package parser

import expr.AbstractExpr
import java.util.*

abstract class AbstractParser<T>(data: String) {

    protected val tokens = LinkedList(StringTokenizer(data).toList().map { it.toString() })

    /**
     * @return root expression
     */
    abstract fun parse(): AbstractExpr<T>

    protected fun consume(func: () -> AbstractExpr<T>): AbstractExpr<T> {
        tokens.poll()
        return func()
    }
}