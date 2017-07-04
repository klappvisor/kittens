package kats

import kats.data.option.Option
import kats.kinds.K1

interface MonadFilter<F> : Monad<F>, FunctorFilter<F> {
    fun <A> empty(): K1<F, A>

    // TODO: review implementation
    override fun <A, B> mapFilter(fa: K1<F, A>, f: (A) -> Option<B>): K1<F, B> =
            flatMap(fa) { a -> f(a).fold(empty<B>()) { pure(it) } }
}