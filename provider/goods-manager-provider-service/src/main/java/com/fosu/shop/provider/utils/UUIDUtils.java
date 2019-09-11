package com.fosu.shop.provider.utils;

import java.util.UUID;
public class UUIDUtils {

    public static String getUUID(){
        return UUID.randomUUID().toString().replace("-", "");
    }
}
