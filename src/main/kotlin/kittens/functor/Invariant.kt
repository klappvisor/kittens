package kittens.functor

import kittens.kinds.K1

interface Invariant<F> {
    fun <A, B> imap(fa: K1<F, A>, f: (A) -> B, fi: (B) -> A): K1<F, B>
}