package pro.butovanton.games.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "data")
data class Data(
    @PrimaryKey
    @ColumnInfo(name = "date")
    val name: String,
    val logo: String,
    val chanels: Int,
    val viewers: Int)