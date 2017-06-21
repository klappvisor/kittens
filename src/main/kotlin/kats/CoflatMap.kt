package kats

import kats.kinds.Kind1

interface CoflatMap<F> : Functor<F> {
    /**
     * `coflatMap` is the dual of `flatMap` on `FlatMap`. It applies
     * a value in a context to a function that takes a value
     * in a context and returns a normal value.
     */
    fun <A, B> coflatMap(fa: Kind1<F, A>, f: (Kind1<F, A>) -> B): Kind1<F, B>

    /**
     * `coflatten` is the dual of `flatten` on `FlatMap`. Whereas flatten removes
     * a layer of `F`, coflatten adds a layer of `F`
     */
    fun <A> coflatten(fa: Kind1<F, A>): Kind1<F, Kind1<F, A>>
}