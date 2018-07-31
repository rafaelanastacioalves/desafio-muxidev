package com.example.rafaelanastacioalves.moby.repository;

import com.example.rafaelanastacioalves.moby.vo.Fruits;

import io.reactivex.Observable;
import io.reactivex.Single;

public class AppRepository {
    static {
        System.loadLibrary("hello-jni");
    }
    public static Observable<Fruits> getFruits(){
        APIClient APIClient = ServiceGenerator.createService(APIClient.class);
        Observable<Fruits> finalResult = APIClient.getFruitList();

    return finalResult;
    }

    public static Single<Float> getConvertedPrice(float originalPRice) {
        return Single.create(emitter -> {
                float newValue = convertValue(originalPRice);
                emitter.onSuccess(new Float(newValue));
        });
    }


    public native static float convertValue(float originalValue);

}
