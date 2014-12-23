package com.beardroid.morningbuddy;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Calendar;

/**
 * Created by Max on 12/18/2014.
 */
public class Alarm implements Serializable {
    public enum Day{
        SUNDAY,
        MONDAY,
        TUESDAY,
        WEDNESDAY,
        THURSDAY,
        FRIDAY,
        SATURDAY;

        @Override
        public String toString() {
            switch(this.ordinal()){
                case 0:
                    return "Sunday";
                case 1:
                    return "Monday";
                case 2:
                    return "Tuesday";
                case 3:
                    return "Wednesday";
                case 4:
                    return "Thursday";
                case 5:
                    return "Friday";
                case 6:
                    return "Saturday";
            }
            return super.toString();
        }
    }
    private boolean alarmActive = true;
    private Calendar alarmTime = Calendar.getInstance();
    private Day[] days = {Day.SUNDAY, Day.MONDAY, Day.TUESDAY, Day.WEDNESDAY, Day.THURSDAY, Day.FRIDAY, Day.SATURDAY};
    private boolean vibrate = true;
    private String name = "Alarm";

    public Alarm(){

    }
    public boolean getAlarmActive() {
        return this.alarmActive;
    }
    public Calendar getAlarmTime(){
        if (alarmTime.before(Calendar.getInstance()))
            alarmTime.add(Calendar.DAY_OF_MONTH, 1);
        while(!Arrays.asList(getDays()).contains(Day.values()[alarmTime.get(Calendar.DAY_OF_WEEK)-1])){
            alarmTime.add(Calendar.DAY_OF_MONTH, 1);
        }
        return this.alarmTime;
    }
    public Day[] getDays(){
        return days;
    }
    public String getAlarmTimeString(){
        String time = "";
        if (alarmTime.get(Calendar.HOUR_OF_DAY) <= 9)
            time += "0";
        time += String.valueOf(alarmTime.get(Calendar.HOUR_OF_DAY));
        time += ":";
        if (alarmTime.get(Calendar.MINUTE) <= 9)
            time += "0";
        time += String.valueOf(alarmTime.get(Calendar.MINUTE));
        return time;
    }
    public void setAlarmTime(Calendar alarmTime){
        this.alarmTime = alarmTime;
    }
    public void setAlarmTime(String alarmTime){

    }
}
