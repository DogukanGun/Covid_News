package com.dag.covidnews.base

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
abstract class BaseApplication:Application() {
}