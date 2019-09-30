package ru.ryabtsev.se;

/**
 * Messenger application main class.
 *
 */
public class MessengerApplication
{
    public static void main( String[] args )
    {
        final MessengerMainWindow mainWindow =  new MessengerMainWindow();
        mainWindow.setVisible( true );
    }
}
