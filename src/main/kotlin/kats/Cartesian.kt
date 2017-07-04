package kats

import kats.kinds.K1

interface Cartesian<F> {
    fun <A, B> product(fa: K1<F, A>, fb: K1<F, B>): K1<F, Pair<A, B>>
}