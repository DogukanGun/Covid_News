package com.dag.covidnews.base

import android.annotation.TargetApi
import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.content.res.Configuration
import android.content.res.Resources
import android.os.Build
import androidx.preference.PreferenceManager
import com.dag.covidnews.base.AppConstant.SELECTED_LANGUAGE
import dagger.hilt.android.HiltAndroidApp
import java.util.*


@HiltAndroidApp
abstract class BaseApplication:Application() {

}