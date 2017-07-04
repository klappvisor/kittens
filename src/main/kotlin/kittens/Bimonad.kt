package kittens

interface Bimonad<F> : Monad<F>, Comonad<F>