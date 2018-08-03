package com.example.rafaelanastacioalves.moby.repository;

import android.telecom.Call;

import com.example.rafaelanastacioalves.moby.db.DAO;
import com.example.rafaelanastacioalves.moby.vo.Fruit;
import com.example.rafaelanastacioalves.moby.vo.Fruits;


import java.util.List;

import io.reactivex.Single;
import retrofit2.Callback;
import retrofit2.Response;

public class AppRepository {
    static {
        System.loadLibrary("hello-jni");
    }

    public static Single<Fruits> getFruits() {
        return Single.create(emitter -> {
            Fruits fruitList = DAO.getFruitsFromDB();

            if (fruitList != null && !fruitList.getFruits().isEmpty()) {
                emitter.onSuccess(fruitList);
                return;
            }

            APIClient APIClient = ServiceGenerator.createService(APIClient.class);
            retrofit2.Call<Fruits> finalResult = APIClient.getFruitList();

            finalResult.enqueue(new Callback<Fruits>() {
                @Override
                public void onResponse(retrofit2.Call<Fruits> call, Response<Fruits> response) {
                    if (response.isSuccessful()) {
                        DAO.setFruitsIntoDB(response.body());
                        Fruits finalFruits = DAO.getFruitsFromDB();

                        if (finalFruits != null) {
                            emitter.onSuccess(finalFruits);
                            return;
                        } else {
                            emitter.onError(new Throwable("Database Persistance Error!"));
                        }
                    }
                }

                @Override
                public void onFailure(retrofit2.Call<Fruits> call, Throwable t) {
                    emitter.onError(t);
                }
            });

        });

    }

    public static Single<Fruit> getConvertedFruitObject(Fruit fruit) {
        return Single.create(emitter -> {
            float newValue = convertValue(fruit.getPrice());
            fruit.setConvertedPrice(newValue);
            emitter.onSuccess(fruit);
        });
    }


    public native static float convertValue(float originalValue);

}
