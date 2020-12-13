package pro.butovanton.games.net

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface APIInterface {

    @GET("games/top/")
    @Headers(
        "Accept: application/vnd.twitchtv.v5+json",
        "Client-ID: sd4grh0omdj9a31exnpikhrmsu3v46")
    fun games(@Query("offset") offset  : Int ): Call<ResponseFromServer?>?
}