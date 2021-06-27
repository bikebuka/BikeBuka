package com.bikebuka.bikebuka.utils

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters

class BookingWorker(appContext: Context, workerParams: WorkerParameters) :
    Worker(appContext, workerParams) {
    override fun doWork(): Result {
        val appContext = applicationContext
        return Result.success()
    }
}