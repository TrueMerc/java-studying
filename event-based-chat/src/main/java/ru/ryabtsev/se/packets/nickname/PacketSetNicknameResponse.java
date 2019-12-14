package ru.ryabtsev.se.packets.nickname;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.ryabtsev.se.packets.PacketResult;
import ru.ryabtsev.se.packets.PacketType;



@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PacketSetNicknameResponse extends PacketResult {
    {
        setType( PacketType.SETNICKNAME_RESPONSE );
    }
}