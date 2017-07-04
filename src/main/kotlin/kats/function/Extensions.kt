package kats.function

infix fun <A, B, C> ((B) -> C).compose(f: (A) -> B): (A) -> C = { this(f(it)) }

infix fun <A, B, C> ((A) -> B).andThen(g: (B) -> C): (A) -> C = { g(this(it)) }

fun <A> identity(a: A): A = a