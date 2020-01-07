package com.rdstudio.kantinpos.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.rdstudio.kantinpos.database.SetoranDatabase;
import com.rdstudio.kantinpos.dataroom.Setoran;
import com.rdstudio.kantinpos.interroom.SetoranDao;

import java.util.List;

public class SetoranRepository {

    private SetoranDao setoranDao;
    private LiveData<List<Setoran>> mAllSetoran;

    public SetoranRepository(Application application) {
        SetoranDatabase db = SetoranDatabase.getDatabase(application);
        setoranDao = db.setoranDao();
        mAllSetoran = setoranDao.getsetoran();
    }

    public void insert(final Setoran setoran) {
        SetoranDatabase.EXECUTOR_SERVICE.execute(() -> setoranDao.insert(setoran));
    }

    public void update(String nama,String b1, int hb1, int hj1,String b2, int hb2, int hj2,String b3, int hb3, int hj3) {
        SetoranDatabase.EXECUTOR_SERVICE.execute(() -> setoranDao.update(nama,b1, hb1, hj1,b2, hb2, hj2,b3, hb3, hj3));
    }
    public void setor(String nama,int js1,int js2,int js3) {
        SetoranDatabase.EXECUTOR_SERVICE.execute(() -> setoranDao.setor(nama,js1,js2,js3));
    }

    public LiveData<List<Setoran>> getmAllSetoran() {
        return mAllSetoran;
    }
    public LiveData<List<Setoran>> getmSetoran() {
        return setoranDao.getanggota();
    }

    public LiveData<List<Setoran>> getmAllSetoran(String s) {
        return setoranDao.getsetoran(s);
    }
}
