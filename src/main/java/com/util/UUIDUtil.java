package com.util;

import java.util.UUID;

public class UUIDUtil {
    public static String getUUID(String key) {
        UUID u = UUID.nameUUIDFromBytes((key + System.currentTimeMillis()).getBytes());
        return u.toString().replaceAll("-", "0");
    }

    public static String getRandomByUUID() {
        return UUID.randomUUID().toString();
    }

}
