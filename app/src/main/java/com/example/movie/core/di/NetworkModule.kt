package com.example.movie.core.di

import com.example.movie.BuildConfig
import com.example.movie.core.data.remote.MovieService
import com.example.movie.core.data.remote.ParamsInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit

//A notação @Module indica que essa classe é um modulo, vai fornecer os objetos para serem injetados em outras partes do App

//A notação @InstallIn indica em qual componente do hilt o modulo será instalado, neste caso ele será instalado no SingletonComponent
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    //A notação @Provides indica para o hilt quais instancias ele terá que prover
    private const val TIMEOUT_SECONDS = 15L

    //provideParamsInterceotor serve para prover a instancia do ParamsInterceptor
    @Provides
    fun provideParamsInterceotor(): ParamsInterceptor{
        return ParamsInterceptor()
    }

    //Configura os loggins(console.log) das requisições Http, se estiver em modo Debug exibe caso contrário não
    @Provides
    fun provideLogginInterceptor(): HttpLoggingInterceptor{
        return HttpLoggingInterceptor().apply {
            setLevel(
                if (BuildConfig.DEBUG){
                    HttpLoggingInterceptor.Level.BODY
                }
                else{
                    HttpLoggingInterceptor.Level.NONE
                }
            )
        }
    }

    //Configura o cliente Http para realizar conexões
    @Provides
    fun provideOkHttpClient(
        paramsInterceptor: ParamsInterceptor,
        loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient{

        return OkHttpClient.Builder()
            .addInterceptor(paramsInterceptor)// Adiciona as configurações desenvolvidas no ParamsInterceptor na Header da requisição
            .addInterceptor(loggingInterceptor)//
            .readTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)// Define um timeout de leitura
            .connectTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)// Define um timeout de conexão
            .build()

    }

    //Fornece uma instancia do GsonConverterFactory responsável por converter os Jsons em objetos Java e vice-versa
    @Provides
    fun provideGsonConverterFactory(): GsonConverterFactory{
        return GsonConverterFactory.create()
    }

    // Fornece uma instancia de MovieService, que é uma interface Retrofit para fazer as chamadas da nossa API
    @Provides
    fun provideMovieService(
        client: OkHttpClient,
        converterFactory: GsonConverterFactory
    ): MovieService{
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(client)
            .addConverterFactory(converterFactory)
            .build()
            .create(MovieService::class.java)
    }
}