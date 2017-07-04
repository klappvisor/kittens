package kats

import kats.kernel.Monoid
import kats.kinds.K1

interface Foldable<F> {
    fun <A> fold(fa: K1<F, A>, m: Monoid<A>): A =
            foldLeft(fa, m.empty) { x, y -> m.combine(x, y) }

    fun <A, B> foldLeft(fa: K1<F, A>, b: B, f: (B, A) -> B): B

    fun <A, B> foldRight(fa: K1<F, A>, b: Lazy<B>, f: (A, Lazy<B>) -> Lazy<B>): Lazy<B>
}