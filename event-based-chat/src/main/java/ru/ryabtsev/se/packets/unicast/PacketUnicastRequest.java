package ru.ryabtsev.se.packets.unicast;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.Nullable;
import ru.ryabtsev.se.packets.Packet;
import ru.ryabtsev.se.packets.PacketType;

/** Client unicast message packet */
@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PacketUnicastRequest extends Packet {
    {
        setType(PacketType.UNICAST_REQUEST);
    }


    public PacketUnicastRequest( final String receiverLogin, final String message ) {
        this.receiverLogin = receiverLogin;
        this.message = message;
    }

    @Nullable String receiverLogin = "";

    @Nullable
    private String message = "";
}
