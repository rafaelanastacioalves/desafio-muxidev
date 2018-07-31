package com.example.rafaelanastacioalves.moby.repository;

import com.example.rafaelanastacioalves.moby.vo.Fruits;

import io.reactivex.Observable;
import retrofit2.http.POST;

public interface APIClient {

    @POST("/muxidev/desafio-android/master/fruits.json")
    Observable<Fruits> getFruitList();

}
