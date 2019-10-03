package ru.ryabtsev.se.packets.logout;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.ryabtsev.se.packets.Packet;
import ru.ryabtsev.se.packets.PacketType;

/** Client 'logout' command packet */
@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PacketLogoutRequest extends Packet {
    {
        setType(PacketType.LOGOUT_REQUEST);
    }
}
