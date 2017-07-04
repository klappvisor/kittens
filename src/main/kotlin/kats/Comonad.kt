package kats

import kats.kinds.K1

/**
 * Comonad
 *
 * Comonad is the dual of Monad. Whereas Monads allow for the composition of effectful functions,
 * Comonads allow for composition of functions that extract the value from their context.
 *
 * Must obey the laws defined in cats.laws.ComonadLaws.
 */
interface Comonad<F> : CoflatMap<F> {
    /**
     * `extract` is the dual of `pure` on Monad (via `Applicative`)
     * and extracts the value from its context
     */
    fun <A> extract(x: K1<F, A>): A
}

