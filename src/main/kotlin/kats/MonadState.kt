package kats

import kats.kinds.Kind1

interface MonadState<F, S> : Monad<F> {
    fun get(): Kind1<F, S>

    fun set(s: S): Kind1<F, Unit>

    fun <A> state(f: (S) -> Pair<S, A>): Kind1<F, A> =
            flatMap(get()) { ss -> val (s, a) = f(ss); map(set(s)) { _ -> a } }

    fun modify(f: (S) -> S): Kind1<F, Unit> =
            flatMap(get()) { set(f(it)) }

    fun <A> inspect(f: (S) -> A): Kind1<F, A> =
            map(get(), f)
}