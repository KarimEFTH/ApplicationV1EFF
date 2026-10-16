package com.example.applicationv1eff.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [ProduitEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun produitDao(): ProduitDao

     companion object {
         @Volatile
         private var INSTANCE: AppDatabase? = null

         fun getDatabase(context: Context): AppDatabase {
             return INSTANCE ?: synchronized(this) {
                 val instance = Room.databaseBuilder(
                     context.applicationContext,
                     AppDatabase::class.java,
                     "produit_database" // Your database name
                 ).build()
                 INSTANCE = instance
                 instance
             }
         }
     }
}
