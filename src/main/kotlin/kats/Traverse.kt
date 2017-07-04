package kats

import kats.kinds.K1

interface Traverse<F> : Foldable<F>, Functor<F> {
    fun <G, A, B> traverse(fa: K1<F, A>, app: Applicative<G>, f: (A) -> K1<G, B>): K1<G, K1<F, B>>

    fun <G, A> sequence(fga: K1<F, K1<G, A>>, app: Applicative<G>): K1<G, K1<F, A>> =
            traverse(fga, app) { it }
}