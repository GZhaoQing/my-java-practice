package com.ufo.time;

import java.time.Duration;
import java.time.LocalDateTime;

public class checkTimeDelay {

    public static void main(String[] args){

        LocalDateTime
                checkPoint=LocalDateTime.of(2021,1,21,0,0),
                tarPoint=LocalDateTime.now();
        Duration du = Duration.ofDays(270);
        System.out.println(checkToTargetInDu(checkPoint,tarPoint,du));
        System.out.println(Duration.between(checkPoint,tarPoint));
    }

    private static boolean checkToTargetInDu(LocalDateTime checkPoint,LocalDateTime tarPoint,Duration du){
        System.out.println("checkToTargetInDu:"+checkPoint+","+tarPoint+","+du);
        boolean inDu=false;
        if(Duration.between(checkPoint,tarPoint).compareTo(du)<1){
            inDu=true;
        }
        return inDu;
    }
}
