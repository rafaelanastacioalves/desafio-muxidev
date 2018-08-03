package com.example.rafaelanastacioalves.moby.entitymainlisting;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.example.rafaelanastacioalves.moby.vo.Fruit;
import com.example.rafaelanastacioalves.moby.repository.AppRepository;
import com.example.rafaelanastacioalves.moby.vo.Fruits;

import java.util.List;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

public class LiveDataMainEntityListViewModel extends ViewModel {

    private MutableLiveData<List<Fruit>> mMainEntityList = new MutableLiveData<>();

    public MutableLiveData<List<Fruit>> getMainEntityList() {
        return mMainEntityList;
    }


    public void loadData() {
        Timber.i("LiveDataMainEntityListViewModel loadData");

        if (mMainEntityList.getValue() != null) {
            return;
        }


        Single<Fruits> finalResult = AppRepository.getFruits();

        finalResult.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(response -> mMainEntityList.setValue(response.getFruits()),
                        throwable -> throwable.printStackTrace());
    }

}
