package kittens.kernel

interface Monoid<A> : Semigroup<A> {
    val empty: A
}