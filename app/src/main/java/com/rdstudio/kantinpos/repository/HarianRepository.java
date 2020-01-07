package com.rdstudio.kantinpos.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.rdstudio.kantinpos.database.HarianDatabase;
import com.rdstudio.kantinpos.dataroom.Harian;
import com.rdstudio.kantinpos.interroom.HarianDao;

import java.util.List;

public class HarianRepository {

    private HarianDao harianDao;
    private LiveData<List<Harian>> mAllHarian;

    public HarianRepository(Application application){
        HarianDatabase db=HarianDatabase.getDatabase(application);
        harianDao=db.harianDao();
        mAllHarian=harianDao.getHarian();
    }

    public void insert(final Harian harian){
        HarianDatabase.EXECUTOR_SERVICE.execute(() -> harianDao.insert(harian));
    }

    public LiveData<List<Harian>> getmAllHarian(){return mAllHarian;}
}
