package kats

import kats.kernel.Monoid
import kats.kernel.Semigroup
import kats.kinds.Kind1

interface MonoidK<F> : SemigroupK<F> {
    fun <A> empty(): Kind1<F, A>

    override fun <A> algebra(): Monoid<Kind1<F, A>> = object : Monoid<Kind1<F, A>> {
        override val empty: Kind1<F, A>
                get() = this.empty

        override fun combine(a1: Kind1<F, A>, a2: Kind1<F, A>): Kind1<F, A> =
                this@MonoidK.combineK(a1, a2)
    }
}