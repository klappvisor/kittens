package kittens.kernel

interface Semigroup<A> {
    fun combine(a1: A, a2: A): A
}