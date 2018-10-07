package ru.ryabtsev.se;

import lombok.Getter;
import lombok.Setter;

import javax.enterprise.context.ApplicationScoped;

/**
 * @class Class contains network configuration settings.
 */
@Getter
@Setter
@ApplicationScoped
public class NetworkConfiguration {
    private final Integer port = 16160; // Don't use 8080 port. It's for Jenkins.
    private final String host = "localhost";
}
