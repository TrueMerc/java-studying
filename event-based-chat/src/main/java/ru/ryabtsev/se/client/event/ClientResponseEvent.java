package ru.ryabtsev.se.client.event;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;

/**
 * Base class for all response events on client side.
 */
@Getter
public class ClientResponseEvent {
    @NotNull private String message = "";

    public ClientResponseEvent(@NotNull final String message) {
        this.message = message;
    }
}
