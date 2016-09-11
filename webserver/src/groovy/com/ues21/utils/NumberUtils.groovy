package com.ues21.utils

import java.util.Random

class NumberUtils {

    private static Random RANDOM = new Random()
    
    public static int getRandomBetween(int base, int limit) {
        Integer result = null
        while(result == null || result < base || result > limit) {
            result = RANDOM.nextInt(limit + 1)
        }
        return result
    }
}
