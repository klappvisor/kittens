package kats

import kats.kinds.Kind1

interface MonadWriter<F, W> : Monad<F> {
    /** Lift a writer action into the effect */
    fun <A> writer(aw: Pair<W, A>): Kind1<F, A>

    /** Run the effect and pair the accumulator with the result */
    fun <A> listen(fa: Kind1<F, A>): Kind1<F, Pair<W, A>>

    /** Apply the effectful function to the accumulator */
    fun <A> pass(fa: Kind1<F, (W) -> Pair<W, A>>): Kind1<F, A>

    /** Lift the log into the effect */
    fun tell(w: W): Kind1<F, Unit> = writer(Pair(w, Unit))

    /** Pair the value with an inspection of the accumulator */
    fun <A, B> listens(fa: Kind1<F, A>, f: (W) -> B): Kind1<F, Pair<B, A>> =
            map(listen(fa)) { (w, a) -> Pair(f(w), a) }

    /** Modify the accumulator */
    fun <A> censor(fa: Kind1<F, A>, f: (W) -> W) =
            flatMap(listen(fa)) { (w, a) -> writer(Pair(f(w), a)) }
}