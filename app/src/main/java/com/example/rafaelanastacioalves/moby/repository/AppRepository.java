package com.example.rafaelanastacioalves.moby.repository;

import com.example.rafaelanastacioalves.moby.vo.Fruits;

import io.reactivex.Observable;

public class AppRepository {

    public static Observable<Fruits> getFruits(){
        APIClient APIClient = ServiceGenerator.createService(APIClient.class);
        Observable<Fruits> finalResult = APIClient.getFruitList();

    return finalResult;
    }

}
