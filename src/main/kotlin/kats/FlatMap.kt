package kats

import kats.kinds.Kind1

interface FlatMap<F> : Apply<F> {
    fun <A, B> flatMap(fa: Kind1<F, A>, f: (A) -> Kind1<F, B>): Kind1<F, B>

    /**
     * "flatten" a nested `F` of `F` structure into a single-layer `F` structure.
     *
     * This is also commonly called `join`.
     */
    fun <A> flatten(ffa: Kind1<F, Kind1<F, A>>): Kind1<F, A> =
            flatMap(ffa) { it }

    /** Sequentially compose two actions, discarding any value produced by the first. */
    fun <A, B> followedBy(fa: Kind1<F, A>, fb: Kind1<F, B>): Kind1<F, B> =
            flatMap(fa) { _ -> fb }

    /** Sequentially compose two actions, discarding any value produced by the second. */
    fun <A, B> forEffect(fa: Kind1<F, A>, fb: Kind1<F, B>): Kind1<F, A> =
            flatMap(fa) { a -> map(fb) { _ -> a } }

    override fun <A, B> ap(ff: Kind1<F, (A) -> B>, fa: Kind1<F, A>): Kind1<F, B> =
            flatMap(ff) { f -> map(fa, f) }

    override fun <A, B> product(fa: Kind1<F, A>, fb: Kind1<F, B>): Kind1<F, Pair<A, B>> =
            flatMap(fa) { a -> map(fb) { b -> Pair(a, b) } }

    /**
     * Pair `A` with the result of function application.
     */
    fun <A, B> mproduct(fa: Kind1<F, A>, f: (A) -> Kind1<F, B>): Kind1<F, Pair<A, B>> =
            flatMap(fa) { a -> map(f(a)) { Pair(a, it) } }
}