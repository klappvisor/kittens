package kittens

import kittens.kinds.K1

interface CoflatMap<F> : Functor<F> {
    /**
     * `coflatMap` is the dual of `flatMap` on `FlatMap`. It applies
     * a value in a context to a function that takes a value
     * in a context and returns a normal value.
     */
    fun <A, B> coflatMap(fa: K1<F, A>, f: (K1<F, A>) -> B): K1<F, B>

    /**
     * `coflatten` is the dual of `flatten` on `FlatMap`. Whereas flatten removes
     * a layer of `F`, coflatten adds a layer of `F`
     */
    fun <A> coflatten(fa: K1<F, A>): K1<F, K1<F, A>>
}