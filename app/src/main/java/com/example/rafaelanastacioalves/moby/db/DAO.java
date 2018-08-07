package com.example.rafaelanastacioalves.moby.db;

import com.example.rafaelanastacioalves.moby.vo.Fruits;
import com.orhanobut.hawk.Hawk;

public class DAO {

    private static String FRUITS = "fruits_persistance_key";

    public static Fruits getFruitsFromDB() {
        return Hawk.get(FRUITS);
    }

    public static void setFruitsIntoDB(Fruits fruits) {
        Hawk.put(FRUITS, fruits);
    }
}
