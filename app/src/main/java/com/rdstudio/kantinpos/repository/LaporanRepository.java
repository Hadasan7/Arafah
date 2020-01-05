package com.rdstudio.kantinpos.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.rdstudio.kantinpos.database.LaporanDatabase;
import com.rdstudio.kantinpos.dataroom.Laporan;
import com.rdstudio.kantinpos.interroom.LaporanDao;

import java.util.List;

public class LaporanRepository {

    private LaporanDao laporanDao;
    private LiveData<List<Laporan>> mAllLaporan;

    public LaporanRepository(Application application){
        LaporanDatabase db=LaporanDatabase.getDatabase(application);
        laporanDao=db.laporanDao();
        mAllLaporan=laporanDao.getlaporan();
    }

    public void insert(final Laporan laporan){
        LaporanDatabase.EXECUTOR_SERVICE.execute(() -> {
            laporanDao.insert(laporan);
        });
    }

    public LiveData<List<Laporan>> getmAllLaporan(){return mAllLaporan;}
}
