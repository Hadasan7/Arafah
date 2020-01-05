package com.rdstudio.kantinpos.dataroom;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Objects;

@Entity(tableName = "setoran")
public class Setoran implements Parcelable {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "nama")
    private String nama;
    @ColumnInfo(name = "barang1")
    private String barang1;
    @ColumnInfo(name = "harga_beli1")
    private int harga_beli1;
    @ColumnInfo(name = "harga_jual1")
    private int harga_jual1;
    @ColumnInfo(name = "jumlah1")
    private int jumlah1;
    @ColumnInfo(name = "sisa1")
    private int sisa1;
    @ColumnInfo(name = "total1")
    private int total1;
    @ColumnInfo(name = "barang2")
    private String barang2;
    @ColumnInfo(name = "harga_beli2")
    private int harga_beli2;
    @ColumnInfo(name = "harga_jual2")
    private int harga_jual2;
    @ColumnInfo(name = "jumlah2")
    private int jumlah2;
    @ColumnInfo(name = "sisa2")
    private int sisa2;
    @ColumnInfo(name = "total2")
    private int total2;
    @ColumnInfo(name = "barang3")
    private String barang3;
    @ColumnInfo(name = "harga_beli3")
    private int harga_beli3;
    @ColumnInfo(name = "harga_jual3")
    private int harga_jual3;
    @ColumnInfo(name = "jumlah3")
    private int jumlah3;
    @ColumnInfo(name = "sisa3")
    private int sisa3;
    @ColumnInfo(name = "total3")
    private int total3;

    public Setoran(){

    }

    public void setNama(@NonNull String nama) {
        this.nama = nama;
    }

    @NonNull
    public String getNama() {
        return nama;
    }

    public String getBarang1() {
        return barang1;
    }

    public void setBarang1(String barang1) {
        this.barang1 = barang1;
    }

    public int getHarga_beli1() {
        return harga_beli1;
    }

    public void setHarga_beli1(int harga_beli1) {
        this.harga_beli1 = harga_beli1;
    }

    public int getHarga_jual1() {
        return harga_jual1;
    }

    public void setHarga_jual1(int harga_jual1) {
        this.harga_jual1 = harga_jual1;
    }

    public int getJumlah1() {
        return jumlah1;
    }

    public void setJumlah1(int jumlah1) {
        this.jumlah1 = jumlah1;
    }

    public int getSisa1() {
        return sisa1;
    }

    public void setSisa1(int sisa1) {
        this.sisa1 = sisa1;
    }

    public int getTotal1() {
        return total1;
    }

    public void setTotal1(int total1) {
        this.total1 = total1;
    }

    public String getBarang2() {
        return barang2;
    }

    public void setBarang2(String barang2) {
        this.barang2 = barang2;
    }

    public int getHarga_beli2() {
        return harga_beli2;
    }

    public void setHarga_beli2(int harga_beli2) {
        this.harga_beli2 = harga_beli2;
    }

    public int getHarga_jual2() {
        return harga_jual2;
    }

    public void setHarga_jual2(int harga_jual2) {
        this.harga_jual2 = harga_jual2;
    }

    public int getJumlah2() {
        return jumlah2;
    }

    public void setJumlah2(int jumlah2) {
        this.jumlah2 = jumlah2;
    }

    public int getSisa2() {
        return sisa2;
    }

    public void setSisa2(int sisa2) {
        this.sisa2 = sisa2;
    }

    public int getTotal2() {
        return total2;
    }

    public void setTotal2(int total2) {
        this.total2 = total2;
    }

    public String getBarang3() {
        return barang3;
    }

    public void setBarang3(String barang3) {
        this.barang3 = barang3;
    }

    public int getHarga_beli3() {
        return harga_beli3;
    }

    public void setHarga_beli3(int harga_beli3) {
        this.harga_beli3 = harga_beli3;
    }

    public int getHarga_jual3() {
        return harga_jual3;
    }

    public void setHarga_jual3(int harga_jual3) {
        this.harga_jual3 = harga_jual3;
    }

    public int getJumlah3() {
        return jumlah3;
    }

    public void setJumlah3(int jumlah3) {
        this.jumlah3 = jumlah3;
    }

    public int getSisa3() {
        return sisa3;
    }

    public void setSisa3(int sisa3) {
        this.sisa3 = sisa3;
    }

    public int getTotal3() {
        return total3;
    }

    public void setTotal3(int total3) {
        this.total3 = total3;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.nama);
        dest.writeString(this.barang1);
        dest.writeInt(this.harga_beli1);
        dest.writeInt(this.harga_jual1);
        dest.writeInt(this.jumlah1);
        dest.writeInt(this.sisa1);
        dest.writeInt(this.total1);
        dest.writeString(this.barang2);
        dest.writeInt(this.harga_beli2);
        dest.writeInt(this.harga_jual2);
        dest.writeInt(this.jumlah2);
        dest.writeInt(this.sisa2);
        dest.writeInt(this.total2);
        dest.writeString(this.barang3);
        dest.writeInt(this.harga_beli3);
        dest.writeInt(this.harga_jual3);
        dest.writeInt(this.jumlah3);
        dest.writeInt(this.sisa3);
        dest.writeInt(this.total3);
    }

    protected Setoran(Parcel in) {
        this.nama = Objects.requireNonNull(in.readString());
        this.barang1 = in.readString();
        this.harga_beli1 = in.readInt();
        this.harga_jual1 = in.readInt();
        this.jumlah1 = in.readInt();
        this.sisa1 = in.readInt();
        this.total1 = in.readInt();
        this.barang2 = in.readString();
        this.harga_beli2 = in.readInt();
        this.harga_jual2 = in.readInt();
        this.jumlah2 = in.readInt();
        this.sisa2 = in.readInt();
        this.total2 = in.readInt();
        this.barang3 = in.readString();
        this.harga_beli3 = in.readInt();
        this.harga_jual3 = in.readInt();
        this.jumlah3 = in.readInt();
        this.sisa3 = in.readInt();
        this.total3 = in.readInt();
    }

    public static final Parcelable.Creator<Setoran> CREATOR = new Parcelable.Creator<Setoran>() {
        @Override
        public Setoran createFromParcel(Parcel source) {
            return new Setoran(source);
        }

        @Override
        public Setoran[] newArray(int size) {
            return new Setoran[size];
        }
    };
}
