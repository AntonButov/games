package pro.butovanton.games

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import pro.butovanton.games.db.Data
import pro.butovanton.games.repository.Repo

class MainViewModel ( val repo: Repo = (App).appcomponent.getRepo()): ViewModel() {

    private var games: LiveData<List<Data>>? = null

    fun getGames(): LiveData<List<Data>> {
        if (games == null)
            games = repo.getGames()
    return games!!
    }

    fun getMoreGames() {
      repo.getMoreGames()
    }
}