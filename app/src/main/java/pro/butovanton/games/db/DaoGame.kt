/*
 * Copyright 2018 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package pro.butovanton.games.db

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface DaoGame {

    @Query("SELECT * FROM DATA")
    fun getAll(): LiveData<List<Data>>

    @Query("SELECT * FROM DATA")
    suspend fun getLast(): Data


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(dataLast: Data) : Long

    @Query("DELETE FROM data")
    suspend fun delete()

}
