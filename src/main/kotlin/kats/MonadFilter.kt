package kats

import kats.data.option.Option
import kats.kinds.Kind1

interface MonadFilter<F> : Monad<F>, FunctorFilter<F> {
    fun <A> empty(): Kind1<F, A>

    // TODO: review implementation
    override fun <A, B> mapFilter(fa: Kind1<F, A>, f: (A) -> Option<B>): Kind1<F, B> =
            flatMap(fa) { a -> f(a).fold(empty<B>()) { pure(it) } }
}