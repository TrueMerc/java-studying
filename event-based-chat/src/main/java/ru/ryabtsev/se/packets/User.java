package ru.ryabtsev.se.packets;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.Nullable;

@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class User {

    @Nullable
    private String login = "";

    @Nullable
    private String password = "";

//    @Nullable
//    private String email = "";

    @Nullable
    private String nickname = "";

    public User( String login, String password, String nickname ) {
        this.login = login;
        this.password = password;
        this.nickname = nickname;
    }
}
