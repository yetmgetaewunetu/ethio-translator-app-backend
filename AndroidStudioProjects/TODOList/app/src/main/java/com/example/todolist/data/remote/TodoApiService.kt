package com.example.todolist.data.remote

import com.example.todolist.data.model.Todo
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

// data/remote/TodoApiService.kt
interface TodoApiService {
    @GET("todos")
    suspend fun getTodos(): List<Todo>

    companion object {
        const val BASE_URL = "https://jsonplaceholder.typicode.com/"

        fun create(): TodoApiService {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(
                    OkHttpClient.Builder()
                        .addInterceptor(HttpLoggingInterceptor().apply {
                            level = HttpLoggingInterceptor.Level.BODY
                        })
                        .build()
                )
                .build()
            return retrofit.create(TodoApiService::class.java)
        }
    }
}