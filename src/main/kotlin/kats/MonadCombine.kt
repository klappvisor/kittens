package kats

import kats.kinds.Kind1
import kats.kinds.Kind2

/**
 * The combination of a Monad with a MonoidK
 */
interface MonadCombine<F> : MonadFilter<F>, Alternative<F> {
    /**
     * Fold over the inner structure to combine all of the values with
     * our combine method inherited from MonoidK. The result is for us
     * to accumulate all of the "interesting" values of the inner G, so
     * if G is Option, we collect all the Some values, if G is Either,
     * we collect all the Right values, etc.
     */
    fun <G, A> unite(fga: Kind1<F, Kind1<G, A>>, g: Foldable<G>): Kind1<F, A> =
            flatMap(fga) {
                g.foldLeft(it, empty<A>()) { acc, a -> combineK(acc, pure(a)) }
            }

    fun <G, A, B> separate(fgab: Kind1<F, Kind2<G, A, B>>, g: Bifoldable<G>): Pair<Kind1<F, A>, Kind1<F, B>> {
        val xs = flatMap(fgab) { g.bifoldMap(it, algebra<A>(), { pure(it) }, { _ -> empty<A>() }) }
        val ys = flatMap(fgab) { g.bifoldMap(it, algebra<B>(), { _ -> empty<B>() }, { pure(it) }) }
        return Pair(xs, ys)
    }
}
