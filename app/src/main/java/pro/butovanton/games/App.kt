package pro.butovanton.games

import android.app.Application
import pro.butovanton.games.di.AppComponent
import pro.butovanton.games.di.AppModule
import pro.butovanton.games.di.DaggerAppComponent

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        appcomponent = DaggerAppComponent
            .builder()
            .appModule(AppModule(this))
            .build()
    }

    companion object {
        lateinit var appcomponent: AppComponent
        val context = this
    }
}