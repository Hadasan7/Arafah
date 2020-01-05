package com.rdstudio.kantinpos.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.rdstudio.kantinpos.dataroom.Setoran;
import com.rdstudio.kantinpos.interroom.SetoranDao;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Setoran.class},version = 1,exportSchema = false)
public abstract class SetoranDatabase extends RoomDatabase {
    public abstract SetoranDao setoranDao();

    private static volatile SetoranDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS=4;
    public static final ExecutorService EXECUTOR_SERVICE= Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static SetoranDatabase getDatabase(final Context context){
        if (INSTANCE==null){
            synchronized (SetoranDatabase.class){
                if (INSTANCE==null){
                    INSTANCE= Room.databaseBuilder(context.getApplicationContext(),SetoranDatabase.class,"setoran_database").build();
                }
            }
        }
        return INSTANCE;
    }
}
