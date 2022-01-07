package com.fitnest.domain.entity.base

interface BaseRequest {
    fun toMap(): Map<String, Any?>
}