package ru.ryabtsev.se.packets;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Base class for all packets which contains boolean operation result.
 */
@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PacketResult extends Packet {

    private Boolean success = false;

    {
        setType( PacketType.RESULT );
    }

    public PacketResult( Boolean success ) {
        this.success = success;
    }
}

