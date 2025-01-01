package com.example.githubtrending.di

import com.example.githubtrending.model.valueobject.HttpLocateType
import com.example.githubtrending.repository.GitHubRepository
import com.example.githubtrending.repository.GitHubRepositoryImpl
import com.example.githubtrending.repository.http.DoForceHttpClient
import com.example.githubtrending.repository.http.GitHubHttpClient
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import javax.inject.Qualifier

@InstallIn(SingletonComponent::class)
@Module
class InfraModule {

    @HttpLocate(HttpLocateType.GitHubApi)
    @Provides
    internal fun provideGitHubRetrofit(
        httpClient: OkHttpClient
    ): Retrofit {
        val contentType = "application/json".toMediaType()
        val json = Json { ignoreUnknownKeys = true }
        return Retrofit.Builder()
            .baseUrl("https://api.github.com")
            .addConverterFactory(json.asConverterFactory(contentType))
            .client(httpClient)
            .build()
    }

    @HttpLocate(HttpLocateType.DoForceApi)
    @Provides
    internal fun provideDoForceRetrofit(
        httpClient: OkHttpClient
    ): Retrofit {
        val contentType = "application/json".toMediaType()
        val json = Json { ignoreUnknownKeys = true }
        return Retrofit.Builder()
            .baseUrl("https://trend.doforce.us.kg")
            .addConverterFactory(json.asConverterFactory(contentType))
            .client(httpClient)
            .build()
    }

    @Provides
    internal fun provideOkHttp(
        logInterceptor: HttpLoggingInterceptor,
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(logInterceptor)
            .build()
    }

    @Provides
    internal fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    @Provides
    internal fun provideGitHubHttpClient(
        @HttpLocate(HttpLocateType.GitHubApi) retrofit: Retrofit
    ): GitHubHttpClient {
        return retrofit.create(GitHubHttpClient::class.java)
    }

    @Provides
    internal fun provideDoForceHttpClient(
        @HttpLocate(HttpLocateType.DoForceApi) retrofit: Retrofit
    ): DoForceHttpClient {
        return retrofit.create(DoForceHttpClient::class.java)
    }
}

@InstallIn(SingletonComponent::class)
@Module
abstract class RepositoryModule {

    @Binds
    abstract fun bindGithubRepository(impl: GitHubRepositoryImpl): GitHubRepository

}

@Qualifier
@MustBeDocumented
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FIELD, AnnotationTarget.FUNCTION, AnnotationTarget.VALUE_PARAMETER)
annotation class HttpLocate(val value: HttpLocateType = HttpLocateType.GitHubApi)