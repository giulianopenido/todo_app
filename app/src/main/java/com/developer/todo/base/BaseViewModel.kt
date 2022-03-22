package com.developer.todo.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.developer.todo.helper.SingleLiveEvent
import kotlinx.coroutines.launch

open class BaseViewModel : ViewModel() {

    val showLoading by lazy { SingleLiveEvent<Void>() }
    val hideLoading by lazy { SingleLiveEvent<Void>() }

    protected fun doAsyncWork(
        shouldShowLoading: Boolean = true,
        exceptionHandler: (Exception) -> Unit = {},
        work: suspend () -> Unit
    ) {
        viewModelScope.launch {
            doWork(shouldShowLoading, exceptionHandler, work)
        }
    }

    private suspend fun <T> doWork(
        shouldShowLoading: Boolean = true,
        exceptionHandler: (Exception) -> Unit,
        work: suspend () -> T
    ): T? {
        return try {
            if (shouldShowLoading) {
                showLoading.call()
            }
            work()
        } catch (e: Exception) {
            exceptionHandler(e)
            null
        } finally {
            if (shouldShowLoading) {
                hideLoading.call()
            }
        }
    }
}