package pro.butovanton.games.net

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import pro.butovanton.games.net.APIInterface
import pro.butovanton.games.net.ResponseFromServer
import pro.butovanton.games.net.ResponseNet
import retrofit2.Call


class Server(val api : APIInterface) {

    private val responseLD = MutableLiveData<ResponseNet>()

    fun get(offset: Int): LiveData<ResponseNet> {
        api.games(offset)?.enqueue(object: retrofit2.Callback<ResponseFromServer?> {
            override fun onResponse(call: Call<ResponseFromServer?>, response: retrofit2.Response<ResponseFromServer?>) {
                val responseNet = ResponseNet(ResponseNet.Ok)
                responseNet.body = response.body()
                responseLD.value = responseNet
            }

            override fun onFailure(call: Call<ResponseFromServer?>, t: Throwable) {
                responseLD.value = ResponseNet(ResponseNet.Failure)
            }
        })
        return responseLD
    }
}