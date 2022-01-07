package com.fitnest.mapper

interface ResponseMapper<I, O> {
    fun map(source: I?): O
}