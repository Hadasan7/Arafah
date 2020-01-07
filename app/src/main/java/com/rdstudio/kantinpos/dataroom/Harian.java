package com.rdstudio.kantinpos.dataroom;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "harian")
public class Harian {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    private int id;
    @ColumnInfo(name = "tanggal")
    private String tanggal;
    @ColumnInfo(name = "laba")
    private String laba;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getLaba() {
        return laba;
    }

    public void setLaba(String laba) {
        this.laba = laba;
    }
}
