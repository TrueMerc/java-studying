package ru.ryabtsev.se.packets;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Client 'login' command packet.
 */
@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PacketResult extends Packet {

    {
        setType( PacketType.RESULT );
    }

    public PacketResult( Boolean result ) {
        this.success = result;
    }

    private Boolean success = true;
}

