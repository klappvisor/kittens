package kats.functor

import kats.kinds.Kind1

interface Invariant<F> {
    fun <A, B> imap(fa: Kind1<F, A>, f: (A) -> B, fi: (B) -> A): Kind1<F, B>
}