package ru.ryabtsev.se.packets;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

/**
 * Default chat application packet.
 */
@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Packet {

    @Nullable
    private String id = UUID.randomUUID().toString();

    @Nullable
    private PacketType type = PacketType.UNKNOWN;

    @Nullable
    private Long timestamp = System.currentTimeMillis();
}
