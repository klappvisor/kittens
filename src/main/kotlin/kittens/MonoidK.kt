package kittens

import kittens.kernel.Monoid
import kittens.kinds.K1

interface MonoidK<F> : SemigroupK<F> {
    fun <A> empty(): K1<F, A>

    override fun <A> algebra(): Monoid<K1<F, A>> = object : Monoid<K1<F, A>> {
        override val empty: K1<F, A>
                get() = this.empty

        override fun combine(a1: K1<F, A>, a2: K1<F, A>): K1<F, A> =
                this@MonoidK.combineK(a1, a2)
    }
}