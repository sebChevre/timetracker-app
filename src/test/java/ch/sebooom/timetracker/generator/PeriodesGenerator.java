package ch.sebooom.timetracker.generator;

import ch.sebooom.timetracker.domaine.periode.Periode;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Random;

public class PeriodesGenerator {

    private Periode generateFakePeriode() {

        Periode periode = Periode.ouvrirPeriode(generateFakeDate());
        return periode;

    }

    private ZonedDateTime generateFakeDate () {
        return ZonedDateTime.of(LocalDateTime.of(
                getRandomYear(),getRandomMonth(),getRandomDay(),
                getRandomHours(),getRandomMinutes()), ZoneId.systemDefault());
    }

    private int getRandomYear(){
        return new Random()
                .ints(2000,2020)
                .findFirst()
                .getAsInt();
    }

    private int getRandomMonth(){
        return new Random()
                .ints(1,12)
                .findFirst()
                .getAsInt();
    }

    private int getRandomDay(){
        return new Random()
                .ints(1,31)
                .findFirst()
                .getAsInt();
    }

    private int getRandomHours(){
        return new Random()
                .ints(0,23)
                .findFirst()
                .getAsInt();
    }

    private int getRandomMinutes(){
        return new Random()
                .ints(0,59)
                .findFirst()
                .getAsInt();
    }
}
