package ru.ryabtsev;

/**
 * Demonstrates date/time class features.
 */
public class Application
{
    public static void main( String[] args )
    {
        DateTime now = DateTime.now();

        System.out.println("Date: " + now.getDate().toString());
        System.out.println("Time: " + now.getTime().toString());

        Time plusOneMinute = now.getTime().addSeconds(60);
        Time minusOneMinute = now.getTime().addSeconds(-60);
        Time plusOneHour = now.getTime().addMinutes(60);
        Time minusOneHour = now.getTime().addMinutes(-60);
        Time plusOneMinuteViaAddMinutes = now.getTime().addMinutes(1);

        System.out.println("Time (minus one minute): " + minusOneMinute.toString());
        System.out.println("Time (plus one minute): " + plusOneMinute.toString());
        System.out.println("Time (minus one hour): " + minusOneHour.toString());
        System.out.println("Time (plus one hour): " + plusOneHour.toString());
        System.out.println("Minute: " + plusOneMinuteViaAddMinutes);

        if(!(plusOneMinuteViaAddMinutes.equals(plusOneMinute))) {
            System.out.println("Something works wrong.");
        }
    }
}
