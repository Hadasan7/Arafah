package com.rdstudio.kantinpos.model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.rdstudio.kantinpos.dataroom.Harian;
import com.rdstudio.kantinpos.repository.HarianRepository;

import java.util.List;

public class HarianModel extends AndroidViewModel {
    private HarianRepository repository;
    private LiveData<List<Harian>> mAllHarian;
    public HarianModel(@NonNull Application application) {
        super(application);
        repository=new HarianRepository(application);
        mAllHarian=repository.getmAllHarian();
    }

    public void insert(Harian harian){repository.insert(harian);}

    public LiveData<List<Harian>> getmAllHarian(){return mAllHarian;}
}
