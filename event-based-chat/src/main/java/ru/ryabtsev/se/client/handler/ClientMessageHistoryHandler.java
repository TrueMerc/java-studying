package ru.ryabtsev.se.client.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import ru.ryabtsev.se.User;
import ru.ryabtsev.se.client.Client;
import ru.ryabtsev.se.client.event.ClientMessageHistoryEvent;
import ru.ryabtsev.se.client.event.ClientMessageInputEvent;
import ru.ryabtsev.se.packets.login.PacketLoginRequest;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.ObservesAsync;
import javax.inject.Inject;
import java.util.List;
import java.util.Scanner;


@ApplicationScoped
public class ClientMessageHistoryHandler {

    @Inject
    private Client client;

    @SneakyThrows
    public void handler(@ObservesAsync final ClientMessageHistoryEvent event) {
        List<String> messages = client.readAll();

        for( String message: messages ) {
            System.out.println( message );
        }
    }
}