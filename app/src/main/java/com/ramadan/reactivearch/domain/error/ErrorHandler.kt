package com.ramadan.reactivearch.domain.error

interface ErrorHandler {
    fun getError(throwable: Throwable) : Failure
}