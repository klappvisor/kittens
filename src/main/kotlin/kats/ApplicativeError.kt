package kats

import kats.function.andThen
import kats.kinds.Kind1

interface ApplicativeError<F, E> : Applicative<F> {
    fun <A> raiseError(e: E): Kind1<F, A>

    fun <A> handleErrorWith(fa: Kind1<F, A>, f: (E) -> Kind1<F, A>): Kind1<F, A>

    fun <A> handleError(fa: Kind1<F, A>, f: (E) -> A): Kind1<F, A> =
            handleErrorWith(fa) { x -> (f andThen { pure(it) })(x) }
}