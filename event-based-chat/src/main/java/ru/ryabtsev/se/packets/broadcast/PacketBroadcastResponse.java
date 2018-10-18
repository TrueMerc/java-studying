package ru.ryabtsev.se.packets.broadcast;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.Nullable;
import ru.ryabtsev.se.packets.PacketResult;
import ru.ryabtsev.se.packets.PacketType;

@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PacketBroadcastResponse extends PacketResult {
    {
        setType(PacketType.BROADCAST_RESPONSE);
    }

    @Nullable
    private String login = "";

    @Nullable
    private String message = "";
}
