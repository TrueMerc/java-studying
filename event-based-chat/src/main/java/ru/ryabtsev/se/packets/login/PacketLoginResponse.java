package ru.ryabtsev.se.packets.login;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.ryabtsev.se.packets.PacketResult;
import ru.ryabtsev.se.packets.PacketType;

/**
 * Login response packet.
 */
@Getter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PacketLoginResponse extends PacketResult {
    {
        setType( PacketType.LOGIN_RESPONSE );
    }

    /**
     * Constructs login response packet.
     * @param success - login procedure success sign.
     */
    PacketLoginResponse( boolean success ) {
        super( success );
    }
}
