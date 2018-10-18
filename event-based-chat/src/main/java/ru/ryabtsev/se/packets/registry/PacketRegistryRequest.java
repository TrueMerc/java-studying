package ru.ryabtsev.se.packets.registry;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.Nullable;
import ru.ryabtsev.se.packets.Packet;
import ru.ryabtsev.se.packets.PacketType;

/**
 * Client 'registry' command packet.
 */
@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public final class PacketRegistryRequest extends Packet {
    {
        setType( PacketType.REGISTRY_REQUEST );
    }

    @Nullable
    private String login = "";

    @Nullable
    private String password = "";
}

