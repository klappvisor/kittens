package kats

import kats.functor.Invariant
import kats.kinds.Kind1

interface Functor<F> : Invariant<F> {
    fun <A, B> map(fa: Kind1<F, A>, f: (A) -> B): Kind1<F, B>

    override fun <A, B> imap(fa: Kind1<F, A>, f: (A) -> B, fi: (B) -> A): Kind1<F, B> = map(fa, f)

    /**
     * Lift a function f to operate on Functors
     */
    fun <A, B> lift(f: (A) -> B): (Kind1<F, A>) -> Kind1<F, B> = { map(it, f) }

    /**
     * Empty the fa of the values, preserving the structure
     */
    fun <A> void(fa: Kind1<F, A>): Kind1<F, Unit> = map(fa) { Unit }

    /**
     * Tuple the values in fa with the result of applying a function
     * with the value
     */
    fun <A, B> fproduct(fa: Kind1<F, A>, f: (A) -> B): Kind1<F, Pair<A, B>> = map(fa) { Pair(it, f(it)) }

    /**
     * Replaces the `A` value in `F[A]` with the supplied value.
     */
    fun <A, B> replace(fa: Kind1<F, A>, b: B) = map(fa) { _ -> b }
}