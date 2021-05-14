package com.tommy.nl.tests;

import org.apache.commons.lang3.RandomStringUtils;

public class RandomGenerator {

    private static String randomAlphanumeric(Integer length){
        return RandomStringUtils.randomAlphanumeric(length);
    }

    public static  String  randomEmailAddress(Integer length){
        String email = randomAlphanumeric(length) + "@Demo.com";
        return  email.toLowerCase();
    }
}
