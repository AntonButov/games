package pro.butovanton.games.repository

import pro.butovanton.games.db.Data
import pro.butovanton.games.net.GameEntity

class Mapper {
    fun mapGameEntityToData(gameEntitys: List<GameEntity>) : List<Data> {
        val result = mutableListOf<Data>()
        gameEntitys.forEach { gameEntity ->
            result.add(
                Data(
                    gameEntity.game.name,
                    gameEntity.game.box.small,
                    gameEntity.channels,
                    gameEntity.viewers)
            )
        }
        return result
    }
}