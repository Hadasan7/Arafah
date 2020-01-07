package com.rdstudio.kantinpos.model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.rdstudio.kantinpos.dataroom.Laporan;
import com.rdstudio.kantinpos.repository.LaporanRepository;

import java.util.List;

public class LaporanModel extends AndroidViewModel {
    private LaporanRepository repository;
    private LiveData<List<Laporan>> mAllLaporan;
    public LaporanModel(@NonNull Application application) {
        super(application);
        repository=new LaporanRepository(application);
        mAllLaporan=repository.getmAllLaporan();
    }

    public void insert(Laporan laporan){repository.insert(laporan);}

    public LiveData<List<Laporan>> getmAllLaporan(){return mAllLaporan;}
    public void delete(){repository.delete();}
}
