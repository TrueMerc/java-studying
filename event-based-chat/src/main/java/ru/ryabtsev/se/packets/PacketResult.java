package ru.ryabtsev.se.packets;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Base class for all packets which contains boolean operation result.
 */
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PacketResult extends Packet {
    {
        setType( PacketType.RESULT );
    }

    private Boolean success = false;

    /**
     * Constructs result packet.
     * @param success - operation result.
     */
    public PacketResult( Boolean success ) {
        this.success = success;
    }

    /**
     * Operation result.
     * @return true - if operation was successful, false - if it wasn't.
     */
    public boolean isSuccess() {
        return success;
    }
}

