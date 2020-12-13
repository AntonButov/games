package pro.butovanton.games.di

import dagger.Component
import pro.butovanton.games.db.DaoGame
import pro.butovanton.games.net.APIInterface
import pro.butovanton.games.net.Server
import pro.butovanton.games.repository.Repo

@Component(modules = arrayOf(AppModule::class))
interface AppComponent {

    fun getDao(): DaoGame
    fun getRepo(): Repo
    fun getServer(): Server
    val apiInterface: APIInterface?
}