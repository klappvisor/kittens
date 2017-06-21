package kats

import kats.kinds.Kind1

interface Apply<F> : Functor<F>, Cartesian<F> {
    fun <A, B> ap(ff: Kind1<F, (A) -> B>, fa: Kind1<F, A>): Kind1<F, B>

    override fun <A, B> product(fa: Kind1<F, A>, fb: Kind1<F, B>): Kind1<F, Pair<A, B>> =
        ap(map(fa) { a: A -> { b: B -> Pair(a, b) } }, fb)

    fun <A, B, C> ap2(ff: Kind1<F, (Pair<A, B>) -> C>, fa: Kind1<F, A>, fb: Kind1<F, B>) =
        map(product(fa, product(fb, ff))) { (a, p) -> val (b, f) = p; f(Pair(a, b)) }

    fun <A, B, C> map2(fa: Kind1<F, A>, fb: Kind1<F, B>, f: (Pair<A, B>) -> C) = map(product(fa, fb), f)
}