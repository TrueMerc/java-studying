package ru.ryabtsev.se.client.handler;

import org.jetbrains.annotations.NotNull;
import ru.ryabtsev.se.client.ClientBean;
import ru.ryabtsev.se.client.event.*;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import java.util.Scanner;

import static ru.ryabtsev.se.client.ChatCommands.*;

@ApplicationScoped
public class ClientMessageInputHandler {


    @Inject
    private ClientBean client;

    @Inject
    private Event<ClientMessageInputEvent> clientMessageReadEvent;

    @Inject
    private Event<ClientMessageInputEvent> clientMessageInputEvent;

    @Inject
    private Event<ClientMessageBroadcastEvent> clientMessageBroadcastEvent;

    @Inject
    private Event<ClientMessageUnicastEvent> clientMessageUnicastEvent;

    @Inject
    private Event<ClientMessageLoginEvent> clientMessageLoginEvent;

    @Inject
    private Event<ClientMessagePingEvent> clientMessagePingEvent;

    @Inject
    private Event<ClientMessageRegistryEvent> clientMessageRegistryEvent;


    public void handle(@Observes final ClientMessageInputEvent event) {
        System.out.println("Enter cmd (message or exit)");
        @NotNull final Scanner in = new Scanner(System.in);
        @NotNull final String message = in.nextLine();

        switch( message ) {
            case CMD_PING:
                clientMessagePingEvent.fireAsync(new ClientMessagePingEvent());
                clientMessageInputEvent.fire( new ClientMessageInputEvent() );
                return;
            case CMD_REGISTRY:
                clientMessageRegistryEvent.fire(new ClientMessageRegistryEvent());
                return;
            case CMD_LOGIN:
                clientMessageLoginEvent.fire(new ClientMessageLoginEvent() );
                return;
            case CMD_BROADCAST:
                clientMessageBroadcastEvent.fire(new ClientMessageBroadcastEvent() );
                return;
            case CMD_UNICAST:
                clientMessageUnicastEvent.fire(new ClientMessageUnicastEvent() );
                return;
            case CMD_EXIT:
                client.exit();
                return;
        }

        clientMessageInputEvent.fire( new ClientMessageInputEvent() );
    }
}
