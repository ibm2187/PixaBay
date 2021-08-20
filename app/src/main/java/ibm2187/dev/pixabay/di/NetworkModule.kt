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
object NetworkModule {

    private val pool = ConnectionPool(8, 30, TimeUnit.SECONDS)
    private val loggingInterceptor =
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    @Singleton
    @Provides
    fun retrofitService(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gsonProvider()))
            .client(provideOkhttpService(30))
            .build()
    }


    @Singleton
    @Provides
    fun provideOkhttpService(timeout: Long): OkHttpClient {
        val okHttpClient = OkHttpClient.Builder()
        if (BuildConfig.DEBUG) {
            okHttpClient.addInterceptor(loggingInterceptor)
        }
        okHttpClient.apply {
            addInterceptor(provideOkhttpLoggingInterceptor())
            connectTimeout(timeout, TimeUnit.SECONDS)
            writeTimeout(timeout, TimeUnit.SECONDS)
            readTimeout(timeout, TimeUnit.SECONDS)
            connectionPool(pool)
        }

        return okHttpClient.build()
    }

    @Singleton
    @Provides
    fun provideOkhttpLoggingInterceptor(): Interceptor {
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
    fun getPixaBayApi(): PixabayApi {
        return retrofitService().create(PixabayApi::class.java)
    }
}