package com.rdstudio.kantinpos.model;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.rdstudio.kantinpos.dataroom.Setoran;
import com.rdstudio.kantinpos.repository.SetoranRepository;

import java.util.List;

public class SetoranModel extends AndroidViewModel {
    private SetoranRepository repository;
    private LiveData<List<Setoran>> mAll;
    public SetoranModel(@NonNull Application application) {
        super(application);
        repository=new SetoranRepository(application);
        mAll=repository.getmAllSetoran();
    }

    public void insert(Setoran setoran){repository.insert(setoran);}
    public void update(String nama,String b1, int hb1, int hj1,String b2, int hb2, int hj2,String b3, int hb3, int hj3){repository.update(nama,b1, hb1, hj1,b2, hb2, hj2,b3, hb3, hj3);}
    public void setor(String nama,int js1,int js2,int js3){repository.setor(nama,js1,js2,js3);
        Log.e("setor: ",nama+js1+""+js2+""+js3 );}
    public LiveData<List<Setoran>> getmAll(){return mAll;}
    public LiveData<List<Setoran>> getsetoran(){return repository.getmSetoran();}
    public LiveData<List<Setoran>> getmAll(String s){return repository.getmAllSetoran(s);}

}
