package kittens

interface Alternative<F> : Applicative<F>, MonoidK<F>