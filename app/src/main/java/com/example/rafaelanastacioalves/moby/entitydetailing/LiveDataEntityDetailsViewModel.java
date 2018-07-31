package com.example.rafaelanastacioalves.moby.entitydetailing;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.example.rafaelanastacioalves.moby.repository.AppRepository;
import com.example.rafaelanastacioalves.moby.vo.Fruit;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import timber.log.Timber;

public class LiveDataEntityDetailsViewModel extends ViewModel {

    private MutableLiveData<Float> mEntityDetails = new MutableLiveData<>();

    public MutableLiveData<Float> getEntityDetails() {
        return mEntityDetails;
    }

    public void loadData(float originalValue) {
        Timber.i("LiveDataEntityDetailsViewModel loadData");

        if(mEntityDetails.getValue() != null){
            return;
        }


//

        Single<Float> result = AppRepository.getConvertedPrice(1.5f);
        result.observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> mEntityDetails.setValue(response),
                        throwable -> throwable.printStackTrace());



    }
}

