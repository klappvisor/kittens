package kats

import kats.kinds.K1

interface Monad<F> : FlatMap<F>, Applicative<F> {
    override fun <A, B> map(fa: K1<F, A>, f: (A) -> B): K1<F, B> =
            flatMap(fa) { a -> pure(f(a)) }
}