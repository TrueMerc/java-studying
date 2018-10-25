package ru.ryabtsev.se.packets.broadcast;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.ryabtsev.se.packets.PacketResult;
import ru.ryabtsev.se.packets.PacketType;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class PacketBroadcastResponse extends PacketResult {
    {
        setType(PacketType.BROADCAST_RESPONSE);
    }

    @NotNull
    private String login = "";

    @NotNull
    private String message = "";

    /**
     * Constructs server packet broadcast response message.
     * @// TODO: 10/24/18 Divide this class into response message for sender and message class for receivers
     */
    public PacketBroadcastResponse(@NotNull final String login, @NotNull final String message) {
        super( true );
        this.login = login;
        this.message = message;
    }
    
}
