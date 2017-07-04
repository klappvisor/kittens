package kats

import kats.function.andThen
import kats.kinds.K1

interface ApplicativeError<F, E> : Applicative<F> {
    fun <A> raiseError(e: E): K1<F, A>

    fun <A> handleErrorWith(fa: K1<F, A>, f: (E) -> K1<F, A>): K1<F, A>

    fun <A> handleError(fa: K1<F, A>, f: (E) -> A): K1<F, A> =
            handleErrorWith(fa) { x -> (f andThen { pure(it) })(x) }
}