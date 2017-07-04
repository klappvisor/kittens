package kats

import kats.kinds.K1

interface MonadReader<F, R> : Monad<F> {
    fun ask(): K1<F, R>

    fun <A> local(fa: K1<F, A>, f: (R) -> R): K1<F, A>

    fun <A> reader(f: (R) -> A): K1<F, A> = map(ask(), f)
}