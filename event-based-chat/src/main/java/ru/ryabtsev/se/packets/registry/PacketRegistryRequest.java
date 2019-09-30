package ru.ryabtsev.se.packets.registry;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.ryabtsev.se.packets.Packet;
import ru.ryabtsev.se.packets.PacketType;

/**
 * Client 'registry' command packet.
 */
@Getter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public final class PacketRegistryRequest extends Packet {
    {
        setType( PacketType.REGISTRY_REQUEST );
    }

    @NotNull
    private String login = "";

    @NotNull
    private String password = "";

    /**
     * Constructs registry request packet with given parameters.
     * @param login - user login.
     * @param password - user password.
     */
    public PacketRegistryRequest(@NotNull final String login, @NotNull final String password) {
        this.login = login;
        this.password = password;
    }
}

