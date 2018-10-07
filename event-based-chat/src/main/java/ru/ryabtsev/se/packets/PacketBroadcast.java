package ru.ryabtsev.se.packets;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.Nullable;

/**
 * Chat server broadcast message packet.
 */
@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public final class PacketBroadcast extends Packet {

    {
        setType( PacketType.BROADCAST );
    }

    @Nullable
    private String message = "";
}
