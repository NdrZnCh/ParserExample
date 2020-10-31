package expr

interface Expr<T> {
    fun eval(): T
}