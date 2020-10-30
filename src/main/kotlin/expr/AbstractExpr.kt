package expr

abstract class AbstractExpr<T> {
    abstract fun evaluate(): T
}