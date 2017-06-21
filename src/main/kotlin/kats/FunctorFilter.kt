package kats

import kats.data.option.Option
import kats.kinds.Kind1

interface FunctorFilter<F> : Functor<F> {
    fun <A, B> mapFilter(fa: Kind1<F, A>, f: (A) -> Option<B>): Kind1<F, B>

    fun <A, B> collect(fa: Kind1<F, A>, f: (A) -> Option<B>): Kind1<F, B> =
            mapFilter(fa, f)

    fun <A> filter(fa: Kind1<F, A>, f: (A) -> Boolean): Kind1<F, A> =
            mapFilter(fa) { a -> if(f(a)) Option.Some(a) else Option.None }

    fun <A> flattenOption(fa: Kind1<F, Option<A>>): Kind1<F, A> = mapFilter(fa) { it }
}