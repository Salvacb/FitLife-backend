package com.example.fitlifeapp;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import android.content.Context;

@Database(entities = {RutinaEntity.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase INSTANCE;

    public abstract RutinaDao rutinaDao();

    public static AppDatabase getDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "fitlife_db")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries() // Solo para pruebas. Se recomienda usar threads.
                    .build();
        }
        return INSTANCE;
    }
}
