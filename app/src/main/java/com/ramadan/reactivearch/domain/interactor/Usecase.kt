package com.ramadan.reactivearch.domain.interactor

interface Usecase<T,R> {
    fun execute(param : T) : R
}