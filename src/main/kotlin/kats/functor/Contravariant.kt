package kats.functor

import kats.kinds.Kind1

/**
 * Must obey the laws defined in cats.laws.ContravariantLaws.
 */
interface Contravariant<F> : Invariant<F> {
    fun <A, B> contramap(fa: Kind1<F, A>, f: (B) -> A): Kind1<F, B>

    override fun <A, B> imap(fa: Kind1<F, A>, f: (A) -> B, fi: (B) -> A): Kind1<F, B> =
            contramap(fa, fi)
}