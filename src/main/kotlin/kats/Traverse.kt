package kats

import kats.kinds.Kind1

interface Traverse<F> : Foldable<F>, Functor<F> {
    fun <G, A, B> traverse(fa: Kind1<F, A>, app: Applicative<G>, f: (A) -> Kind1<G, B>): Kind1<G, Kind1<F, B>>

    fun <G, A> sequence(fga: Kind1<F, Kind1<G, A>>, app: Applicative<G>): Kind1<G, Kind1<F, A>> =
            traverse(fga, app) { it }
}