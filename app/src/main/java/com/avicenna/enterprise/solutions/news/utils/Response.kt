package com.avicenna.enterprise.solutions.news.utils

sealed class Response<T>(val data: T? = null, val error: String? = null) {
    class Success<T>(data: T? = null) : Response<T>(data = data)
    class Error<T>(message: String) : Response<T>(error = message)
    class Loading<T>: Response<T>()
}