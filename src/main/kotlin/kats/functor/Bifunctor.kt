package kats.functor

import kats.kinds.K2

interface Bifunctor<F> {
    fun <A, B, C, D> bimap(fab: K2<F, A, B>, f: (A) -> C, g: (B) -> D): K2<F, C, D>
}