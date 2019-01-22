package ru.ryabtsev.algorithms;


/**
 * The eighth homework main application.
 */
public class MainApplication
{
    public static void main( String[] args )
    {

        HashMap<String, String> myMap = new HashMap<>();
        myMap.put("0", "1");
        myMap.put("2", "3");
        myMap.put("8", "16");

        if( !"1".equals(myMap.get("0")) || !"3".equals(myMap.get("2")) || !"16".equals(myMap.get("8")) ) {
            System.out.println("Getter error!");
        }

        System.out.println("Processing completed.");
    }
}
