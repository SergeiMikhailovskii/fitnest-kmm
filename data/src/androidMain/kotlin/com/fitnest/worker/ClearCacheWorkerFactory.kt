package com.fitnest.worker

import android.content.Context
import androidx.work.ListenableWorker
import androidx.work.WorkerFactory
import androidx.work.WorkerParameters
import com.fitnest.domain.usecase.private_area.ClearCacheUseCase
import kotlinx.coroutines.Dispatchers

class ClearCacheWorkerFactory(
    private val clearCacheUseCase: ClearCacheUseCase
) : WorkerFactory() {
    override fun createWorker(
        appContext: Context,
        workerClassName: String,
        workerParameters: WorkerParameters
    ): ListenableWorker? = when (workerClassName) {
        ClearCacheWorker::class.qualifiedName -> ClearCacheWorker(
            appContext,
            workerParameters,
            clearCacheUseCase,
            Dispatchers.IO
        )
        else -> null
    }
}