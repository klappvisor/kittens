package kats.functor

import kats.kinds.Kind2

interface Bifunctor<F> {
    fun <A, B, C, D> bimap(fab: Kind2<F, A, B>, f: (A) -> C, g: (B) -> D): Kind2<F, C, D>
}