package com.example.convidados.repository

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.convidados.model.GuestModel

//class GuestDataBase(context: Context?) : SQLiteOpenHelper(context, NAME, null, VERSION) {
@Database(entities = [GuestModel::class], version = 1)
abstract class GuestDataBase : RoomDatabase() {

    abstract fun guestDao(): GuestDAO

    companion object {
        private lateinit var INSTANCE : GuestDataBase

        fun getDataBase(context: Context) : GuestDataBase {
            if(!::INSTANCE.isInitialized) {
                synchronized(GuestDataBase::class) {
                    INSTANCE = Room.databaseBuilder(context, GuestDataBase::class.java, "guestbb")
                        .addMigrations(MIGRATION)
                        .allowMainThreadQueries()
                        .build()
                }
            }
            return INSTANCE
        }

        private val MIGRATION : Migration = object : Migration(1 , 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("DELETE FROM Guest")
            }

        }
    }

//    companion object{
//        private const val NAME = "guestdb"
//        private const val VERSION = 1
//    }
//
//    override fun onCreate(db: SQLiteDatabase?) {
//        db?.execSQL("create table " + DataBaseConstants.GUEST.TABLE_NAME + " (" +
//                DataBaseConstants.GUEST.COLUMNS.ID + " integer primary key autoincrement, " +
//                DataBaseConstants.GUEST.COLUMNS.NAME + " text, " +
//                DataBaseConstants.GUEST.COLUMNS.PRESENCE + " integer);")
//    }
//
//    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
//        TODO("Not yet implemented")
//    }
}