package kats

import kats.kinds.Kind1

/**
 * A monad that also allows you to raise and or handle an error value.
 *
 * This type class allows one to abstract over error-handling monads.
 */
interface MonadError<F, E> : ApplicativeError<F, E>, Monad<F> {
    /**
     * Turns a successful value into an error if it does not satisfy a given predicate.
     */
    fun <A> ensure(fa: Kind1<F, A>, e: () -> E, predicate: (A) -> Boolean): Kind1<F, A> =
            flatMap(fa) { if (predicate(it)) pure(it) else raiseError(e()) }

    /**
     * Turns a successful value into an error specified by the `error` function if it does not satisfy a given predicate.
     */
    fun <A> ensureOr(fa: Kind1<F, A>, e: (A) -> E, predicate: (A) -> Boolean): Kind1<F, A> =
            flatMap(fa) { if (predicate(it)) pure(it) else raiseError(e(it)) }
}