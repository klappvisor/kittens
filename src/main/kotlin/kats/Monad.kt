package kats

import kats.kinds.Kind1

interface Monad<F> : FlatMap<F>, Applicative<F> {
    override fun <A, B> map(fa: Kind1<F, A>, f: (A) -> B): Kind1<F, B> =
            flatMap(fa) { a -> pure(f(a)) }
}