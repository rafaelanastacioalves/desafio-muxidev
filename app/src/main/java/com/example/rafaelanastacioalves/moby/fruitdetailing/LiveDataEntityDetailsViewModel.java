package com.example.rafaelanastacioalves.moby.fruitdetailing;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.example.rafaelanastacioalves.moby.repository.AppRepository;
import com.example.rafaelanastacioalves.moby.vo.Fruit;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import timber.log.Timber;

public class LiveDataEntityDetailsViewModel extends ViewModel {

    private MutableLiveData<Fruit> mFruit = new MutableLiveData<>();

    public MutableLiveData<Fruit> getFruit() {
        return mFruit;
    }

    public void loadData(Fruit fruit) {
        Timber.i("LiveDataEntityDetailsViewModel loadData");

        if (mFruit.getValue() != null) {
            return;
        }


        Single<Fruit> result = AppRepository.getConvertedFruitObject(fruit);
        result.observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> mFruit.setValue(response),
                        throwable -> throwable.printStackTrace());

    }
}

