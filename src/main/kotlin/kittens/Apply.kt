package kittens

import kittens.kinds.K1

interface Apply<F> : Functor<F>, Cartesian<F> {
    fun <A, B> ap(fa: K1<F, A>, ffa: K1<F, (A) -> B>): K1<F, B>

    override fun <A, B> product(fa: K1<F, A>, fb: K1<F, B>): K1<F, Pair<A, B>> =
        ap(fb, map(fa) { a: A -> { b: B -> Pair(a, b) } })

    fun <A, B, C> ap2(ff: K1<F, (Pair<A, B>) -> C>, fa: K1<F, A>, fb: K1<F, B>) =
        map(product(fa, product(fb, ff))) { (a, p) -> val (b, f) = p; f(Pair(a, b)) }

    fun <A, B, C> map2(fa: K1<F, A>, fb: K1<F, B>, f: (Pair<A, B>) -> C) = map(product(fa, fb), f)
}