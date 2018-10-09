package ru.ryabtsev.se.client.event;

import org.jetbrains.annotations.NotNull;
import ru.ryabtsev.se.client.ClientBean;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import java.util.Scanner;

@ApplicationScoped
public class ClientMessageInputEvent {


    private static final String CMD_EXIT = "exit";

    private static final String CMD_LOGIN = "login";

    private static final String CMD_PING = "ping";

    private static final String CMD_BROADCAST = "broadcast";

    private static final String CMD_REGISTRY = "registry";

    @Inject
    private ClientBean client;

    @Inject
    private Event<ClientMessageInputEvent> clientMessageInputEvent;

    @Inject
    private Event<ClientMessageBroadcastEvent> clientMessageBroadcastEvent;

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

        if (CMD_PING.equals(message)) {
            clientMessagePingEvent.fireAsync(new ClientMessagePingEvent());
            clientMessageInputEvent.fire(new ClientMessageInputEvent());
            return;
        }

        if (CMD_LOGIN.equals(message)) {
            clientMessageLoginEvent.fireAsync(new ClientMessageLoginEvent() );
            clientMessageInputEvent.fire(new ClientMessageInputEvent());
            return;
        }

        if (CMD_BROADCAST.equals(message)) {
            clientMessageBroadcastEvent.fireAsync(new ClientMessageBroadcastEvent() );
            clientMessageInputEvent.fire(new ClientMessageInputEvent());
            return;
        }

        if (CMD_REGISTRY.equals(message)) {
            clientMessageRegistryEvent.fireAsync(new ClientMessageRegistryEvent() );
            clientMessageInputEvent.fire(new ClientMessageInputEvent());
            return;
        }

        if (CMD_EXIT.equals(message)) {
            client.exit();
            return;
        }

        clientMessageInputEvent.fire( new ClientMessageInputEvent() );
    }
}
