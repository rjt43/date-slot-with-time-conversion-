package io.plugo.darth.box.controller;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Java82 {
    public static void main(String[] args) {
        
        String dateTimeString = "2023-06-29 01:56:43";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse(dateTimeString, formatter);

        ZoneId sourceZone = ZoneId.of("America/New_York");
        ZonedDateTime sourceDateTime = ZonedDateTime.of(dateTime, sourceZone);

        ZoneId targetZone = ZoneId.of("Asia/Kolkata");
        ZonedDateTime targetDateTime = sourceDateTime.withZoneSameInstant(targetZone).plusMinutes(60);
        //DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss z");

        ZonedDateTime stopperDate=targetDateTime.plusDays(1).withHour(0).withMinute(0).withSecond(0);

        int minute = targetDateTime.getMinute();
        if(minute<30){
            targetDateTime=  targetDateTime.withMinute(30);
        }else if(minute>=30){
            targetDateTime=   targetDateTime.plusMinutes(60-minute);
        }


        List<ZonedDateTime> gaps = new ArrayList<>();
        while(targetDateTime.compareTo(stopperDate)<0){
            gaps.add(targetDateTime.withZoneSameInstant(sourceZone));
            targetDateTime = targetDateTime.plusMinutes(30);
        }

        for (ZonedDateTime time : gaps) {
            System.out.println(time);
        }


     /*   String convertedDateTime2 = targetDateTime.format(outputFormatter);

        System.out.println("Converted date and time: " + convertedDateTime2);*/
    }
}
