package com.rdstudio.kantinpos.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.rdstudio.kantinpos.dataroom.Harian;
import com.rdstudio.kantinpos.interroom.HarianDao;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Harian.class},version = 1,exportSchema = false)
public abstract class HarianDatabase extends RoomDatabase {
    public abstract HarianDao harianDao();

    private static volatile HarianDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS=4;
    public static final ExecutorService EXECUTOR_SERVICE= Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static HarianDatabase getDatabase(final Context context){
        if (INSTANCE==null){
            synchronized (HarianDatabase.class){
                if (INSTANCE==null){
                    INSTANCE= Room.databaseBuilder(context.getApplicationContext(), HarianDatabase.class,"harian_database").build();
                }
            }
        }
        return INSTANCE;
    }
}
