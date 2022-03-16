package com.jp.blueddit.service

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitUtils {

    companion object {
        /** Return main instance
         * @param path Main path API
         */
        fun getRetrofitInstance(path : String) : Retrofit {
            val gson = GsonBuilder().setLenient().create()
            return Retrofit.Builder()
                .baseUrl(path)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }
}