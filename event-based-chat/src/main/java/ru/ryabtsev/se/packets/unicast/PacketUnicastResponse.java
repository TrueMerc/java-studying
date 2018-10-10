package ru.ryabtsev.se.packets.unicast;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.Nullable;
import ru.ryabtsev.se.packets.PacketResult;
import ru.ryabtsev.se.packets.PacketType;

/** Server response to client 'unicast' message request. */
@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PacketUnicastResponse extends PacketResult {
    {
        setType(PacketType.REGISTRY_RESPONSE);
    }

    PacketUnicastResponse( Boolean success ) {
        super( success );
    }
}
