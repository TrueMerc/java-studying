package ru.ryabtsev.se.packets.unicast;

import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.Nullable;
import ru.ryabtsev.se.packets.Packet;
import ru.ryabtsev.se.packets.PacketType;


@Getter
@Setter
public class PacketUnicastMessage extends Packet {
    {
        setType(PacketType.UNICAST_MESSAGE );
    }

    @Nullable String senderLogin = "";

    @Nullable
    private String message = "";

    /**
     * Constructs unicast packet message.
     * @param senderLogin - message sender login.
     * @param message - message text.
     */
    public PacketUnicastMessage( final String senderLogin, final String message ) {
        this.senderLogin = senderLogin;
        this.message = message;
    }
}

