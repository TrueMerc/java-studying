package ru.ryabtsev.se.packets.ping;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.ryabtsev.se.packets.PacketResult;
import ru.ryabtsev.se.packets.PacketType;

/** Server response to 'ping' request packet. */

@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PacketPingResponse extends PacketResult {
    {
        setType(PacketType.PING_RESPONSE);
    }

    PacketPingResponse(Boolean success) {
        super( success );
    }
}
