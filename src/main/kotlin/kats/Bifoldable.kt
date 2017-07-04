package kats

import kats.kernel.Monoid
import kats.kinds.K2

interface Bifoldable<F> {
    fun <A, B, C> bifoldLeft(fab: K2<F, A, B>, c: C, f: (C, A) -> C, g: (C, B) -> C): C

    fun <A, B, C> bifoldRight(fab: K2<F, A, B>, c: Lazy<C>, f: (A, Lazy<C>) -> Lazy<C>, g: (B, Lazy<C>) -> Lazy<C>): Lazy<C>

    fun <A, B, C> bifoldMap(fab: K2<F, A, B>, m: Monoid<C>, f: (A) -> C, g: (B) -> C): C =
            bifoldLeft(fab, m.empty, { c, a -> m.combine(c, f(a)) }, { c, b -> m.combine(c, g(b)) })
}