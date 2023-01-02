package com.fitnest.android

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.work.Configuration
import androidx.work.WorkManager
import com.fitnest.android.di.registrationModule
import com.fitnest.android.di.serviceModule
import com.fitnest.android.di.viewModelModule
import com.fitnest.di.dataExceptionHandlerModule
import com.fitnest.di.databaseModule
import com.fitnest.di.repositoryModule
import com.fitnest.di.serializationModule
import com.fitnest.domain.di.mapperModule
import com.fitnest.domain.di.useCaseModule
import com.fitnest.domain.usecase.private_area.ClearCacheUseCase
import com.fitnest.worker.ClearCacheWorker
import com.fitnest.worker.ClearCacheWorkerFactory
import org.kodein.di.DI
import org.kodein.di.DIAware
import org.kodein.di.DIContext
import org.kodein.di.bind
import org.kodein.di.diContext
import org.kodein.di.instance

class FitnestApplication : Application(), Configuration.Provider, DIAware {
    override fun getWorkManagerConfiguration(): Configuration {
        val clearCacheUseCase by instance<ClearCacheUseCase>()
        return Configuration.Builder()
            .setMinimumLoggingLevel(android.util.Log.INFO)
            .setWorkerFactory(ClearCacheWorkerFactory(clearCacheUseCase))
            .build()
    }

    override fun onCreate() {
        super.onCreate()
        createNotificationChannel()
        WorkManager.getInstance(applicationContext)
            .enqueue(ClearCacheWorker.startUpClearCacheWork())
    }

    override val diContext: DIContext<*> = diContext(this)

    override val di by DI.lazy {
        bind<Context>() with instance(this@FitnestApplication)
        import(dataExceptionHandlerModule)
        import(databaseModule)
        import(registrationModule)
        import(viewModelModule)
        import(repositoryModule)
        import(serviceModule)
        import(mapperModule)
        import(serializationModule)
        import(useCaseModule)
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = getString(R.string.default_channel_name)
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel("9999", name, importance)
            val notificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }
}