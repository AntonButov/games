package pro.butovanton.games.net

import pro.butovanton.games.net.Game

data class GameEntity(val channels: Int,
                      val viewers: Int,
                      val game: Game
)
