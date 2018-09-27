package ru.ryabtsev.se;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

/**
 * Messenger main window class.
 */

public class MessengerMainWindow extends JFrame {

    private final static String DEFAULT_NAME = "Simple messenger";
    private final static int DEFAULT_HORIZONTAL_SIZE = 640;
    private final static int DEFAULT_VERTICAL_SIZE = 480;

    private final JPanel mainPanel;
    private final JTextArea chatTextArea;
    private final JTextArea messageTextArea;
    private final JButton sendButton;

    public MessengerMainWindow() {
        setTitle( DEFAULT_NAME );
        setDefaultCloseOperation( WindowConstants.EXIT_ON_CLOSE );
        setBounds( 300, 300, DEFAULT_HORIZONTAL_SIZE, DEFAULT_VERTICAL_SIZE);
        setMaximumSize( new Dimension( DEFAULT_HORIZONTAL_SIZE, DEFAULT_VERTICAL_SIZE) );
        setMinimumSize( new Dimension( DEFAULT_HORIZONTAL_SIZE, DEFAULT_VERTICAL_SIZE) );
        setLayout( null );
        mainPanel = new JPanel();
        mainPanel.setLayout( null );

        chatTextArea = new JTextArea();
        JScrollPane chatScrollPane = new JScrollPane( chatTextArea );
        chatScrollPane.setBounds( 5, 5, 630, 355 );
        mainPanel.add( chatScrollPane );

        messageTextArea = new JTextArea();
        messageTextArea.setBounds( 5, 370, 630, 30);
        mainPanel.add( messageTextArea );

        sendButton = new JButton( "Send");
        sendButton.setToolTipText( "Send message (Enter)" );
        sendButton.setMnemonic( KeyEvent.VK_ENTER );
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                sendMessage();
            }
        });
        sendButton.setBounds( 550, 410, 80, 30 );
        mainPanel.add( sendButton );

        setContentPane( mainPanel );
    }

    private void sendMessage() {
        String messageText = messageTextArea.getText();
        chatTextArea.append( messageText + '\n' );
        messageTextArea.replaceRange("", 0, messageText.length() );
    }
}
