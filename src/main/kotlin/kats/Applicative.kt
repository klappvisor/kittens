package kats

import kats.kinds.K1

interface Applicative<F> : Apply<F> {
    fun <A> pure(a: A): K1<F, A>

    fun unit(): K1<F, Unit> = pure(Unit)

    override fun <A, B> map(fa: K1<F, A>, f: (A) -> B): K1<F, B> = ap(fa, pure(f))
}