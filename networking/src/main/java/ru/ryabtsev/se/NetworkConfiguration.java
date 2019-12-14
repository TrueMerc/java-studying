package ru.ryabtsev.se;

import lombok.Getter;
import lombok.Setter;

/**
 * @class Class contains network configuration settings.
 */
@Getter
@Setter
public class NetworkConfiguration {
    private final Integer port = 18080;
    private final String host = "localhost";
}
