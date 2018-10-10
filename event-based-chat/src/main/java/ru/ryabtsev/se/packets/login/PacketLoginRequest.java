package ru.ryabtsev.se.packets.login;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.Nullable;
import ru.ryabtsev.se.packets.Packet;
import ru.ryabtsev.se.packets.PacketType;

import java.util.UUID;

/**
 * Client 'login' command packet.
 */
@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public final class PacketLoginRequest extends Packet {

    {
        setType( PacketType.LOGIN_REQUEST );
    }

    @Nullable
    private String login = "";

    @Nullable
    private String password = "";
}
