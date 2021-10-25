package ibm2187.dev.pixabay.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ibm2187.dev.pixabay.BuildConfig
import ibm2187.dev.pixabay.model.network.api.PixabayApi
import okhttp3.ConnectionPool
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Singleton
    @Provides
    fun provideConnectionPool(): ConnectionPool {
        return ConnectionPool(8, 30, TimeUnit.SECONDS)
    }

    @Singleton
    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE)
    }

    @Singleton
    @Provides
    fun retrofitService(client: OkHttpClient, gson: Gson): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(client)
            .build()
    }


    @Singleton
    @Provides
    fun provideOkhttpService(
        pool: ConnectionPool,
        loggingInterceptor: HttpLoggingInterceptor,
        mainInterceptor: Interceptor
    ): OkHttpClient {
        val okHttpClient = OkHttpClient.Builder()

        okHttpClient.apply {
            addInterceptor(loggingInterceptor)
            addInterceptor(mainInterceptor)
            connectTimeout(30, TimeUnit.SECONDS)
            writeTimeout(30, TimeUnit.SECONDS)
            readTimeout(30, TimeUnit.SECONDS)
            connectionPool(pool)
        }

        return okHttpClient.build()
    }

    @Singleton
    @Provides
    fun provideMainInterceptor(): Interceptor {
        return Interceptor {
            val request = it.request()
            val requestURL = request.url

            val requestQueryUrl = requestURL.newBuilder()
                .addQueryParameter("key", "22987170-a645446e885845ec526c80906")
                .build()

            val requestBuilder = request.newBuilder()
                .url(requestQueryUrl)

            return@Interceptor it.proceed(requestBuilder.build())
        }
    }

    @Singleton
    @Provides
    fun gsonProvider(): Gson = GsonBuilder()
        .setLenient()
        .create()

    @Singleton
    @Provides
    fun getPixaBayApi(retrofit: Retrofit): PixabayApi {
        return retrofit.create(PixabayApi::class.java)
    }
}