package kats

import kats.kinds.K1

interface FlatMap<F> : Apply<F> {
    fun <A, B> flatMap(fa: K1<F, A>, f: (A) -> K1<F, B>): K1<F, B>

    /**
     * "flatten" a nested `F` of `F` structure into a single-layer `F` structure.
     *
     * This is also commonly called `join`.
     */
    fun <A> flatten(ffa: K1<F, K1<F, A>>): K1<F, A> =
            flatMap(ffa) { it }

    /** Sequentially compose two actions, discarding any value produced by the first. */
    fun <A, B> followedBy(fa: K1<F, A>, fb: K1<F, B>): K1<F, B> =
            flatMap(fa) { _ -> fb }

    /** Sequentially compose two actions, discarding any value produced by the second. */
    fun <A, B> forEffect(fa: K1<F, A>, fb: K1<F, B>): K1<F, A> =
            flatMap(fa) { a -> map(fb) { _ -> a } }

    override fun <A, B> ap(fa: K1<F, A>, ffa: K1<F, (A) -> B>): K1<F, B> =
            flatMap(ffa) { f -> map(fa, f) }

    override fun <A, B> product(fa: K1<F, A>, fb: K1<F, B>): K1<F, Pair<A, B>> =
            flatMap(fa) { a -> map(fb) { b -> Pair(a, b) } }

    /**
     * Pair `A` with the result of function application.
     */
    fun <A, B> mproduct(fa: K1<F, A>, f: (A) -> K1<F, B>): K1<F, Pair<A, B>> =
            flatMap(fa) { a -> map(f(a)) { Pair(a, it) } }
}