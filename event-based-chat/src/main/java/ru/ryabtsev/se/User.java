package ru.ryabtsev.se;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
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

    @Nullable
    private String nickname = "";

    /**
     * Constructs User object.
     * @param login - user login.
     * @param password - user password.
     * @param nickname - user nickname.
     */
    public User(@NotNull String login, @NotNull String password, @Nullable String nickname ) {
        this.login = login;
        this.password = password;
        this.nickname = nickname;
    }
}
