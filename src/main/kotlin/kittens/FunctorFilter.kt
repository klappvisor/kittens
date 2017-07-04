package kittens

import kittens.data.option.Option
import kittens.kinds.K1

interface FunctorFilter<F> : Functor<F> {
    fun <A, B> mapFilter(fa: K1<F, A>, f: (A) -> Option<B>): K1<F, B>

    fun <A, B> collect(fa: K1<F, A>, f: (A) -> Option<B>): K1<F, B> =
            mapFilter(fa, f)

    fun <A> filter(fa: K1<F, A>, f: (A) -> Boolean): K1<F, A> =
            mapFilter(fa) { a -> if(f(a)) Option.Some(a) else Option.None }

    fun <A> flattenOption(fa: K1<F, Option<A>>): K1<F, A> = mapFilter(fa) { it }
}