package ru.ryabtsev.se.configuration;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.enterprise.context.ApplicationScoped;

/**
 * @class Class contains network configuration settings.
 */
@Getter
@Setter
@NoArgsConstructor
@ApplicationScoped
public class NetworkConfiguration {
    private Integer port = 16160; // Don't use 8080 port. It's for Jenkins.
    private String host = "localhost";
    private String login;
}
