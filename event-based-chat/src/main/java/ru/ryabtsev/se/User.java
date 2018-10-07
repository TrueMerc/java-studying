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
    private String email = "";

    @Nullable
    private String nickname = "";

    public User(@NotNull final String login, @NotNull final String password) {
        this.login = login;
        this.password = password;
    }
}
