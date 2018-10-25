package ru.ryabtsev.se.packets.registry;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.ryabtsev.se.packets.PacketResult;
import ru.ryabtsev.se.packets.PacketType;

/**
 * Server response on client registry request.
 */

@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PacketRegistryResponse extends PacketResult {
    {
        setType(PacketType.REGISTRY_RESPONSE);
    }

    /**
     * Constructs server registry response packet.
     * @param success - registration success.
     */
    PacketRegistryResponse( Boolean success ) {
        super( success );
    }
}
