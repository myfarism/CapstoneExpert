package com.example.capstoneexpert.core.di

import androidx.room.Room
import com.example.capstoneexpert.core.data.NewsRepository
import com.example.capstoneexpert.core.data.source.local.LocalDataSource
import com.example.capstoneexpert.core.data.source.local.room.NewsDatabase
import com.example.capstoneexpert.core.data.source.remote.RemoteDataSource
import com.example.capstoneexpert.core.data.source.remote.network.ApiService
import com.example.capstoneexpert.core.domain.repository.INewsRepository
import com.example.capstoneexpert.core.utils.AppExecutors
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.core.scope.get
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val databaseModule = module {
    factory { get<NewsDatabase>().newsDao() }
    single {
        val passphrase: ByteArray = SQLiteDatabase.getBytes("faris".toCharArray())
        val factory = SupportFactory(passphrase)
        Room.databaseBuilder(
            androidContext(),
            NewsDatabase::class.java, "News.db"
        ).fallbackToDestructiveMigration()
            .openHelperFactory(factory)
            .build()
    }
}

val networkModule = module {
    single {
        val hostname = "saurav.tech"
        val certificatePinner = CertificatePinner.Builder()
            .add(hostname, "sha256/T4OcKVSnUyUYA8NnPVrjF0Q/QPKRSvQV4dlj3atWuf4=")
            .build()

        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .certificatePinner(certificatePinner)
            .build()
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://saurav.tech/NewsAPI/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }
}

val repositoryModule = module {
    single { LocalDataSource(get()) }
    single { RemoteDataSource(get()) }
    factory { AppExecutors() }
    single<INewsRepository> {
        NewsRepository(
            get(),
            get()
        )
    }
}