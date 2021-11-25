package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        String name = "Filya";
        char yes = 'y';
        byte age = 9;
        double weight = 7.5;
        float dailyFeed = 0.350f;
        boolean isPrettyCat = true;
        int stepsPerDay = 12345;
        long stepsPerMonth = 370350L;
        LOG.debug("User info name : {}, age : {}", name, age);
        LOG.info("Weight is {}, scheduled meals for the day : {} kg, hungry now? - {}", weight, dailyFeed, yes);
        LOG.info("Is pretty cat : {}, steps per day : {}, steps per month: {}", isPrettyCat, stepsPerDay, stepsPerMonth);
    }
}
