package com.rdstudio.kantinpos.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.rdstudio.kantinpos.dataroom.Laporan;
import com.rdstudio.kantinpos.interroom.LaporanDao;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Laporan.class},version = 1,exportSchema = false)
public abstract class LaporanDatabase extends RoomDatabase {
    public abstract LaporanDao laporanDao();

    private static volatile LaporanDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS=4;
    public static final ExecutorService EXECUTOR_SERVICE= Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static LaporanDatabase getDatabase(final Context context){
        if (INSTANCE==null){
            synchronized (SetoranDatabase.class){
                if (INSTANCE==null){
                    INSTANCE= Room.databaseBuilder(context.getApplicationContext(),LaporanDatabase.class,"laporan_database").build();
                }
            }
        }
        return INSTANCE;
    }
}
