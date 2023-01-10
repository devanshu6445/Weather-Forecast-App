package com.weatherforecast.repository

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

abstract class BaseRepository {
    protected val ioScope = CoroutineScope(Dispatchers.IO + Job())
    protected val apiHandler = ApiHandler()
}