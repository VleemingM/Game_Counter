package com.example.gamecounter.room.migrations

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import androidx.room.testing.MigrationTestHelper
import androidx.sqlite.db.framework.FrameworkSQLiteOpenHelperFactory
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.gamecounter.db.GameCounterDatabase
import com.example.gamecounter.db.GameCounterDatabase.Companion.MIGRATION_1_2
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MigrationTest {

    companion object {
        private const val TEST_DB = "migration-test"
    }

    @get:Rule
    val helper: MigrationTestHelper = MigrationTestHelper(
        InstrumentationRegistry.getInstrumentation(),
        GameCounterDatabase::class.java.canonicalName,
        FrameworkSQLiteOpenHelperFactory()
    )

    @Test
    fun migrationFrom1To2(){

        // EXAMPLE
        //Create DB with version 0
        val db = helper.createDatabase(TEST_DB,0)

//        db.insert("Player",SQLiteDatabase.CONFLICT_REPLACE,values)
        helper.runMigrationsAndValidate(TEST_DB,1,true,MIGRATION_1_2)

    }
}