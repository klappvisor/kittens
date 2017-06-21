package kats

import kats.kernel.Semigroup
import kats.kinds.Kind1

interface SemigroupK<F> {
    fun <A> combineK(fa1: Kind1<F, A>, fa2: Kind1<F, A>): Kind1<F, A>

    fun <A> algebra(): Semigroup<Kind1<F, A>> = object : Semigroup<Kind1<F, A>> {
        override fun combine(a1: Kind1<F, A>, a2: Kind1<F, A>): Kind1<F, A> =
                combineK(a1, a2)
    }
}