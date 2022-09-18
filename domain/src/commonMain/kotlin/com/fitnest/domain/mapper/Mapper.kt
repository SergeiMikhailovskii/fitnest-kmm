package com.fitnest.domain.mapper

interface Mapper<I, O> {
    fun map(source: I?): O
}
