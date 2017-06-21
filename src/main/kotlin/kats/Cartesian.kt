package kats

import kats.kinds.Kind1

interface Cartesian<F> {
    fun <A, B> product(fa: Kind1<F, A>, fb: Kind1<F, B>): Kind1<F, Pair<A, B>>
}