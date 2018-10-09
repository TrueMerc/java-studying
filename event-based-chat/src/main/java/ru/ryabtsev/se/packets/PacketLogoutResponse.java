package ru.ryabtsev.se.packets;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PacketLogoutResponse extends PacketResult {

    {
        setType( PacketType.LOGOUT )
    }

    public PacketLogoutResponse(Boolean result) {
        super(result);
    }


}
