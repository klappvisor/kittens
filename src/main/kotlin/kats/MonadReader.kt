package kats

import kats.kinds.Kind1

interface MonadReader<F, R> : Monad<F> {
    fun ask(): Kind1<F, R>

    fun <A> local(fa: Kind1<F, A>, f: (R) -> R): Kind1<F, A>

    fun <A> reader(f: (R) -> A): Kind1<F, A> = map(ask(), f)
}