package ru.ryabtsev.se.packets.broadcast;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.Nullable;
import ru.ryabtsev.se.packets.Packet;
import ru.ryabtsev.se.packets.PacketType;

/**
 * Chat server broadcast message packet.
 */
@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public final class PacketBroadcastRequest extends Packet {

    {
        setType( PacketType.BROADCAST_REQUEST );
    }

    @Nullable
    private String message = "";
}
