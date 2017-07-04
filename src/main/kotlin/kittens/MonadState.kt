package kittens

import kittens.kinds.K1

interface MonadState<F, S> : Monad<F> {
    fun get(): K1<F, S>

    fun set(s: S): K1<F, Unit>

    fun <A> state(f: (S) -> Pair<S, A>): K1<F, A> =
            flatMap(get()) { ss -> val (s, a) = f(ss); map(set(s)) { _ -> a } }

    fun modify(f: (S) -> S): K1<F, Unit> =
            flatMap(get()) { set(f(it)) }

    fun <A> inspect(f: (S) -> A): K1<F, A> =
            map(get(), f)
}