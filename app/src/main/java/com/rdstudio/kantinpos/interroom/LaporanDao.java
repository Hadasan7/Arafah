package com.rdstudio.kantinpos.interroom;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.rdstudio.kantinpos.dataroom.Laporan;

import java.util.List;

@Dao
public interface LaporanDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Laporan laporan);

    @Query("SELECT * from laporan ORDER BY id")
    LiveData<List<Laporan>> getlaporan();
}
