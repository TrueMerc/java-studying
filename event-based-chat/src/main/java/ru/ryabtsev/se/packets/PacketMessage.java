package ru.ryabtsev.se.packets;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.Nullable;

/**
 * Chat server message packet (sends from server to client).
 */
@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public final class PacketMessage extends Packet {

    {
        setType( PacketType.MESSAGE );
    }

    @Nullable
    private String login = "";

    @Nullable
    private String message = "";
}