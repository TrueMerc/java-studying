package ru.ryabtsev.se.packets.nickname;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.Nullable;
import ru.ryabtsev.se.packets.Packet;
import ru.ryabtsev.se.packets.PacketType;


/**
 * Client 'setnickname' command packet.
 */
@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public final class PacketSetNicknameRequest extends Packet {
    {
        setType( PacketType.SETNICKNAME_REQUEST );
    }

    @Nullable
    private String login = "";

    @Nullable
    private String nickname = "";

}
