package pro.butovanton.games.net

import pro.butovanton.games.net.ResponseFromServer

class ResponseNet (var response: Int) {
    companion object {
        const val Ok = 0
        const val Failure = 1
    }
    var body: ResponseFromServer? = null
}