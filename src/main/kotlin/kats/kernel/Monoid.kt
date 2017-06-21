package kats.kernel

interface Monoid<A> : Semigroup<A> {
    val empty: A
}