package kats

import kats.kinds.Kind1

interface Applicative<F> : Apply<F> {
    fun <A> pure(a: A): Kind1<F, A>

    fun unit(): Kind1<F, Unit> = pure(Unit)

    override fun <A, B> map(fa: Kind1<F, A>, f: (A) -> B): Kind1<F, B> = ap(pure(f), fa)
}