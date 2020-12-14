package pro.butovanton.games.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import pro.butovanton.games.App
import pro.butovanton.games.db.DaoGame
import pro.butovanton.games.db.Data
import pro.butovanton.games.net.ResponseFromServer
import pro.butovanton.games.net.ResponseNet
import pro.butovanton.games.net.Server

class Repo(val server: Server = (App).appcomponent.getServer(), val dao: DaoGame = (App).appcomponent.getDao()) {

    private val STEP_PAGE = 10
    private var offset = 0

    init {
        serverGetAndSave(0)
    }

    fun getGames(): LiveData<List<Data>> {
        return dao.getAll()
    }

    fun getMoreGames() {
        offset += STEP_PAGE
        serverGetAndSave(offset)
    }

    private fun serverGetAndSave(offset: Int) {
        server.get(offset).observeForever { responseNet ->
            if (responseNet != null && responseNet!!.response == ResponseNet.Ok)
                saveToBD(responseNet.body)
        }
    }

    private fun saveToBD(responseFromServer: ResponseFromServer?) {
        GlobalScope.launch {
        dao.insert(Mapper().mapGameEntityToData(responseFromServer!!.top))
        }
    }
}