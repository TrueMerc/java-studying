package ru.ryabtsev.se.client.event;

import org.jetbrains.annotations.NotNull;

/**
 * Login response event.
 */
public final class ClientMessageLoginResponseEvent extends ClientResponseEvent {
    public ClientMessageLoginResponseEvent(@NotNull String message) {
        super(message);
    }
}
