package com.rdstudio.kantinpos.interroom;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.rdstudio.kantinpos.dataroom.Harian;

import java.util.List;

@Dao
public interface HarianDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Harian harian);

    @Query("SELECT * from harian ORDER BY id")
    LiveData<List<Harian>> getHarian();
}
