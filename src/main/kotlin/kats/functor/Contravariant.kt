package kats.functor

import kats.kinds.K1

/**
 * Must obey the laws defined in cats.laws.ContravariantLaws.
 */
interface Contravariant<F> : Invariant<F> {
    fun <A, B> contramap(fa: K1<F, A>, f: (B) -> A): K1<F, B>

    override fun <A, B> imap(fa: K1<F, A>, f: (A) -> B, fi: (B) -> A): K1<F, B> =
            contramap(fa, fi)
}