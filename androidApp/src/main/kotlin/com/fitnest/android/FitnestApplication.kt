package com.fitnest.android

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.util.Log
import androidx.work.Configuration
import androidx.work.WorkManager
import com.fitnest.android.di.registrationModule
import com.fitnest.android.di.serviceModule
import com.fitnest.android.di.viewModelModule
import com.fitnest.android.di.workerModule
import com.fitnest.di.dataExceptionHandlerModule
import com.fitnest.di.databaseModule
import com.fitnest.di.repositoryModule
import com.fitnest.di.serializationModule
import com.fitnest.domain.di.mapperModule
import com.fitnest.worker.ClearCacheWorker
import com.fitnest.worker.ClearCacheWorkerFactory
import org.kodein.di.DI
import org.kodein.di.DIAware
import org.kodein.di.DIContext
import org.kodein.di.android.closestDI
import org.kodein.di.android.subDI
import org.kodein.di.bind
import org.kodein.di.diContext
import org.kodein.di.instance

class FitnestApplication : Application(), Configuration.Provider, DIAware {
    override fun getWorkManagerConfiguration(): Configuration {
        val di = subDI(closestDI()) { import(workerModule) }
        val workerFactory by di.instance<ClearCacheWorkerFactory>()
        return Configuration.Builder()
            .setMinimumLoggingLevel(Log.INFO)
            .setWorkerFactory(workerFactory)
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