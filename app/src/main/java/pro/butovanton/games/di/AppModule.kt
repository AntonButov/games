package pro.butovanton.games.di


import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import pro.butovanton.games.App
import pro.butovanton.games.db.AppDatabase
import pro.butovanton.games.db.DaoGame
import pro.butovanton.games.net.APIInterface
import pro.butovanton.games.net.Server
import pro.butovanton.games.repository.Repo
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class AppModule(val app: App) {

    @Provides
    fun getDao(db: AppDatabase): DaoGame {
        return db.getDao()
    }

    @Provides
    fun getDb(): AppDatabase {
        return AppDatabase.getDB(app)
    }

    @Provides
    fun getRepo(server: Server): Repo {
        return Repo(server)
    }

    @Provides
    fun getServer(api: APIInterface): Server {
        return Server(api)
    }

    @Provides
    fun getApiInterface(retroFit: Retrofit): APIInterface {
        return retroFit.create(APIInterface::class.java)
    }

    @Provides
    fun getRetrofit(): Retrofit {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder()
        return Retrofit.Builder()
            .baseUrl("https://api.twitch.tv/kraken/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client.build())
            .build()
    }
}