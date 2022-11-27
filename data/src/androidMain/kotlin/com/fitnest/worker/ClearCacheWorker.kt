package com.fitnest.worker

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkerParameters
import com.fitnest.domain.usecase.private_area.ClearCacheUseCase
import io.github.aakira.napier.Napier
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import java.util.Calendar
import java.util.concurrent.TimeUnit

class ClearCacheWorker(
    applicationContext: Context,
    workerParams: WorkerParameters,
    private val clearCacheUseCase: ClearCacheUseCase,
    private val ioDispatcher: CoroutineDispatcher
) : CoroutineWorker(applicationContext, workerParams) {

    override suspend fun doWork(): Result {
        return withContext(ioDispatcher) {
            try {
                clearCacheUseCase().getOrThrow()

                enqueueNextRequest()

                Result.success()
            } catch (e: Exception) {
                Napier.e("WorkManager error", e)
                Result.failure()
            }
        }
    }

    private fun enqueueNextRequest() {
        WorkManager.getInstance(applicationContext).enqueue(startUpClearCacheWork())
    }

    companion object {
        private const val TAG_OUTPUT = "TAG_OUTPUT"

        fun startUpClearCacheWork() = OneTimeWorkRequestBuilder<ClearCacheWorker>()
            .setInitialDelay(calculateDelay(), TimeUnit.MILLISECONDS)
            .addTag(TAG_OUTPUT)
            .build()

        private fun calculateDelay() = run {
            val currentDate = Calendar.getInstance()
            val dueDate = Calendar.getInstance()
            dueDate.set(Calendar.HOUR_OF_DAY, 0)
            dueDate.set(Calendar.MINUTE, 0)
            dueDate.set(Calendar.SECOND, 0)

            if (dueDate.before(currentDate)) {
                dueDate.add(Calendar.HOUR_OF_DAY, 24)
            }

            dueDate.timeInMillis - currentDate.timeInMillis
        }
    }
}