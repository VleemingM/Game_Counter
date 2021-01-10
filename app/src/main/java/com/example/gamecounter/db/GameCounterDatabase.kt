package com.example.gamecounter.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.gamecounter.db.dao.GameDao
import com.example.gamecounter.db.dao.PlayerDao
import com.example.gamecounter.db.dao.PlayerRoundDao
import com.example.gamecounter.db.dao.RoundDao
import com.example.gamecounter.db.model.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(
    entities = arrayOf(
        Player::class,
        Game::class,
        PlayerRoundCrossRef::class,
        Round::class,
    ), version = 1, exportSchema = true
)
public abstract class GameCounterDatabase : RoomDatabase() {
    abstract fun playerDao(): PlayerDao
    abstract fun roundDao(): RoundDao
    abstract fun gameDao(): GameDao
    abstract fun playerRoundDao(): PlayerRoundDao


    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.


        @Volatile
        private var INSTANCE: GameCounterDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): GameCounterDatabase {
            //if the INSTANCE is not null, then return it,
            //if it is, then create the database

            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext, GameCounterDatabase::class.java,
                    "game_counter_database"
                )
                    .fallbackToDestructiveMigration()
                    .addCallback(GameCounterDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                //return instance
                instance
            }
        }
    }

    private class GameCounterDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { gameCounterDatabase ->
                scope.launch {
                    populateDatabase(gameCounterDatabase.playerDao(), gameCounterDatabase.gameDao())
                }
            }
        }

        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            INSTANCE?.let { gameCounterDatabase ->
                scope.launch {
                    populateDatabase(gameCounterDatabase.playerDao(), gameCounterDatabase.gameDao())
                }
            }
        }

        suspend fun populateDatabase(playerDao: PlayerDao, gameDao: GameDao) {
            // Add sample players
            var player = Player(name = "Michiel", age = 26)
            playerDao.insert(player)
            player = Player(name = "Mandy", age = 27)
            playerDao.insert(player)
            player = Player(name = "Noa", age = 2)
            playerDao.insert(player)
            player = Player(name = "Seb", age = 0)
            playerDao.insert(player)
            val game = Game(name = "Catan")
            gameDao.insert(game)

            val round = Round(gameId = game.gameId)

        }

    }
}