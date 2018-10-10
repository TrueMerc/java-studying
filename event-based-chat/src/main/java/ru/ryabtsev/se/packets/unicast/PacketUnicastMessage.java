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

    public PacketUnicastMessage( final String senderLogin, final String message ) {
        this.senderLogin = senderLogin;
        this.message = message;
    }

    @Nullable String senderLogin = "";

    @Nullable
    private String message = "";
}

