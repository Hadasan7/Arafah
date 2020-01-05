package com.rdstudio.kantinpos.interroom;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.rdstudio.kantinpos.dataroom.Setoran;

import java.util.List;

@Dao
public interface SetoranDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Setoran setoran);

    @Query("SELECT * from setoran")
    LiveData<List<Setoran>> getsetoran();

    @Query("SELECT * from setoran WHERE nama=:s")
    LiveData<List<Setoran>> getsetoran(String s);

    @Query("SELECT * from setoran WHERE jumlah1!=0")
    LiveData<List<Setoran>> getanggota();

    @Query("UPDATE setoran SET barang1=:b1,harga_beli1=:hb1,harga_jual1=:hj1,barang2=:b2,harga_beli2=:hb2,harga_jual2=:hj2,barang3=:b3,harga_beli3=:hb3,harga_jual3=:hj3 WHERE nama=:nama")
    void update(String nama, String b1, int hb1, int hj1,String b2, int hb2, int hj2,String b3, int hb3, int hj3);

    @Query("UPDATE setoran SET jumlah1=:js1,jumlah2=:js2,jumlah3=:js3 WHERE nama=:nama")
    void setor(String nama,int js1,int js2,int js3);

}
