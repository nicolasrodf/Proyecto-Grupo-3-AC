package com.app.data.datasource

interface LocationDataSource {
    suspend fun findLastRegion(): String?
}